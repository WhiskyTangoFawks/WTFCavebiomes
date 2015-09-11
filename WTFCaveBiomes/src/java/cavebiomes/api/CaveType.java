package cavebiomes.api;

import java.util.Random;
import cavebiomes.WTFCaveBiomesConfig;
import net.minecraft.world.World;

public class CaveType
{
	public static GenCoreBase gen;
	public final String	name = "RENAME ME";
	protected int depth = 0; //Set this to 1 for shallow, 2 for mid, and 3 for deep

	public int DungeonWeight = ConfigAPI.dungeonChance;

	public int ceilingaddonchance = ConfigAPI.ceilingAddonChance * depth;
	int flooraddonchance = ConfigAPI.floorAddonChance * depth;

	public DungeonSet dungeons;
	

	public CaveType(int cavedepth,  DungeonSet dungeonset)
	{
		this.depth = cavedepth;
		this.ceilingaddonchance = WTFCaveBiomesConfig.ceilingAddonChance * cavedepth;
		this.flooraddonchance = WTFCaveBiomesConfig.floorAddonChance * depth;
		this.dungeons = dungeonset;

	}

	/**
	 *  Called for ceiling generation- note, ceiling addons are called seperately
	 */
	public void generateCeiling(World world, Random random, int x, int y, int z){
		//gen.setBlockWithoutNotify(world, x, y, z, block, metadata)
	}

	/**
	 * Called to generate the floor
	 * If you want to generate addons, use shouldFloorGenAddon to hook into the random chance set in the config, and generate them within this method
	 */
	public void generateFloor(World world, Random random, int x, int y, int z){
		//gen.setBlockWithoutNotify(world, x, y, z, block, metadata)
		if (shouldGenFloorAddon(random)){
			//gen.genStalagmite(world, x, y, z, depth);
		}
	}

	/**
	 * Called to generate the ceiling addons, primarily used for stalacties and vines
	 */
	public void generateCeilingAddons(World world, Random random, int x, int y, int z){
		//gen.genStalactite(world, x, y, z, depth);
	}


	protected boolean shouldGenFloorAddon(Random random){
		return random.nextInt(100) < this.flooraddonchance;
	}




	

	


}
