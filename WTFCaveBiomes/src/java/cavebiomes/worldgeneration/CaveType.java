package cavebiomes.worldgeneration;

import java.util.Random;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.utilities.gencores.GenCoreProvider;
import cavebiomes.utilities.gencores.VanillaGen;
import net.minecraft.world.World;
import wtfcore.WTFCore;

public class CaveType
{
	protected static VanillaGen gen;
	public final String			name;
	protected int depth;

	public int DungeonWeight = WTFCaveBiomesConfig.dungeonChance;

	protected int ceilingaddonchance;
	protected int flooraddonchance;

	protected DungeonSet dungeons;
	Random rand = new Random();

	public CaveType(String name, int cavedepth,  DungeonSet dungeonset)
	{
		this.name = name;
		this.depth = cavedepth;
		this.ceilingaddonchance = WTFCaveBiomesConfig.ceilingAddonChance * cavedepth;
		this.flooraddonchance = WTFCaveBiomesConfig.floorAddonChance * depth;
		this.dungeons = dungeonset;

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
			WTFCore.log.info("Spawning "+ type.name);
			type.SpawnDungeon(world, rand, x, y, z, ceiling, floor);
		}
		else { WTFCore.log.info("DungeonMap is Empty");
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
		if(dungeons==null){
			WTFCore.log.info("NULL WARNING");}
		return (dungeons.getRandomDungeon(rand));	
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
