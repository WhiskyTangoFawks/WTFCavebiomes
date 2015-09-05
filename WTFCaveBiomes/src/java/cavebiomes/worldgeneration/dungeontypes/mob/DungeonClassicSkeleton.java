package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.worldgeneration.dungeontypes.DungeonType;

public class DungeonClassicSkeleton extends DungeonType{

	public DungeonClassicSkeleton() {
		super("ClassicSkeleton");
	}

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick,0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick,0);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick,0);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		if (WTFCaveBiomesConfig.EnableMobSpawners){
			while (world.isAirBlock(x,  y-1,  z)){y--;}
			world.setBlock(x, y, z, Blocks.mob_spawner, 0, 2);
			TileEntityMobSpawner spawner = (TileEntityMobSpawner)world.getTileEntity(x, y, z);
			spawner.func_145881_a().setEntityName("Skeleton");
		}
	}


}
