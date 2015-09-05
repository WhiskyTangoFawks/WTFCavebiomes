package cavebiomes.worldgeneration.dungeontypes;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import cavebiomes.EventListener;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.utilities.gencores.VanillaGen;
import wtfcore.WTFCore;
import wtfcore.utilities.DungeonBlockPosition;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
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

	//private int spawnHeight;
	public String name;

	protected static VanillaGen gen;

	public DungeonType(String name){
		this.name = name;
		DungeonType.gen = VanillaGen.getGenMethods();
	}


	public void SpawnDungeon(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		if (!canSpawnHere(world, x, y, z, ceiling, floor)){return;}
		
		if (BiomeDictionary.isBiomeOfType(world.getBiomeGenForCoords(x, z), Type.SNOWY)){modifier =  Blocks.ice;}
		else {modifier=null;}

		if(WTFCaveBiomesConfig.logDungeons){
			EventListener.thePlayer.addChatMessage(new ChatComponentText("Spawning " + this.name + " @ " + x + " " + y + " " + z));
			WTFCore.log.info("Spawning " + this.name + " @ " + x + " " + y + " " + z);
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
						double currentY = y + 0.5;
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
				this.generateWalls(world, rand, i, j, k);
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
	 * Checks for special conditions
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




}
