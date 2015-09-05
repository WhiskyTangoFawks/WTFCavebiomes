package cavebiomes.entities;

import cavebiomes.CaveBiomes;
import net.minecraft.block.BlockAir;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SpawnerBlock extends BlockAir implements ITileEntityProvider{

	public String entityName;
	protected SpawnerBlock(String entityName) {
		super();
		this.entityName = entityName;
		this.setCreativeTab(CaveBiomes.tabCaveDecorations);

	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) {
		 
		//spawner.setMob();
		return new SpawnerBlockEntity(entityName); 
		
	}

}
