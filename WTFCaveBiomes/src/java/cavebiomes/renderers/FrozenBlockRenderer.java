package cavebiomes.renderers;


import cavebiomes.blocks.FrozenBlock;
import cavebiomes.proxy.CBClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class FrozenBlockRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {

		FrozenBlock fblock = (FrozenBlock) block;

		if(CBClientProxy.renderPass == 0){
			if (fblock.innerBlock.getRenderType() == 1){
				renderer.renderCrossedSquares(fblock.innerBlock,  x,  y, z);
			}
		}
		else{
			renderer.renderStandardBlock(block, x, y, z);
		}
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return RenderRegisterer.FrozenBlockRenderType;
	}

}
