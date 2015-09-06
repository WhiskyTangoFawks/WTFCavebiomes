package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import cavebiomes.entities.Entities;
import cavebiomes.worldgeneration.dungeontypes.DungeonType;

public class DungeonTypeMummysTomb extends DungeonType{

	public DungeonTypeMummysTomb() {
		super("MummiesTomb");
	}

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.sandstone,0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.sandstone,0);

		if (random.nextBoolean()){
			world.setBlock(x, y+2, z, Entities.spawners.get("ZombieMummy"));
		}
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.sandstone, 0);
	}

	@Override
	public boolean generateWallStripe(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.sandstone, 1);
		return true;
	}



}
