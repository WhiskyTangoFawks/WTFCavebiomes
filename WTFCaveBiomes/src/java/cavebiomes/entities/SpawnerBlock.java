package cavebiomes.entities;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class SpawnerBlock extends Block implements ITileEntityProvider{

	protected Entity mob;

	protected SpawnerBlock(Entity entity) {
		super(Material.rock);
		this.mob = entity;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new SpawnerBlockEntity(mob);
	}

}
