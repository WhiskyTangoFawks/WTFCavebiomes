package cavebiomes.worldgeneration.cavetypes.shallow;

import java.util.Random;

import cavebiomes.worldgeneration.CaveType;
import cavebiomes.worldgeneration.DungeonType;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CaveTypeSandy extends CaveType{

	public CaveTypeSandy(String name, int cavedepth, DungeonType[] dungeonlist) {
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
		int height = MathHelper.abs_int((MathHelper.abs_int(x+z/3) % 10) -5)/2 + (random.nextInt(3)-2);

		if (height < 0 ){
			if (shouldGenFloorAddon()){
				gen.genStalagmite(world, x, y, z, depth);
			}
		}
		else {
			gen.replaceBlock(world, x, y, z, Blocks.sand, 0);
		}
	}




}
