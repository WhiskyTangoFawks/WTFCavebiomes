package cavebiomes.worldgeneration.dungeontypes;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.utilities.gencores.VanillaGen;
import wtfcore.WTFCore;
import wtfcore.utilities.DungeonBlockPosition;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class DungeonType{

	protected Random random = new Random();

	protected Block modifier;
	protected int spawncounter;
	protected int wallStripe;
	
	//These are the required dimensions for generation- to make a larger area required, override these
	public int xClearance = 6;
	public int yClearance = 4;
	public int zClearance = 6;
	
	int dungeonMaxSize = 6;

	public String name;

	protected static VanillaGen gen;

	public DungeonType(String name){
		this.name = name;
		DungeonType.gen = VanillaGen.getGenMethods();
	}


	public void SpawnDungeon(World world, Random rand, int x, int y, int z)
	{
		//Step1: loop outwards, and find a rough center of the open space
		
		//Get X
		int xpos = x+1;
		for (int loop = 0; world.isAirBlock(xpos, y, z) && loop < dungeonMaxSize ; loop++){
			xpos++;
		}
		int xneg = x-1;
		for (int loop = 0;world.isAirBlock(xneg, y, z) && loop < dungeonMaxSize; loop++){
			xneg--;
		}
		int xrange = (xpos-xneg);
		if (xrange < xClearance){return;}
		x=xneg+xrange/2;
		
		//Get z
		int zpos = z+1;
		for (int loop = 0; world.isAirBlock(x, y, zpos) && loop < dungeonMaxSize; loop++){
			zpos++;
		}
		int zneg = z-1;
		for (int loop = 0;world.isAirBlock(x, y, zneg) && loop < dungeonMaxSize; loop++){
			zneg--;
		}
		int zrange = (zpos-zneg);
		if (zrange < zClearance){return;}
		z=zneg+zrange/2;
		//get Y
		int ypos = y+1;
		for (int loop = 0; world.isAirBlock(x, ypos, z) && loop < dungeonMaxSize; loop++){
			ypos++;
		}
		int yneg = y-1;
		while (world.isAirBlock(x, yneg, z)){
			yneg--;
		}
		int yrange = (ypos-yneg);
		if (yrange < yClearance || yrange > 2*yClearance){return;}
		y=yneg+yrange/2;
		int floor = yneg;
		int ceiling = ypos;
		this.wallStripe =y+1;
		
		//WTFCore.log.info("Size = " + xrange + " " + yrange + " " +zrange);

		if (!canSpawnHere(world, x, y, z, ceiling, floor)){return;}
		
		if (BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(x, z), Type.SNOWY)){modifier =  Blocks.ice;}
		else {modifier=null;}

		if(WTFCaveBiomesConfig.logDungeons){
			//EventListener.thePlayer.addChatMessage(new ChatComponentText("Spawning " + this.name + " @ " + x + " " + y + " " + z));
			WTFCore.log.info("Spawning " + this.name + " @ " + x + " " + floor + " " + z);
		}

		//so, it finds all the walls, then draws a box that big to make it's explosion
		//can I instead add all the blocks to a hashset, and then draw a ray to all the solid ones?
		HashSet<DungeonBlockPosition> hashset = new HashSet<DungeonBlockPosition>();
		HashSet<DungeonBlockPosition> air = new HashSet<DungeonBlockPosition>();

		int xmin = (0-xrange)/2;
		int xmax = xrange/2;
		int ymin = (0-yrange)/2;
		int ymax = yrange/2;
		int zmin = (0-zrange)/2;
		int zmax = zrange/2;
		
		double oriX = x + 0.5;
		double oriY = floor + (ceiling - floor)/2;
		double oriZ = z + 0.5;
		
		for (int xloop = xmin; xloop < xmax+1; ++xloop) {
			for (int yloop = ymin; yloop < ymax+1; ++yloop) {
				for (int zloop = zmin; zloop < zmax+1; ++zloop) {
					if (xloop==xmin || xloop==xmax || yloop==ymin || yloop==ymax || zloop==zmin || zloop == zmax){

						
						//the values to increment along the ray each loop
						double incX = (0 + xloop);
						double incY = (0 + yloop);
						double incZ = (0 + zloop);

						//length of the vector
						double vectorLength = Math.sqrt(incX * incX + incY * incY + incZ * incZ);
						
						
						//setting the values
						incX /= (vectorLength);
						incY /= (vectorLength);
						incZ /= (vectorLength);
						
						//origin
		
						
						int i = MathHelper.floor_double(oriX);
						int j = MathHelper.floor_double(oriY);
						int k = MathHelper.floor_double(oriZ);
						
						//problem: this is potentially running after stalactite generation
						for (int loop = 1; world.isAirBlock(i, j, k) && loop < vectorLength+1; loop++){
							air.add(new DungeonBlockPosition(i, j, k, false));
							i = MathHelper.floor_double(oriX+(incX*loop));
							j = MathHelper.floor_double(oriY+(incY*loop));
							k = MathHelper.floor_double(oriZ+(incZ*loop));
							
						}
		
						if (!world.isAirBlock(i, j, k)){
							hashset.add(new DungeonBlockPosition(i, j, k, false));
						}

					}
				}
			}
		}

		Iterator<DungeonBlockPosition> iterator= hashset.iterator();

		DungeonBlockPosition chunkposition;
		while (iterator.hasNext()) {
			chunkposition = (DungeonBlockPosition)iterator.next();
			int i = chunkposition.chunkPosX;
			int j = chunkposition.chunkPosY;
			int k = chunkposition.chunkPosZ;

			//gen.setBlockWithoutNotify(world, i, j, k, Blocks.gold_block, 0);
			
			
			if (j < oriY && !world.getBlock(i, j+1, k).renderAsNormalBlock())
			{
				//gen.setBlockWithoutNotify(world,i, j, k, Blocks.redstone_block, 0);
				this.generateFloor(world, rand, i, j, k);
			}
			else if ( j > oriY && !world.getBlock(i,  j-1, k).renderAsNormalBlock()){
				this.generateCeiling(world, rand, i, j, k);
				//gen.setBlockWithoutNotify(world, i, j, k, Blocks.gold_block, 0);
			}
			else {
				
				if (j == wallStripe && generateWallStripe(world, rand, i, j, k)){
					//the generator is called as a boolean- to make sure that if a dungeontype doesn't have a stripe, it doesn't skip that part of the wall
				}
				else {
					//gen.setBlockWithoutNotify(world, i, j, k, Blocks.iron_block, 0);
					this.generateWalls(world, rand, i, j, k);
				}
			}

		}

		this.generateCenter(world, rand, x, y, z, ceiling, floor);

		iterator = air.iterator();
		while (iterator.hasNext()){
			chunkposition = (DungeonBlockPosition)iterator.next();
			int i = chunkposition.chunkPosX;
			int j = chunkposition.chunkPosY;
			int k = chunkposition.chunkPosZ;

			this.generateFill(world, rand, i,j,k);
		}
		spawncounter = 0;
	}

	/**
	 * Checks for special conditions- if you're using blocks from other mods, you should add a check here for the mod being installed to prevent null crashes
	 */
	public boolean canSpawnHere(World world, int x, int y, int z, int ceiling, int floor) {
		return true;
	}


	/**
	 * Generates blocks for the ceiling
	 */
	public void generateCeiling(World world, Random rand, int x, int y, int z){	}

	/**
	 * generates blocks on the floor
	 */
	public void generateFloor(World world, Random rand, int x, int y, int z){}
/**
 *  generates blocks on the wall
 */
	public void generateWalls(World world, Random rand, int x, int y, int z){}

	/**
	 * called once, at the center of the dungeon, used to generate mob spawners and the like
	 */
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor){}

	/**
	 * generates fill blocks- used by cave in and frozen dungeons, to replace all air blocks within the dungeon with whatever is supplied.
	 **/
	public void generateFill(World world, Random rand, int x, int y, int z){

	}
	/**
	 * generates a stripe along the wall, 1 block below the ceiling
	 **/
	public boolean generateWallStripe(World world, Random rand, int x, int y, int z){
		return false;
	}



}
