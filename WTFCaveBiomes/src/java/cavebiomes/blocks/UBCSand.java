package cavebiomes.blocks;

import java.util.List;

import wtfcore.items.ItemMetadataSubblock;
import wtfcore.utilities.BlockInfo;
import wtfcore.utilities.BlockSets;
import wtfcore.utilities.UBCblocks;
import cavebiomes.CaveBiomes;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGravel;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class UBCSand extends BlockGravel{

	private Block parentBlock;
	private int parentMeta;
	private IIcon[] textures;
	public static Block sedimentarySand;


	protected void UBCsand(Block block, int meta) {
		this.parentMeta = meta;
		this.parentBlock = block;
		this.setCreativeTab(CaveBiomes.tabCaveDecorations);
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	public static void register(){

		if (Loader.isModLoaded("UndergroundBiomes")){
			sedimentarySand =  new UBCSand().setBlockName("sedimentarySand");
			GameRegistry.registerBlock(sedimentarySand, ItemMetadataSubblock.class, "sedimentarySand");
			
			BlockSets.addStoneBlock(UBCblocks.SedimentaryStone, UBCSand.sedimentarySand, 8);
			BlockSets.floorBlock.put(new BlockInfo(UBCblocks.SedimentaryStone, 0, BlockSets.Modifier.cobblestone), UBCSand.sedimentarySand);
			
			//recipes
			for (int loop = 0; loop < 8; loop++){
				GameRegistry.addShapelessRecipe(new ItemStack(UBCblocks.SedimentaryStone, 4, loop), new Object[] {new ItemStack(UBCSand.sedimentarySand, 1, loop), new ItemStack(UBCSand.sedimentarySand, 1, loop), new ItemStack(UBCSand.sedimentarySand, 1, loop), new ItemStack(UBCSand.sedimentarySand, 1, loop)});
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < 8; ++i)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister){

		textures = new IIcon[16];

		for (int metaloop=0; metaloop < 8; metaloop++){
			this.textures[metaloop]= iconRegister.registerIcon(CaveBiomes.modid + ":" + UBCblocks.SedimentaryStoneList[metaloop]+"_sand");
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return textures[meta];
	}
}
