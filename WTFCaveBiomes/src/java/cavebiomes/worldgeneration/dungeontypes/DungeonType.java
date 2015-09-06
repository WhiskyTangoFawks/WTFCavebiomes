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
	int xmin = -5;
	int xmax = 5;
	int ymin = -5;
	int ymax = 5;
	int zmin = -5;
	int zmax = 5;
	float radius = 6;
	protected Random random = new Random();

	protected Block modifier;
	protected int spawncounter;
	protected int wallStripe;
	
	//These are the required dimensions for generation- to make a larger area required, override these
	public int xClearance = 6;
	public int yClearance = 4;
	public int zClearance = 6;

	public String name;

	protected static VanillaGen gen;

	public DungeonType(String name){
		this.name = name;
		DungeonType.gen = VanillaGen.getGenMethods();
	}


	public void SpawnDungeon(World world, Random rand, int x, int y, int z)
	{
		//This is a routine which scans out in each direction, to find the center of a large clear area
		
		//Get X
		int xpos = x+1;
		for (int loop = 0; world.isAirBlock(xpos, y, z) && loop < 8; loop++){
			xpos++;
		}
		int xneg = x-1;
		for (int loop = 0;world.isAirBlock(xneg, y, z) && loop < 8; loop++){
			xneg--;
		}
		int xrange = (xpos-xneg);
		if (xrange < xClearance){return;}
		x=xneg+xrange/2;
		
		//Get z
		int zpos = z+1;
		for (int loop = 0; world.isAirBlock(x, y, zpos) && loop < 8; loop++){
			zpos++;
		}
		int zneg = z-1;
		for (int loop = 0;world.isAirBlock(x, y, zneg) && loop < 8; loop++){
			zneg--;
		}
		int zrange = (zpos-zneg);
		if (zrange < zClearance){return;}
		z=zneg+zrange/2;
		//get Y
		int ypos = y+1;
		for (int loop = 0; world.isAirBlock(x, ypos, z) && loop < 8; loop++){
			ypos++;
		}
		int yneg = y-1;
		for (int loop = 0;world.isAirBlock(x, yneg, z) && loop < 8; loop++){
			yneg--;
		}
		int yrange = (ypos-yneg);
		if (yrange < yClearance){return;}
		y=yneg+yrange/2;
		int floor = yneg;
		int ceiling = ypos;
		this.wallStripe = ceiling -1;
		
		if (!canSpawnHere(world, x, y, z, ceiling, floor)){return;}
		
		if (BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(x, z), Type.SNOWY)){modifier =  Blocks.ice;}
		else {modifier=null;}

		if(WTFCaveBiomesConfig.logDungeons){
			//EventListener.thePlayer.addChatMessage(new ChatComponentText("Spawning " + this.name + " @ " + x + " " + y + " " + z));
			WTFCore.log.info("Spawning " + this.name + " @ " + x + " " + floor + " " + z);
		}


		HashSet<DungeonBlockPosition> hashset = new HashSet<DungeonBlockPosition>();
		HashSet<DungeonBlockPosition> air = new HashSet<DungeonBlockPosition>();

		for (int xloop = xmin; xloop < xmax+1; ++xloop) {
			for (int yloop = ymin; yloop < ymax+1; ++yloop) {
				for (int zloop = zmin; zloop < zmax+1; ++zloop) {
					if (xloop==xmin || xloop==xmax || yloop==ymin || yloop==ymax || zloop==zmin || zloop == zmax){
						double d3 = (0 + xloop);
						double d4 = (0 + yloop);
						double d5 = (0 + zloop);

						double vectorLength = Math.sqrt(d3 * d3 + d4 * d4 + d5 * d5);
						d3 /= (vectorLength);
						d4 /= (vectorLength);
						d5 /= (vectorLength);

						//origin
						double currentX = x + 0.5;
						double currentY =floor + (ceiling - floor)/2;
						double currentZ = z + 0.5;

						float vectorStr = radius;
						Boolean cont = true;

						for (float f2 = 0.3F; vectorStr > 0.0F && cont; vectorStr -= f2 * 0.75F){
							int i = MathHelper.floor_double(currentX);
							int j = MathHelper.floor_double(currentY);
							int k = MathHelper.floor_double(currentZ);

							if (!world.isAirBlock(i, j, k)){
								hashset.add(new DungeonBlockPosition(i, j, k, false));
								cont = false;
							}
							else {
								air.add(new DungeonBlockPosition(i, j, k, false));
							}

							vectorStr -= 0.3F;
							currentX += d3 * f2;
							currentY += d4 * f2;
							currentZ += d5 * f2;
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


			if (j < y && !world.getBlock(i, j+1, k).renderAsNormalBlock())
			{
				this.generateFloor(world, rand, i, j, k);
			}
			else if ( j > y && !world.getBlock(i,  j-1, k).renderAsNormalBlock()){
				this.generateCeiling(world, rand, i, j, k);
			}
			else {
				
				if (y == wallStripe && generateWallStripe(world, rand, i, j, k)){
					//the generator is called as a boolean- to make sure that if a dungeontype doesn't have a stripe, it doesn't skip that part of the wall
				}
				else {
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
		//I could really use something that makes a bubble, then finds the center of it
		//I can probably do this with just x-y-z loops that go out in each direction
		//until they hit not air
		//then, find the center of it
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
