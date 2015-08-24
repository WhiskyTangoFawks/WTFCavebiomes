
package cavebiomes.blocks;

import java.util.List;
import java.util.Random;

import wtfcore.blocks.IAlphaMaskedBlock;
import wtfcore.blocks.ChildBlockCarryMetadata;
import wtfcore.items.ItemMetadataSubblock;
import wtfcore.proxy.ClientProxy;
import wtfcore.utilities.BlockInfo;
import wtfcore.utilities.BlockSets;
import wtfcore.utilities.UBCblocks;
import wtfcore.utilities.BlockSets.Modifier;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import cavebiomes.CaveBiomes;
import cavebiomes.WTFCaveBiomesConfig;
import cavebiomes.proxy.CBClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMagmaCrust extends ChildBlockCarryMetadata implements IAlphaMaskedBlock{

	public static Block stoneMagmaCrust;
	public static Block igneousMagmaCrust;
	public static Block metamorphicMagmaCrust;
	public ResourceLocation resourceDomain;
	public ResourceLocation textureLocation;
	private int numSubBlocks;

	private IIcon[] textures;

	public BlockMagmaCrust(Block block, int subblocks)
	{
		super(block);
		this.setCreativeTab(CaveBiomes.tabCaveDecorations);
		this.numSubBlocks=subblocks;
		if (WTFCaveBiomesConfig.lavacrustGlow){
			this.setLightLevel(0.2F);// THIS HAS TO BE KEPT AS A FLOAT
		}
	}

	public static Block registerMagmaCrust(Block block, String name, int subblocks){
		Block blockToRegister = new BlockMagmaCrust(block, subblocks).setBlockName(name);
		GameRegistry.registerBlock(blockToRegister, ItemMetadataSubblock.class, name);
		
		BlockSets.blockTransformer.put(new BlockInfo(block, 0, Modifier.stoneMagmaCrust), blockToRegister);
		BlockSets.meltBlocks.add(blockToRegister);
				
		return blockToRegister;
	}

	public static void register(){

		stoneMagmaCrust = registerMagmaCrust(Blocks.stone, "stone_lavacrust", 0);

		if (Loader.isModLoaded("UndergroundBiomes")){
			igneousMagmaCrust = registerMagmaCrust(UBCblocks.IgneousStone, "igneous_lavacrust", 8);
			metamorphicMagmaCrust = registerMagmaCrust(UBCblocks.MetamorphicStone, "metamorphic_lavacrust", 8);
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister){

		String maskType = "lavacrust";

		textures = new IIcon[16];

		if (this.parentBlock == Blocks.stone) {
			String textureName = "stone_lavacrust";
			this.textures[0] = iconRegister.registerIcon(CaveBiomes.modid+":" + textureName);
			ClientProxy.registerBlockOverlay(textureName, "minecraft:cobblestone", maskType, CaveBiomes.alphaMaskDomain, true);

		}

		if (Loader.isModLoaded("UndergroundBiomes") && (parentBlock == UBCblocks.IgneousStone || parentBlock == UBCblocks.MetamorphicStone)){
			for (int metaloop=0; metaloop < 8; metaloop++){
				String textureName = null;
				String stoneType = null;
				if (this.parentBlock == UBCblocks.IgneousStone){
					stoneType = UBCblocks.IgneousStoneList[metaloop];
					textureName = stoneType+"_lavacrust";

				}
				else if (this.parentBlock == UBCblocks.MetamorphicStone){
					stoneType = UBCblocks.MetamorphicStoneList[metaloop];
					textureName = stoneType+"_lavacrust";
				}

				this.textures[metaloop]= iconRegister.registerIcon(CaveBiomes.modid + ":"+ textureName);
				ClientProxy.registerBlockOverlay(textureName, "undergroundbiomes:"+stoneType, maskType, CaveBiomes.alphaMaskDomain, true);
			}

		}

	}

    @Override
	public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
    	return true;
    }
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < numSubBlocks; ++i)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return textures[meta];
	}

    @Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
    {
        return getIcon(side, world.getBlockMetadata(x, y, z));
    }

	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean renderAsNormalBlock(){return false;}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType(){
		return CBClientProxy.MagmaCrustRenderType;
	}
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		world.setBlock(x, y, z, Blocks.flowing_lava, 0, 3);
	}

	@Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        if (!WTFCaveBiomesConfig.lavacrustAnimations){return;}
		int l;

        double d5;
        double d6;
        double d7;

        if (random.nextInt(30) == 0 && world.isAirBlock(x, y-1, z) && WTFCaveBiomesConfig.enableDrippingBlocks)
        {
            d5 = x + random.nextFloat();
            d6 = y - 0.05D;
            d7 = z + random.nextFloat();

            world.spawnParticle("dripLava", d5, d6, d7, 0.0D, 0.0D, 0.0D);

        }


        if (random.nextInt(15) == 0 && world.isAirBlock(x,  y+1,  z))
        {
            d5 = x + random.nextFloat();
            d6 = y + this.maxY;
            d7 = z + random.nextFloat();
            world.spawnParticle("smoke", d5, d6, d7, 0.0D, 0.0D, 0.0D);
            world.playSound(d5, d6, d7, "liquid.lavapop", 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
        }

        if (random.nextInt(200) == 0)
        {
            world.playSound(x, y, z, "liquid.lava", 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
        }

    }
}
