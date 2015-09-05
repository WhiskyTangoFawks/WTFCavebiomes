package cavebiomes;

import cavebiomes.utilities.BiomeAndHeight;
import cavebiomes.worldgeneration.CaveType;
import cavebiomes.worldgeneration.CaveTypeRegister;
import cavebiomes.worldgeneration.dungeontypes.DungeonType;
import cavebiomes.worldgeneration.dungeontypes.DungeonTypeRegister;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import wtfcore.WTFCore;
import wtfcore.utilities.BlockInfo;
import wtfcore.utilities.BlockSets;

public class CaveBiomesAPI {

	/**
	 * Use this to add new custom cave types to the generator.
	 * @param cavetype
	 * @param biome 
	 * @param depth : The depth at which the cave should spawn, must be 1 (shallow) 2 (mid) or 3 (deep)
	 */
	public static void addCaveType(CaveType cavetype, BiomeGenBase biome, int depth){
		if (depth >0 && depth < 4){
			CaveTypeRegister.cavebiomemap.put(new BiomeAndHeight(biome, depth), cavetype);
		}
		else {
			WTFCore.log.info("CaveBiomesAPI: CaveType had depth outside acceptable range " + cavetype.name);
		}
	}
	
	public enum DungeonBiomeType {DEFAULT, FOREST, WET, SWAMP, DESERT, JUNGLE, COLD, VOLCANIC};
	
	/**
	 * Adds a custom dungeon to the generator
	 * @param dungeon
	 * @param biometype: the type of biome you wish it to spawn in
	 */
	public static void addDungeon(DungeonType dungeon, DungeonBiomeType biometype){
		if (biometype == DungeonBiomeType.DEFAULT){
			DungeonTypeRegister.defaultSet.addDungeon(dungeon);
		}
		else if (biometype == DungeonBiomeType.FOREST){
			DungeonTypeRegister.forestSet.addDungeon(dungeon);
		}
		else if (biometype == DungeonBiomeType.WET){
			DungeonTypeRegister.wetSet.addDungeon(dungeon);
		}
		else if (biometype == DungeonBiomeType.SWAMP){
			DungeonTypeRegister.swampSet.addDungeon(dungeon);
		}
		else if (biometype == DungeonBiomeType.DESERT){
			DungeonTypeRegister.desertSet.addDungeon(dungeon);
		}
		else if (biometype == DungeonBiomeType.JUNGLE){
			DungeonTypeRegister.jungleSet.addDungeon(dungeon);
		}
		else if (biometype == DungeonBiomeType.COLD){
			DungeonTypeRegister.coldSet.addDungeon(dungeon);
		}
		else if (biometype == DungeonBiomeType.VOLCANIC){
			DungeonTypeRegister.volcanicSet.addDungeon(dungeon);
		}
	}
	/**
	 * Adds a Stone+Cobblestone pairing to the blockTransformer, allowing cavebiomes to use it for generation
	 * @param dungeon
	 * @param biometype: the type of biome you wish it to spawn in
	 */
	public static void addStoneAndCobble(Block stone, Block cobblestone){
		BlockSets.blockTransformer.put(new BlockInfo(stone, 0, BlockSets.Modifier.cobblestone), cobblestone);
		BlockSets.ReplaceHashset.add(stone);
	}
	
}
