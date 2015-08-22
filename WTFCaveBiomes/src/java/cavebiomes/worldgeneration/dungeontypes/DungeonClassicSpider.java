package cavebiomes.worldgeneration.dungeontypes;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import wtfcore.utilities.BlockSets;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.worldgeneration.DungeonType;

public class DungeonClassicSpider extends DungeonType{

	public DungeonClassicSpider(String name) {
		super(name);
	}

	BlockSets.Modifier[] swampBlock = {BlockSets.Modifier.mossy_cobblestone, BlockSets.Modifier.cobblestone, BlockSets.Modifier.MossyStone};

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){

		BlockSets.Modifier addonblock = swampBlock[random.nextInt(swampBlock.length)];
		gen.transformBlock(world, x, y, z, addonblock);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.cobblestone, 0);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		if (WTFCaveBiomesConfig.EnableMobSpawners){
			while (world.isAirBlock(x,  y-1,  z)){y--;}
			world.setBlock(x, y, z, Blocks.mob_spawner, 0, 2);
			TileEntityMobSpawner spawner = (TileEntityMobSpawner)world.getTileEntity(x, y, z);
			spawner.func_145881_a().setEntityName("Spider");
		}
	}


}

