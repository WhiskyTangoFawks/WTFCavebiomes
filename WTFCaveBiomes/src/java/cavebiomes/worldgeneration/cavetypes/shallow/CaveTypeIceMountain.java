package cavebiomes.worldgeneration.cavetypes.shallow;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.world.World;
import wtfcore.utilities.BlockSets;
import cavebiomes.worldgeneration.CaveType;
import cavebiomes.worldgeneration.dungeontypes.DungeonSet;
import cavebiomes.worldgeneration.dungeontypes.DungeonType;

public class CaveTypeIceMountain extends CaveType{

	public CaveTypeIceMountain(String name, int cavedepth,
			DungeonSet coldSet) {
		super(name, cavedepth, coldSet);
	}

	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		if (random.nextBoolean()){
			gen.genStalactite(world, x, y, z, depth);
		}
		else {
			gen.genIcicle(world, x, y, z);
		}
	}

	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{

		if (random.nextInt(4) < 1){
			gen.transformBlock(world, x, y, z, BlockSets.Modifier.cobblestone);
		}
		if (random.nextInt(8) < 1){
			gen.setStoneAddon(world, x, y, z, BlockSets.Modifier.cobblestone);
			gen.freezeBlock(world, x, y+1, z);
		}
		else if (shouldGenFloorAddon()){
			gen.genStalagmite(world, x, y, z, depth);
		}
		else {
			gen.freezeBlock(world, x, y, z);
		}
	}



}
