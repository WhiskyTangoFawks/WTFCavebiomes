package cavebiomes.blocks;

import java.util.HashMap;
import cavebiomes.blocks.customplants.Foxfire;
import cavebiomes.blocks.customplants.LavaVine;
import cavebiomes.blocks.customplants.CinderShroom;
import cavebiomes.blocks.customplants.PlantsCavePlants;
import cavebiomes.items.ItemMoss;
import cavebiomes.utilities.StoneRegister;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import wtfcore.InterModBlocks;
import wtfcore.WTFCore;
import wtfcore.api.BlockSets;
import wtfcore.utilities.UBCblocks;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class CaveBlocks
{
	public static HashMap<Block, BlockSpeleothems[]> speleothemMap = new HashMap<Block, BlockSpeleothems[]>();
	public static HashMap<Block, FrozenBlock[]> frozenspeleothemMap = new HashMap<Block, FrozenBlock[]>();
	public static String[] formationType ={"StalactiteSmall", "StalactiteLargeBase", "StalactiteLargeTip", "LargeColumn", "StalagmiteSmall", "StalagmiteLargeBase", "StalagmiteLargeTip"};

	public static String[] vanillaDirt = {"dirt", "coarse_dirt", "dirt_podzol_top"};
	public static String[] vanillaRedstone = {"redstone_block"};
	
	public static Block IcePatch;
	public static Block GlowstoneStalactite;
	public static Block Roots;
	public static Block CaveOrchid;
	public static Block PlantMoss;
	public static Block frozenRoots;
	public static Block MossyDirt;
	public static Block lavaVine;

	//Method to call registry of Blocks
	public static void BlockRegister()
	{
		BlockIcicle.register();	
		UBCSand.register();
		CinderShroom.register();
		BlockRoots.registerRoots();
		Foxfire.register();
		ItemMoss.register();
		
		lavaVine = new LavaVine().setBlockName("lava_vine");
		GameRegistry.registerBlock(lavaVine, "lava_vine");
	
		IcePatch = new IcePatch().setBlockName("ice_patch");
		GameRegistry.registerBlock(IcePatch, "ice_patch");

		String[] names={"glowstone"};
		GlowstoneStalactite = new BlockSpeleothems(Blocks.glowstone, 0, names, "minecraft").setBlockName("glowstone_stalactite_small").setLightLevel(0.7F);
		GameRegistry.registerBlock(GlowstoneStalactite, "glowstone_stalactite_small");


		CaveOrchid = new PlantsCavePlants().setBlockName("cave_orchid");
		GameRegistry.registerBlock(CaveOrchid, "cave_orchid");
		
		MossyDirt = MossyStone.registerMossyBlock(Blocks.dirt, "dirt", vanillaDirt, "minecraft");
		
		RedstoneSpeleothem.registerSpeleothemSet(Blocks.redstone_ore, "redstone_block", vanillaRedstone, "minecraft");
		RedstoneSpeleothem.registerSpeleothemSet(Blocks.lit_redstone_ore, "redstone_block", vanillaRedstone, "minecraft");
		
		DrippingBlock.registerDrippingBlock(Blocks.dirt, 0, BlockSets.Modifier.waterDrippingStone,1, "dripping_water_dirt" );
		
		StoneRegister stone = getStoneRegister(Blocks.stone, Blocks.cobblestone, "stone", "stone", "cobblestone", "minecraft");
		stone.mossyCobble = false;
		stone.register();
		
		StoneRegister sand = getStoneRegister(Blocks.sandstone, Blocks.sand, "sandstone", "sand", "sand", "minecraft");
		sand.mossyStone=false;
		sand.mossyCobble=false;
		sand.lavacrust=false;
		sand.drippingWater=false;
		sand.drippingLava=false;
		sand.register();
		
		if (Loader.isModLoaded("UndergroundBiomes")){
			
			StoneRegister igneous = getStoneRegister(UBCblocks.IgneousStone, UBCblocks.IgneousCobblestone, "igneous", UBCblocks.IgneousStoneList, UBCblocks.IgneousCobblestoneList, "undergroundbiomes");
			igneous.register();
			
			StoneRegister metamorphic = getStoneRegister(UBCblocks.MetamorphicStone, UBCblocks.MetamorphicCobblestone, "metamorphic", UBCblocks.MetamorphicStoneList, UBCblocks.MetamorphicCobblestoneList, "undergroundbiomes");
			metamorphic.register();
			
			StoneRegister sedimentary = getStoneRegister(UBCblocks.SedimentaryStone, UBCSand.sedimentarySand, "sedimentary", UBCblocks.SedimentaryStoneList, UBCSand.SedimentarySandTextures, "undergroundbiomes");
			sedimentary.mossyCobble = false;
			sedimentary.lavacrust = false;
			sedimentary.drippingLava=false;
			sedimentary.register();
		}
		
		
	}
	
	public static StoneRegister getStoneRegister(Block stone, Block cobblestone, String unlocalisedName, String[] stoneNames, String[] cobbleNames, String domain){
		return new StoneRegister(stone, cobblestone, unlocalisedName, stoneNames, cobbleNames, domain);
	}
	public static StoneRegister getStoneRegister(Block stone, Block cobblestone, String unlocalisedName, String stoneNames, String cobbleNames, String domain){
		return new StoneRegister(stone, cobblestone, unlocalisedName, stoneNames, cobbleNames, domain);
	}
	
	
	public static Block[] getSpeleothemSet(Block block, Block modifier){
		if (modifier == Blocks.redstone_ore) {
			WTFCore.log.info("Returning redstone speleothems");
			return InterModBlocks.unlitRedstoneSpeleothems;
		}
		if (modifier == null){
			return speleothemMap.get(block);
		}
		else {//if (modifier == Blocks.ice){
			return frozenspeleothemMap.get(block);
		}
	}



}
