package cavebiomes.worldgeneration.cavetypes.shallow;

import java.util.Random;
import net.minecraft.world.World;
import wtfcore.utilities.BlockSets;
import cavebiomes.worldgeneration.CaveType;
import cavebiomes.worldgeneration.DungeonType;

public class CaveTypeMountain extends CaveType{

	public CaveTypeMountain(String name, int cavedepth,	DungeonType[] dungeonlist) {
		super(name, cavedepth, dungeonlist);
	}

	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		gen.genStalactite(world, x, y, z, depth);
	}

	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		if (random.nextInt(4) < 1){
			gen.transformBlock(world, x, y, z, BlockSets.Modifier.cobblestone);
		}
		if (random.nextInt(8) < 1){
			gen.setStoneAddon(world, x, y, z, BlockSets.Modifier.cobblestone);
		}
		else {
			if (shouldGenFloorAddon()){
				gen.genStalagmite(world, x, y, z, depth);
			}
		}
	}



}