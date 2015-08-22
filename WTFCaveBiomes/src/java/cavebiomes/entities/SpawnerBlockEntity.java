package cavebiomes.entities;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public class SpawnerBlockEntity extends TileEntity{

	private Entity mob;
	int activatingRangeFromPlayer = 16;

	protected SpawnerBlockEntity(Entity entity) {
		this.mob = entity;
	}


	@Override
	public void updateEntity() {}

    public boolean isActivated()
    {
        return worldObj.getClosestPlayer(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D, this.activatingRangeFromPlayer) != null;
    }

}
