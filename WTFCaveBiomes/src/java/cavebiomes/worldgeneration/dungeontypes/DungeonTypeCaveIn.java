package cavebiomes.worldgeneration.dungeontypes;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import wtfcore.utilities.BlockInfo;
import wtfcore.utilities.BlockSets;
import cavebiomes.worldgeneration.DungeonType;

public class DungeonTypeCaveIn extends DungeonType {

	public DungeonTypeCaveIn(String name) {
		super(name);
	}

		Block cobblestone;

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		if (cobblestone == null){
			cobblestone = BlockSets.floorBlock.get(new BlockInfo(world.getBlock(x,y,z), world.getBlockMetadata(x,y,z), BlockSets.Modifier.cobblestone));
		}
	}

	@Override
	public void generateFill(World world, Random random, int x, int y, int z)
	{
		if (cobblestone!=null){
			gen.genFloatingStone(world, x, y, z, cobblestone);
		}
	}

}
