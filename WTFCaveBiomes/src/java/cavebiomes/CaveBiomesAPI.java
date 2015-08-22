package cavebiomes;

import cavebiomes.utilities.BiomeAndHeight;
import cavebiomes.worldgeneration.CaveType;
import cavebiomes.worldgeneration.CaveTypeRegister;
import cavebiomes.worldgeneration.DungeonType;
import net.minecraft.world.biome.BiomeGenBase;

public class CaveBiomesAPI {

	public static void addCaveType(CaveType cavetype, BiomeGenBase biome, int depth){
		CaveTypeRegister.cavebiomemap.put(new BiomeAndHeight(biome, depth), cavetype);
	}
	
	public enum DungeonCategory {DEFAULT, FOREST, WET, SWAMP, DESERT, JUNGLE, COLD, VOLCANIC};
	
	public static void addDungeon(DungeonType dungeon, DungeonCategory category){
		if (category == DungeonCategory.DEFAULT){
			CaveTypeRegister.defaultlist.add(dungeon);
		}
		else if (category == DungeonCategory.FOREST){
			CaveTypeRegister.forestlist.add(dungeon);
		}
		else if (category == DungeonCategory.WET){
			CaveTypeRegister.wetlist.add(dungeon);
		}
		else if (category == DungeonCategory.SWAMP){
			CaveTypeRegister.swamplist.add(dungeon);
		}
		else if (category == DungeonCategory.DESERT){
			CaveTypeRegister.desertlist.add(dungeon);
		}
		else if (category == DungeonCategory.JUNGLE){
			CaveTypeRegister.junglelist.add(dungeon);
		}
		else if (category == DungeonCategory.COLD){
			CaveTypeRegister.coldlist.add(dungeon);
		}
		else if (category == DungeonCategory.VOLCANIC){
			CaveTypeRegister.volcaniclist.add(dungeon);
		}
	}
	
}
