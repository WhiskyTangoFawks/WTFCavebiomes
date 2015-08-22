package cavebiomes.worldgeneration.cavetypes.shallow;

import java.util.Random;
import wtfcore.utilities.BlockSets;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cavebiomes.blocks.CaveBlocks;
import cavebiomes.worldgeneration.CaveType;
import cavebiomes.worldgeneration.DungeonType;

public class CaveTypeSwamp extends CaveType {

	public CaveTypeSwamp(String name, int cavedepth, DungeonType[] dungeonlist) {
		super(name, cavedepth, dungeonlist);
	}


	@Override
	public void generateCeiling(World world, Random random, int x, int y, int z)
	{
		if (random.nextInt(4)==0){
			gen.transformBlock(world, x, y, z, BlockSets.Modifier.lavaDrippinStone);
		}
	}


	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		if (random.nextBoolean()){
			if (gen.GenVines(world, x, y, z)){//vines generated
			}
			else {
				gen.genStalactite(world, x, y, z, depth);
			}
		}
	}

	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		int height = 6+MathHelper.abs_int((MathHelper.abs_int(x-z) % 10) -5) + (random.nextInt(3)-1);

		BlockSets.Modifier[] swampBlock = {BlockSets.Modifier.mossy_cobblestone, BlockSets.Modifier.cobblestone, BlockSets.Modifier.MossyStone};
		BlockSets.Modifier addonblock = swampBlock[random.nextInt(swampBlock.length)];

		if (height < 9 ){
			if (IsBlockSurrounded(world, x, y, z)){
				gen.setFluid(world, x, y, z, Blocks.water, addonblock);
				if (random.nextInt(8)==0 && CaveBlocks.caveLilyPad.canBlockStay(world,  x,  y+1,  z)){
					gen.setBlockWithoutNotify(world, x, y+1, z, CaveBlocks.caveLilyPad, 0);
				}
			}
		}
		else{
			if (shouldGenFloorAddon() && addonblock == null){
				gen.genStalagmite(world, x, y, z, depth);
			}
		}
	}
}
