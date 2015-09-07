package cavebiomes.blocks;

import net.minecraft.block.Block;
import wtfcore.utilities.BlockInfo;
import wtfcore.utilities.BlockSets;

public class StoneRegister {

	Block stone;
	Block cobblestone;
	String unlocalisedName;
	String[] stoneNames;
	String domain;

	public boolean speleothems = true;
	public boolean lavacrust = true;
	public boolean mossyStone = true;
	public boolean mossyCobble = true;
	public boolean drippingWater = true;
	public boolean drippingLava = true;
	
	public boolean cobbleFall = true;
	public boolean genReplaceable = true;

	public StoneRegister(Block stone, Block cobblestone, String unlocalisedName, String[] stoneNames, String domain){
		this.stone = stone;
		this.cobblestone = cobblestone;
		this.unlocalisedName = unlocalisedName;
		this.stoneNames = stoneNames;
		this.domain = domain;
	}

	public StoneRegister(Block stone, Block cobblestone, String unlocalisedName, String stoneName, String domain){
		this.stone = stone;
		this.cobblestone = cobblestone;
		this.unlocalisedName = unlocalisedName;
		this.stoneNames = new String[1];
		this.stoneNames[0] = stoneName;
		this.domain = domain;
	}

	public void register(){
		//BlockTransformer
		BlockSets.blockTransformer.put(new BlockInfo(stone, 0, BlockSets.Modifier.cobblestone), cobblestone);
		//Replaceable during chunk generation
		BlockSets.ReplaceHashset.add(stone);
		//Falling hashset

		//Mossy Stone
		if (mossyStone){
			String mossyStoneName = unlocalisedName;
			if (!mossyStoneName.endsWith("stone")){
				mossyStoneName=mossyStoneName+"_stone";
			}
			MossyStone.registerMossyBlock(stone, mossyStoneName, stoneNames, domain);
		}
		//Mossy Cobblestone
		if (mossyCobble){	
			String mossyCobbleName = unlocalisedName;
			if (!mossyCobbleName.endsWith("cobblestone")){
				mossyCobbleName=mossyCobbleName+"_cobblestone";
			}
			MossyStone.registerMossyCobble(cobblestone, mossyCobbleName, stoneNames, domain);
		}
		//Lava crust
		if (lavacrust){
			BlockMagmaCrust.registerMagmaCrust(cobblestone, unlocalisedName, stoneNames, domain);
		}
		//Speleothems, and frozen speleothems
		if (speleothems){
			BlockSpeleothems.registerSpeleothemSet(stone, unlocalisedName, stoneNames, domain);
		}
		//The boolean for these is part of the dripping class- it's passed along through the register
		DrippingBlock.registerDrippingStoneSet(stone, unlocalisedName, stoneNames.length, drippingWater, drippingLava);

	}
}

