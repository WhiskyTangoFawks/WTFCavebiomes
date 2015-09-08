package cavebiomes.utilities;

import cavebiomes.blocks.BlockMagmaCrust;
import cavebiomes.blocks.BlockSpeleothems;
import cavebiomes.blocks.DrippingBlock;
import cavebiomes.blocks.MossyStone;
import net.minecraft.block.Block;
import wtfcore.utilities.BlockInfo;
import wtfcore.utilities.BlockSets;

public class StoneRegister {

	private Block stone;
	private Block cobblestone;
	private String unlocalisedName;
	private String[] stoneTextureNames;
	private String[] cobbleTextureNames;
	private String domain;

	/**
	 * generates a set of stalactites and stalagmites based on the stone block
	 */
	public boolean speleothems = true;
	
	/**
	 * generates a lava crust block based on the stone block
	 */
	public boolean lavacrust = true;
	
	/**
	 * generates a mossy versions of the stone
	 */
	public boolean mossyStone = true;
	
	/**
	 * generates a mossy versions of the cobblestone
	 */
	public boolean mossyCobble = true;
	
	/**
	 * generates a dripping water stone and rainstone
	 */
	public boolean drippingWater = true;
	
	/**
	 * generates dripping lava and raining lava stone
	 */
	public boolean drippingLava = true;
	
	/**
	 * adds cobblestone to the falling blocks list- requires WTFTweaks to be present, otherwise it gets ignored
	 */
	public boolean cobbleFall = true;
	
	/**
	 * allows the stone block to be replaced during world generation by cave biomes 
	 */
	public boolean genReplaceable = true;

	public StoneRegister(Block stone, Block cobblestone, String unlocalisedName, String[] stoneTextureNames, String[] cobbleTextureNames, String domain){
		this.stone = stone;
		this.cobblestone = cobblestone;
		this.unlocalisedName = unlocalisedName;
		this.stoneTextureNames = stoneTextureNames;
		this.cobbleTextureNames = cobbleTextureNames;
		this.domain = domain;
	}
	public StoneRegister(Block stone, Block cobblestone, String unlocalisedName, String stoneTextureName, String cobbleTextureName, String domain){
		this.stone = stone;
		this.cobblestone = cobblestone;
		this.unlocalisedName = unlocalisedName;
		this.stoneTextureNames = new String[1];
		this.stoneTextureNames[0] = stoneTextureName;
		this.cobbleTextureNames = new String[1];
		this.cobbleTextureNames[0] = cobbleTextureName;
		this.domain = domain;
	}

	public void register(){
		
		
		//BlockTransformer
		BlockSets.blockTransformer.put(new BlockInfo(stone, 0, BlockSets.Modifier.cobblestone), cobblestone);
		
		//Replaceable during chunk generation
		if (genReplaceable){
			BlockSets.ReplaceHashset.add(stone);
		}
		BlockSets.surfaceBlocks.add(stone);
		BlockSets.surfaceBlocks.add(cobblestone);
		
		//Falling hashset, requires WTFTweaks to be present to function
		if (cobbleFall){
			BlockSets.addDefaultFallingBlock(cobblestone, 1);
		}

		//Mossy Stone
		if (mossyStone){
			String mossyStoneName = unlocalisedName;
			if (!mossyStoneName.endsWith("stone")){
				mossyStoneName=mossyStoneName+"_stone";
			}
			MossyStone.registerMossyBlock(stone, mossyStoneName, stoneTextureNames, domain);
		}
		//Mossy Cobblestone
		if (mossyCobble){	
			String mossyCobbleName = unlocalisedName;
			if (!mossyCobbleName.endsWith("cobblestone")){
				mossyCobbleName=mossyCobbleName+"_cobblestone";
			}
			MossyStone.registerMossyCobble(cobblestone, mossyCobbleName, cobbleTextureNames, domain);
		}
		//Lava crust
		if (lavacrust){
			BlockMagmaCrust.registerMagmaCrust(cobblestone, unlocalisedName, cobbleTextureNames, domain);
		}
		//Speleothems, and frozen speleothems
		if (speleothems){
			BlockSpeleothems.registerSpeleothemSet(stone, unlocalisedName, stoneTextureNames, domain);
		}
		//The boolean for these is part of the dripping class- it's passed along through the register
		DrippingBlock.registerDrippingStoneSet(stone, unlocalisedName, stoneTextureNames.length, drippingWater, drippingLava);

	}
}

