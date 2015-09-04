package cavebiomes.entities;


import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;


public class SpawnerBlockEntity extends TileEntity{

	private Entity mob;
	int activatingRangeFromPlayer = 16;

	protected SpawnerBlockEntity() {
		//this.mob = entity;
	}


	public boolean anyPlayerInRange()
	{
		return this.worldObj.getClosestPlayer((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, activatingRangeFromPlayer) != null;
	}
	
	public void setMob(Entity entity){
		mob = entity;
	}

	@Override
	public void updateEntity()
	{
		if(this.anyPlayerInRange()){
			if (!this.worldObj.isRemote && mob != null)
			{
				mob.setLocationAndAngles(this.xCoord, this.yCoord, this.zCoord,this.worldObj.rand.nextFloat() * 360.0F, 0.0F);
				worldObj.spawnEntityInWorld(mob);
				worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
			}
		}
	}


}
