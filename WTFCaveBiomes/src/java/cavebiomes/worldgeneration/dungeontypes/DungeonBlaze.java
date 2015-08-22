package cavebiomes.worldgeneration.dungeontypes;

import java.util.Random;

import wtfcore.utilities.BlockSets;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.worldgeneration.DungeonType;

public class DungeonBlaze extends DungeonType{

	public DungeonBlaze() {
		super("ClassicBlaze");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.transformBlock(world, x, y, z, BlockSets.Modifier.LavaRainStone);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x,  y,  z, Blocks.nether_brick, 0);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x,  y,  z, Blocks.nether_brick, 0);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		if (WTFCaveBiomesConfig.EnableMobSpawners){
			while (world.isAirBlock(x,  y-1,  z)){y--;}
		world.setBlock(x, y, z, Blocks.mob_spawner, 0, 2);
		TileEntityMobSpawner spawner = (TileEntityMobSpawner)world.getTileEntity(x, y, z);
		spawner.func_145881_a().setEntityName("Blaze");
		NBTTagCompound nbt = new NBTTagCompound();
		spawner.writeToNBT(nbt);
		nbt.setShort("spawnCount",(short)1);
		nbt.setShort("MinSpawnDelay",(short)600);
		spawner.readFromNBT(nbt);


		}
	}
}
