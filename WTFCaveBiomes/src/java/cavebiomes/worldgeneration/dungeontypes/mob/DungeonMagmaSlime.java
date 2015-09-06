package cavebiomes.worldgeneration.dungeontypes.mob;

import java.util.Random;

import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.worldgeneration.dungeontypes.DungeonType;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import wtfcore.utilities.BlockSets;

public class DungeonMagmaSlime extends DungeonType{

	public DungeonMagmaSlime() {
		super("MagmaSlime");
	}

	BlockSets.Modifier[] blockarray = {null, BlockSets.Modifier.stoneMagmaCrust, BlockSets.Modifier.cobblestone};

	@Override
	public void generateCeiling(World world, Random rand, int x, int y, int z){
		gen.transformBlock(world, x, y, z, BlockSets.Modifier.LavaRainStone);
	}

	@Override
	public void generateWalls(World world, Random rand, int x, int y, int z){

		gen.transformBlock(world, x, y, z, blockarray[random.nextInt(blockarray.length)]);
	}

	@Override
	public void generateCenter(World world, Random rand, int x, int y, int z, int ceiling, int floor)
	{
		gen.spawnRareVanillaSpawner(world, x, floor+1, z, "LavaSlime");

	}
}