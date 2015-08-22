package cavebiomes.worldgeneration.dungeontypes;

import java.util.Random;

import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.worldgeneration.DungeonType;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import wtfcore.utilities.BlockSets;

public class DungeonMagmaSlime extends DungeonType{

	public DungeonMagmaSlime() {
		super("MagmaSlime");
	}

	BlockSets.Modifier[] blockarray = {null, BlockSets.Modifier.stoneMagmaCrust, BlockSets.Modifier.cobblestone};

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.transformBlock(world, x, y, z, BlockSets.Modifier.LavaRainStone);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){

		gen.transformBlock(world, x, y, z, blockarray[random.nextInt(blockarray.length)]);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		if (WTFCaveBiomesConfig.EnableMobSpawners){
			while (world.isAirBlock(x,  y-1,  z)){y--;}
			world.setBlock(x, y, z, Blocks.mob_spawner, 0, 2);
			TileEntityMobSpawner spawner = (TileEntityMobSpawner)world.getTileEntity(x, y, z);
			spawner.func_145881_a().setEntityName("LavaSlime");
			NBTTagCompound nbt = new NBTTagCompound();
			spawner.writeToNBT(nbt);
			nbt.setShort("spawnCount",(short)2);
			nbt.setShort("MinSpawnDelay",(short)600);
			spawner.readFromNBT(nbt);
		}

	}
}