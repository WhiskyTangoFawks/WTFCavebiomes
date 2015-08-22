package cavebiomes.worldgeneration;

import java.util.ArrayList;
import java.util.Random;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.utilities.gencores.GenCoreProvider;
import cavebiomes.utilities.gencores.VanillaGen;
import net.minecraft.world.World;

public class CaveType
{
	protected static VanillaGen gen;
	public final String			name;
	protected int depth;

	public int DungeonWeight = WTFCaveBiomesConfig.dungeonChance;

	protected int ceilingaddonchance;
	protected int flooraddonchance;

	private ArrayList<DungeonType> dungeonList;
	Random rand = new Random();

	public CaveType(String name, int cavedepth, ArrayList<DungeonType> dungeonlist)
	{
		this.name = name;
		this.depth = cavedepth;
		this.ceilingaddonchance = WTFCaveBiomesConfig.ceilingAddonChance * cavedepth;
		this.flooraddonchance = WTFCaveBiomesConfig.floorAddonChance * depth;
		this.dungeonList = dungeonlist;
		CaveType.gen = GenCoreProvider.getGenCore();
	}

	public String getName()
	{
		return this.name;
	}

	/**
	 *  Generation routine- override this only if you want to change how the cave type generates
	 */
	
	public void generate(World world, Random random, int x, int floor, int ceiling, int z){
		//int center = floor + (ceiling - floor) / 2;
		
		this.generateFloor(world, random, x, floor, z);
		this.generateCeiling(world, random, x, ceiling, z);
		if (random.nextInt(100) < ceilingaddonchance)
		{
			this.generateCeilingAddons(world, random, x, ceiling - 1, z);
		}
	}
	
	/**
	 *  Generation routine- override this only if you want to change how the dungeon generates
	 */

	public void generateDungeon(World world, Random rand, int x, int y, int z, int ceiling, int floor){
		DungeonType type = this.getDungeonType(world, rand, x, y, z);
		if (type != null){
			type.SpawnDungeon(world, rand, x, y, z, ceiling, floor);
		}
	}

	/**
	 *  Called for ceiling generation- note, ceiling addons are called seperately
	 */
	public void generateCeiling(World world, Random random, int x, int y, int z){
	}

	/**
	 * Called to generate the floor
	 * If you want to generate addons, use shouldFloorGenAddon to hook into the random chance set in the config, and generate them within this method
	 */
	public void generateFloor(World world, Random random, int x, int y, int z){
	}

	/**
	 * Called to generate the ceiling addons, primarily used for stalacties and vines
	 */
	public void generateCeilingAddons(World world, Random random, int x, int y, int z){
	}


	private DungeonType getDungeonType(World world, Random rand, int x, int y, int z){
		DungeonType type = this.dungeonList.get(rand.nextInt(dungeonList.size()));
		return type;
	}
	protected boolean shouldGenFloorAddon(){
		return rand.nextInt(100) < this.flooraddonchance;
	}

	/**
	 * Used to determine if a block is surrounded- any placement of fluids should call this method to prevent flooding
	 */
	public static boolean IsBlockSurrounded(World world, int x, int y, int z)	{
		if (!world.isAirBlock(x+1, y, z)){
			if (!world.isAirBlock(x-1, y, z)){
				if (!world.isAirBlock(x, y, z+1)){
					if (!world.isAirBlock(x, y, z-1)){
						if (!world.isAirBlock(x, y-1, z)){
							return true;
						}
					}
				}
			}
		}
		return false;
	}


}
