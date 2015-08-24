package cavebiomes.worldgeneration.cavetypes.mid;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import cavebiomes.worldgeneration.CaveType;
import cavebiomes.worldgeneration.DungeonSet;
import cavebiomes.worldgeneration.DungeonType;

public class CaveTypeMidIce extends CaveType{

	public CaveTypeMidIce(String name, int cavedepth, DungeonSet coldSet) {
		super(name, cavedepth, coldSet);
	}

	@Override
	public void generateCeilingAddons(World world, Random random, int x, int y, int z)
	{
		if (random.nextBoolean()){
			gen.genIcicle(world, x, y, z);
		}
		else {
			gen.genStalactite(world, x, y, z, depth, Blocks.ice);
		}
	}

	@Override
	public void generateFloor(World world, Random random, int x, int y, int z)
	{
		if (shouldGenFloorAddon() && random.nextBoolean() == true){
			gen.genStalagmite(world, x, y, z, depth, Blocks.ice);
		}
		else {
			gen.freezeBlock(world, x, y, z);
		}
	}



}
