package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.entities.Entities;
import cavebiomes.entities.SpawnerBlockEntity;
import cavebiomes.entities.zombie.ZombiePharoh;
import cavebiomes.worldgeneration.dungeontypes.DungeonType;

public class DungeonTypePharohTomb extends DungeonType{

	public DungeonTypePharohTomb() {
		super("Pharoh'sTomb");
	}

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.sandstone,2);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.sandstone,2);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.sandstone, 1);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		if (WTFCaveBiomesConfig.EnableMobSpawners){
			if (WTFCaveBiomesConfig.EnableMobSpawners){
				while (world.isAirBlock(x,  y-1,  z)){y--;}
				world.setBlock(x, y, z, Entities.spawners.get("ZombiePharoh"), 0, 2);
				
			}



		}
	}


}
