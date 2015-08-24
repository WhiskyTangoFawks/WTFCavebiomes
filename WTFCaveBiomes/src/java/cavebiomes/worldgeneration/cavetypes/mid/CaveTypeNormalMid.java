package cavebiomes.worldgeneration.cavetypes.mid;

import java.util.ArrayList;
import java.util.Random;

import cavebiomes.worldgeneration.CaveType;
import cavebiomes.worldgeneration.DungeonSet;
import cavebiomes.worldgeneration.DungeonType;
import net.minecraft.world.World;

public class CaveTypeNormalMid extends CaveType{

	public CaveTypeNormalMid(String name, int cavedepth, DungeonSet defaultSet) {
		super(name, cavedepth, defaultSet);
	}


	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		gen.genStalactite(world, x, y, z, depth);
	}


	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		if (shouldGenFloorAddon()){
			gen.genStalagmite(world, x, y, z, depth);
		}
	}


}
