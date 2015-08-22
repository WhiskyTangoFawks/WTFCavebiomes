package cavebiomes.worldgeneration.dungeontypes;

import java.util.Random;

import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.worldgeneration.DungeonType;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class DungeonSlime extends DungeonType{

	public DungeonSlime() {
		super("Slime");
	}

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		world.setBlock( x, y, z, Blocks.stonebrick, 2, 0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){

		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick, 2);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick, 2);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		if (WTFCaveBiomesConfig.EnableMobSpawners){
			while (world.isAirBlock(x,  y-1,  z)){y--;}
			world.setBlock(x, y, z, Blocks.mob_spawner, 0, 2);
			TileEntityMobSpawner spawner = (TileEntityMobSpawner)world.getTileEntity(x, y, z);
			spawner.func_145881_a().setEntityName("Slime");
			NBTTagCompound nbt = new NBTTagCompound();
			spawner.writeToNBT(nbt);
			nbt.setShort("spawnCount",(short)1);
			nbt.setShort("MinSpawnDelay",(short)2000);
			nbt.setShort("MaxSpawnDelay",(short)16000);
			spawner.readFromNBT(nbt);
		}
	}


}
