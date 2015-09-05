package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;

import cavebiomes.entities.Entities;
import cavebiomes.worldgeneration.dungeontypes.DungeonType;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class DunegonTypeSkeletonMage extends DungeonType{

	public DunegonTypeSkeletonMage() {
		super("SkeletonMage");
	}
	
	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x,  y,  z, Blocks.obsidian, 0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x,  y,  z, Blocks.obsidian, 0);
		if (random.nextInt(5) == 0 && spawncounter < 3){
			world.setBlock(x, y+2, z, Entities.spawners.get("SkeletonMage"));
			spawncounter++;
		}
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x,  y,  z, Blocks.obsidian, 0);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.enchanting_table, 0);
	}
}
