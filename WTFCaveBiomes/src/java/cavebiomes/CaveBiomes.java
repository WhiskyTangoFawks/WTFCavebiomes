package cavebiomes;

import java.util.Iterator;

import cavebiomes.blocks.CaveBlocks;
import cavebiomes.entities.Entities;
import cavebiomes.proxy.CommonProxy;
import cavebiomes.worldgeneration.CaveBiomesWorldScanner;
import cavebiomes.worldgeneration.dungeontypes.DungeonTypeRegister;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import wtfcore.WTFCore;
import wtfcore.WTFCoreConfig;
import wtfcore.WorldGenListener;



@Mod(modid = CaveBiomes.modid, name = "WhiskyTangoFox's Cave Biomes", version = "1.34", dependencies = "after:UndergroundBiomes;required-after:WTFCore@[1.6,)")


public class CaveBiomes {
	public static  final String modid = WTFCore.CaveBiomes;

	@Instance(modid)
	public static CaveBiomes instance;

	@SidedProxy(clientSide="cavebiomes.proxy.CBClientProxy", serverSide="cavebiomes.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static String alphaMaskDomain = "cavebiomes:textures/blocks/alphamasks/";
	public static String overlayDomain =   "cavebiomes:textures/blocks/overlays/";



	public static CreativeTabs tabCaveDecorations = new CreativeTabs("CaveDecorations")
	{

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(CaveBlocks.GlowstoneStalactite);
		}

	};

	@EventHandler
	public void PreInit(FMLPreInitializationEvent preEvent)
	{
		WTFCaveBiomesConfig.customConfig();
		Entities.RegisterEntityList();
		proxy.registerRenderers();
	}
	@EventHandler public void load(FMLInitializationEvent event)
	{

		CaveBlocks.BlockRegister();
		MinecraftForge.EVENT_BUS.register(new EventListener());
		//I was using this to generate magma crust as an ore, but I've now disabled it
		//GameRegistry.registerWorldGenerator(new WorldGeneration(), 0);
		DungeonTypeRegister.AddDungeonTypes();
		//recipes

	}
	@EventHandler
	public void PostInit(FMLPostInitializationEvent postEvent){

		//add a config option to allow users to place a the thing in another dimension
		Iterator<Integer> iterator = WTFCoreConfig.overworlds.iterator();
		while (iterator.hasNext()){
			int dimensionID = iterator.next();
			WorldGenListener.GetScanner.put(dimensionID, new CaveBiomesWorldScanner());
			WTFCore.log.info("Adding CaveBiomes Overworld scanner for dimension " + dimensionID);
		}
		//Once I do a nether decorator, it goes here
	}




}
