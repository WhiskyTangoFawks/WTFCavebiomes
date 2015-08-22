package cavebiomes.worldgeneration.cavetypes.shallow;

import java.util.ArrayList;
import java.util.Random;

import cavebiomes.worldgeneration.CaveType;
import cavebiomes.worldgeneration.DungeonType;
import net.minecraft.world.World;

public class CaveTypeNormalShallow extends CaveType{

	public CaveTypeNormalShallow(String name, int cavedepth, ArrayList<DungeonType> defaultlist) {
		super(name, cavedepth, defaultlist);
	}

	protected float	ceilingAddonWeight	= 0.3F;
	protected float	floorAddonWeight	= 0.2F;



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
