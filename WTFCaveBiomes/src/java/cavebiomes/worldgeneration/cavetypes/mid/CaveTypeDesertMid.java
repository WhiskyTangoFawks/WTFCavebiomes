package cavebiomes.worldgeneration.cavetypes.mid;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cavebiomes.worldgeneration.CaveType;
import cavebiomes.worldgeneration.DungeonType;

public class CaveTypeDesertMid extends CaveType{

	public CaveTypeDesertMid(String name, int cavedepth,
			DungeonType[] dungeonlist) {
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

		int height = 3*MathHelper.abs_int((MathHelper.abs_int((x+z/2)/3) % 10) -5) + random.nextInt(5)+3;

		if (height > 10 ){
			gen.replaceBlock(world, x, y, z, Blocks.sand, 1);
		}

		else {
			if (shouldGenFloorAddon()){
				gen.genStalagmite(world, x, y, z, depth);
			}
		}

	}

}