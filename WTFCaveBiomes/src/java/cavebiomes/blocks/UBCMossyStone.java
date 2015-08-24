package cavebiomes.blocks;


import java.util.ArrayList;
import java.util.List;
import wtfcore.WTFCore;
import wtfcore.blocks.IAlphaMaskedBlock;
import wtfcore.blocks.ChildBlockCarryMetadata;
import wtfcore.items.ItemMetadataSubblock;
import wtfcore.proxy.ClientProxy;
import wtfcore.utilities.BlockInfo;
import wtfcore.utilities.BlockSets;
import wtfcore.utilities.UBCblocks;
import cavebiomes.CaveBiomes;
import cavebiomes.items.ItemMoss;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class UBCMossyStone extends ChildBlockCarryMetadata implements IAlphaMaskedBlock
{

	protected String[] textureNames;
	protected String[] parentLocations;

	public static Block MossyStone;
	public static Block MossyIgneous;
	public static Block MossyMetamorphic;
	public static Block MossySedimentary;
	public static Block MossyDirt;

	public static Block MossyIgneousCobble;
	public static Block MossyMetamorphicCobble;
	public static Block blockToRegister;
	int parentMeta;

	public static String[] vanillaStone = {"stone"};
	public static String[] vanillaDirt = {"dirt", "coarse_dirt", "podzol"};


	static Block childBlockToRegister;
	private IIcon[] textures;

	protected UBCMossyStone(Block block, String[] stoneNames, String domain) {
		super(block);
		this.loadTextureStrings(stoneNames, domain);
		this.setCreativeTab(CaveBiomes.tabCaveDecorations);
	}

	public void loadTextureStrings(String[] stoneNames, String domain){
		textureNames = new String [stoneNames.length];
		parentLocations = new String [stoneNames.length];

		for (int loop = 0; loop < stoneNames.length; loop++){
			textureNames[loop] = "mossy_"+stoneNames[loop];
			parentLocations[loop] = domain+":"+stoneNames[loop];
		}
	}

	public static Block registerMossyBlock(Block block, String stoneGeoType, String[] stoneNames, String domain){

		String name = "mossy_"+stoneGeoType;
		blockToRegister = new UBCMossyStone(block, stoneNames, domain).setBlockName(name);
		GameRegistry.registerBlock(blockToRegister, ItemMetadataSubblock.class, name);
		BlockSets.blockTransformer.put(new BlockInfo(block, 0, BlockSets.Modifier.MossyStone), blockToRegister);

		return blockToRegister;
	}
	public static Block registerMossyCobble(Block block, String stoneGeoType, String[] stoneNames, String domain){

		String name = "mossy_"+stoneGeoType;
		blockToRegister = new UBCMossyStone(block, stoneNames, domain).setBlockName(name);
		GameRegistry.registerBlock(blockToRegister, ItemMetadataSubblock.class, name);
		BlockSets.blockTransformer.put(new BlockInfo(block, 0, BlockSets.Modifier.mossy_cobblestone), blockToRegister);

		return blockToRegister;
	}


	public static void register(){

		MossyDirt = registerMossyBlock(Blocks.dirt, "dirt", vanillaDirt, "minecraft");
		MossyStone = registerMossyBlock(Blocks.stone, "stone", vanillaStone, "minecraft");


		if (Loader.isModLoaded("UndergroundBiomes")){
			MossyIgneousCobble = registerMossyCobble(UBCblocks.IgneousCobblestone, "igneous_cobblestone", UBCblocks.IgneousCobblestoneList, "undergroundbiomes");
			MossyMetamorphicCobble = registerMossyCobble(UBCblocks.MetamorphicCobblestone, "metamorphic_cobblestone", UBCblocks.MetamorphicCobblestoneList, "undergroundbiomes");
			MossyIgneous = registerMossyBlock(UBCblocks.IgneousStone, "igneous_stone", UBCblocks.IgneousStoneList, "undergroundbiomes");
			MossyMetamorphic = registerMossyBlock(UBCblocks.MetamorphicStone, "metamorphic_stone", UBCblocks.MetamorphicStoneList, "undergroundbiomes");
			MossySedimentary = registerMossyBlock(UBCblocks.SedimentaryStone, "sedimentary_stone", UBCblocks.SedimentaryStoneList, "undergroundbiomes");
		}
		
		BlockSets.addStoneBlock(UBCMossyStone.MossyStone, Blocks.mossy_cobblestone);
		BlockSets.addStoneBlock(UBCMossyStone.MossyIgneous, UBCMossyStone.MossyIgneousCobble, 8);
		BlockSets.addStoneBlock(UBCMossyStone.MossyMetamorphic, UBCMossyStone.MossyMetamorphicCobble, 8);
		BlockSets.addStoneBlock(UBCMossyStone.MossySedimentary, UBCSand.sedimentarySand);

		BlockSets.addFallingBlock(UBCMossyStone.MossyIgneousCobble, 1, 8);
		BlockSets.addFallingBlock(UBCMossyStone.MossyMetamorphicCobble, 1, 8);
		BlockSets.addFallingBlock(UBCMossyStone.MossyDirt, 1);
		
	}

    @Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
    	return true;
    }
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return textures[meta];
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister){
		textures = new IIcon[16];
		for (int loop = 0; loop < textureNames.length; loop++){
			textures[loop] = iconRegister.registerIcon(CaveBiomes.modid +":"+ textureNames[loop]);
			ClientProxy.registerBlockOverlay(textureNames[loop], parentLocations[loop], "moss_overlay", CaveBiomes.overlayDomain, false);
		}
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < textureNames.length; ++i)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

    @Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta)
    {
        player.addStat(StatList.mineBlockStatArray[getIdFromBlock(this)], 1);
        player.addExhaustion(0.025F);

        if (this.canSilkHarvest(world, player, x, y, z, meta) && EnchantmentHelper.getSilkTouchModifier(player))
        {
            ArrayList<ItemStack> items = new ArrayList<ItemStack>();
            ItemStack itemstack = this.createStackedBlock(meta);

            if (itemstack != null)
            {
                items.add(itemstack);
            }

            ForgeEventFactory.fireBlockHarvesting(items, world, this, x, y, z, meta, 0, 1.0f, true, player);
            for (ItemStack is : items)
            {
                this.dropBlockAsItem(world, x, y, z, is);
            }
        }
        else
        {
        	  float f = 0.7F;
        	double d0 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
            double d1 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
            double d2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
        	EntityItem mossdrop = new EntityItem(world, x+d0, y+d1, z+d2, new ItemStack(ItemMoss.moss));
        	world.spawnEntityInWorld(mossdrop);
        	WTFCore.log.info("Dropping moss");
        	parentBlock.harvestBlock(world, player, x, y, z, meta);;
        }
    }

}
