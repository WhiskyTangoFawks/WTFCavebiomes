package cavebiomes.worldgeneration.cavetypes.deep;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import wtfcore.utilities.BlockSets;
import cavebiomes.worldgeneration.CaveType;
import cavebiomes.worldgeneration.DungeonType;

public class CaveTypeWetDeep extends CaveType{

	public CaveTypeWetDeep(String name, int cavedepth, DungeonType[] dungeonlist) {
		super(name, cavedepth, dungeonlist);
	}

	BlockSets.Modifier[] blockArray = {null, null, BlockSets.Modifier.cobblestone};

	@Override
	public void generateCeiling(World world, Random random, int x, int y, int z)
	{
		if (random.nextInt(4)==0){
			gen.transformBlock(world, x, y, z, BlockSets.Modifier.waterDrippingStone);
		}
	}

	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		gen.genStalactite(world, x, y, z, depth);
	}

	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		if (IsBlockSurrounded(world, x, y, z)){
			if (random.nextInt(8) == 0){
				gen.transformBlock(world, x, y, z, BlockSets.Modifier.cobblestone);
			}
			else {
				gen.setFluid(world, x, y, z, Blocks.water, blockArray[random.nextInt(blockArray.length)]);
			}
		}
	}


}
