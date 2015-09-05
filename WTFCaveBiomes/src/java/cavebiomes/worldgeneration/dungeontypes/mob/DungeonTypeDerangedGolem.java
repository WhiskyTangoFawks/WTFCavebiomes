package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;

import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.entities.Entities;
import cavebiomes.worldgeneration.dungeontypes.DungeonType;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class DungeonTypeDerangedGolem extends DungeonType {

	public DungeonTypeDerangedGolem() {
		super("DerangedGolem");
	}
	
	
	
	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick,2);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick,2);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.setBlockWithoutNotify(world, x, y, z, Blocks.stonebrick, 1);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		
		if (WTFCaveBiomesConfig.EnableMobSpawners){
			gen.setBlockWithoutNotify(world, x, y, z, Entities.spawners.get("DerangedGolem"), 0);
		}
	}

	@Override
	public boolean canSpawnHere(World world, int x, int y, int z, int ceiling, int floor) {
		return (ceiling - floor > 3);
	}

}
