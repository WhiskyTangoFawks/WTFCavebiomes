package cavebiomes.worldgeneration.dungeontypes;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.worldgeneration.DungeonType;

public class DungeonTypePharohTomb extends DungeonType{

	public DungeonTypePharohTomb(String name) {
		super(name);
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
			while (world.isAirBlock(x,  y-1,  z)){y--;}
			world.setBlock(x, y, z, Blocks.mob_spawner, 0, 2);
			TileEntityMobSpawner spawner = (TileEntityMobSpawner)world.getTileEntity(x, y, z);
			spawner.func_145881_a().setEntityName("ZombiePharoh");


			NBTTagCompound nbt = new NBTTagCompound();
			spawner.writeToNBT(nbt);
			nbt.setShort("spawnCount",(short)2);
			nbt.setShort("MinSpawnDelay",(short)600);
			spawner.readFromNBT(nbt);



		}
	}


}
