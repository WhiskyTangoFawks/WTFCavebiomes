package cavebiomes.entities;


import cavebiomes.CaveBiomes;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;


public class SpawnerBlockEntity extends TileEntity{

	private String entityName;
	int activatingRangeFromPlayer = 16;

	protected SpawnerBlockEntity(String entityName) {
		this.entityName = entityName;
	}


	public boolean anyPlayerInRange()
	{
		return this.worldObj.getClosestPlayer(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D, activatingRangeFromPlayer) != null;
	}

	@Override
	public void updateEntity()
	{
		if(this.anyPlayerInRange()){
			if (!this.worldObj.isRemote)
			{
				//mob.setLocationAndAngles(this.xCoord, this.yCoord, this.zCoord,this.worldObj.rand.nextFloat() * 360.0F, 0.0F);
				//worldObj.spawnEntityInWorld(mob);
				spawnEntity();
				worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
			}
		}
	}

	public void spawnEntity()
	{
		String entityToSpawnNameFull = CaveBiomes.modid+"."+entityName;
		if (EntityList.stringToClassMapping.containsKey(entityToSpawnNameFull))
		{
			EntityLiving entityToSpawn = (EntityLiving) EntityList.createEntityByName(entityToSpawnNameFull, worldObj);
			if 	(entityToSpawn != null){
				entityToSpawn.setLocationAndAngles(this.xCoord+0.5, this.yCoord, this.zCoord+0.5, MathHelper.wrapAngleTo180_float(this.worldObj.rand.nextFloat()*360.0F), 0.0F);
				worldObj.spawnEntityInWorld(entityToSpawn);
				entityToSpawn.onSpawnWithEgg((IEntityLivingData)null);
				entityToSpawn.playLivingSound();
			}
		}
		else
		{
			//DEBUG
			System.out.println("Entity not found "+entityToSpawnNameFull);
		}


		//return entityToSpawn;
	}


}
