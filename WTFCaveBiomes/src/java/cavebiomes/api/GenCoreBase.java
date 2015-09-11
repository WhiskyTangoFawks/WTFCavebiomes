package cavebiomes.api;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import wtfcore.api.BlockInfo;
import wtfcore.api.BlockSets;



public interface GenCoreBase {


	/**
	 **Used to set a block, based on a modifier and the block at the given location
	 **/
	public void transformBlock(World world, int x, int y, int z, BlockSets.Modifier modifier);
	/**
	 **Used to replace a block.  Checks that the block is replaceable first
	 **/
	public void replaceBlock(World world, int x, int y, int z, Block block, int metadata);
	/**
	 **Used to set a block at y+1, based on a modifier and the block at the given location.  e.g. cobblestone boulders
	 **/
	public void setStoneAddon(World world, int x, int y, int z, BlockSets.Modifier modifier);


	/**
	 **Used to set an ice patch above the given block, checks that it's a normal block first
	 **/
	public void freezeBlock(World world, int x, int y, int z);

	/**
	 **Sets the block to the fluid
	 **/
	public void setFluid(World world, int x, int y, int z, Block fluid);

	/**
	 **Sets the block to the fluid, and transforms the block below it
	 **/
	public void setFluid(World world, int x, int y, int z, Block fluid, BlockSets.Modifier modifier);

	/**
	 **used by the cave in dungeon type to get the stone type for UBC compatibility
	 **/
	public void genFloatingStone(World world, int x, int y, int z, Block modifier);


	public void genStalagmite(World world, int x, int y, int z, int depth);

	public void genStalagmite(World world, int x, int y, int z, int depth, Block modifier);

	public void genStalagmite(World world, int x, int y, int z, Block modifier, int size);

	public void genStalactite(World world, int x, int y, int z, int depth);

	public void genStalactite(World world, int x, int y, int z, int depth, Block modifier);


	public void genStalactite(World world, int x, int y, int z, int depth, Block modifier, int size);

	/**
	 **Generates an icicle hanging from the ceiling
	 **/
	public  void genIcicle (World world, int x, int y, int z);
	/**
	 **Generates vines hanging from the ceiling
	 **/
	public boolean GenVines(World world, int x, int y, int z);
	
	/**
	 * Used to determine if a block is surrounded- any placement of fluids should call this method to prevent flooding
	 */
	public boolean IsBlockSurrounded(World world, int x, int y, int z);

	/**
	 **UBC sensitive version of world.getBlock- is overridden in UBCGen if UBC is installed
	 **/
	public BlockInfo getBlockToReplace(World world, int x, int y, int z);

	/**
	 **Checks if spawners are enabled, and then generates a mob spawner
	 **/
	public void spawnVanillaSpawner(World world, int x, int y, int z, String entityName);
	
	/**
	 **Checks if spawners are enabled, and then generates a mob spawner, setting it's max mobs to 2, and spawn time to long
	 **/
	public void spawnRareVanillaSpawner(World world, int x, int y, int z, String entityName);


	/**
	 **Use instead of world.setBlock, when you don't want it to update adjacent blocks.  non-fluid blocks placed during world generation should use this method,
	 *and fluid blocks should call the setFluid method- which sets the fluid, and schedules an update for outside of generation
	 **/
	public boolean setBlockWithoutNotify(World world, int x, int y, int z, Block block, int metadata);

	/**
	 * This method is only called during world scanning, it's used to check if a block should be replaced, used primarily by the scanner to remove Ores that WTFOres has been told to generate
	 * when the user hasn't set the parent ore's generation to off (e.g. removing emeralds from the world).
	 */
	public void replaceBlockDuringGen(Chunk chunk, Block oldBlock, int x, int y, int z);
	
}
