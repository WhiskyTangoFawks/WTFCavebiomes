package cavebiomes.blocks;

import java.util.HashMap;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.blocks.customplants.Foxfire;
import cavebiomes.blocks.customplants.CinderShroom;
import cavebiomes.blocks.customplants.PlantsCavePlants;
import cavebiomes.items.ItemMoss;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.registry.GameRegistry;

public class CaveBlocks
{
	public static HashMap<Block, BlockSpeleothems[]> speleothemMap = new HashMap<Block, BlockSpeleothems[]>();
	public static HashMap<Block, FrozenBlock[]> frozenspeleothemMap = new HashMap<Block, FrozenBlock[]>();
	public static String[] formationType ={"StalactiteSmall", "StalactiteLargeBase", "StalactiteLargeTip", "LargeColumn", "StalagmiteSmall", "StalagmiteLargeBase", "StalagmiteLargeTip"};

	public static Block IcePatch;
	public static Block GlowstoneStalactite;
	public static Block Roots;
	public static Block CaveOrchid;
	public static Block caveLilyPad;
	public static Block PlantMoss;
	public static Block frozenRoots;

	//Method to call registry of Blocks
	public static void BlockRegister()
	{
		BlockIcicle.register();
		BlockSpeleothems.register();
		UBCSand.register();
		BlockMagmaCrust.register();
		CinderShroom.register();
		DrippingBlock.register();
		if (WTFCaveBiomesConfig.enableMossyStone){
			UBCMossyStone.register();
		}
		BlockRoots.registerRoots();
		Foxfire.register();
		ItemMoss.register();
		caveLilyPad = Blocks.waterlily;

		IcePatch = new IcePatch().setBlockName("ice_patch");
		GameRegistry.registerBlock(IcePatch, "ice_patch");

		String[] names={"glowstone"};
		GlowstoneStalactite = new BlockSpeleothems(Blocks.glowstone, 0, names, "minecraft").setBlockName("glowstone_stalactite_small").setLightLevel(0.7F);
		GameRegistry.registerBlock(GlowstoneStalactite, "glowstone_stalactite_small");


		CaveOrchid = new PlantsCavePlants().setBlockName("cave_orchid");
		GameRegistry.registerBlock(CaveOrchid, "cave_orchid");
	}

	public static Block[] getSpeleothemSet(Block block, Block modifier){
		if (modifier == null){
			return speleothemMap.get(block);
		}
		else {//if (modifier == Blocks.ice){
			return frozenspeleothemMap.get(block);
		}
	}



}
