package cavebiomes.worldgeneration.cavetypes.mid;

import java.util.ArrayList;
import java.util.Random;

import cavebiomes.api.CaveType;
import cavebiomes.api.DungeonSet;
import cavebiomes.api.DungeonType;
import net.minecraft.world.World;

public class CaveTypeNormalMid extends CaveType{

	public final String	name = "DefaultMid";
	public CaveTypeNormalMid(int cavedepth, DungeonSet defaultSet) {
		super(cavedepth, defaultSet);
	}


	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		gen.genStalactite(world, x, y, z, depth);
	}


	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		if (shouldGenFloorAddon(random)){
			gen.genStalagmite(world, x, y, z, depth);
		}
	}


}
