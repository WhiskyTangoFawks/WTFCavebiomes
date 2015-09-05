package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;

import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.worldgeneration.dungeontypes.DungeonType;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class DungeonClassicZombie extends DungeonType{


	public DungeonClassicZombie() {
		super("ClassicZombie");
	}

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.replaceBlock(world, x, y, z, Blocks.mossy_cobblestone, 0);
	}

	@Override
	public void generateFloor(World world, Random rand, int x, int y, int z){
		gen.replaceBlock(world, x, y, z, Blocks.mossy_cobblestone, 0);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){
		gen.replaceBlock(world, x, y, z, Blocks.mossy_cobblestone, 0);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		if (WTFCaveBiomesConfig.EnableMobSpawners){
			while (world.isAirBlock(x,  y-1,  z)){y--;}
			world.setBlock(x, y, z, Blocks.mob_spawner, 0, 2);
			TileEntityMobSpawner spawner = (TileEntityMobSpawner)world.getTileEntity(x, y, z);
			BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
			if (BiomeDictionary.isBiomeOfType(biome, Type.SNOWY)){
				spawner.func_145881_a().setEntityName("ZombieFrozen");
			}
			else {
				spawner.func_145881_a().setEntityName("Zombie");
			}
		}
	}
}