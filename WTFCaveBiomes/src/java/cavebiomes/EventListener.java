package cavebiomes;

import java.util.Random;

import cavebiomes.blocks.UBCSand;
import cavebiomes.entities.skeleton.SkeletonIce;
import cavebiomes.entities.skeleton.SkeletonKnight;
import cavebiomes.entities.skeleton.SkeletonLava;
import cavebiomes.entities.skeleton.SkeletonMage;
import cavebiomes.entities.zombie.ZombieFrozen;
import cavebiomes.entities.zombie.ZombieMummy;
import cavebiomes.entities.zombie.ZombiePharoh;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import wtfcore.utilities.UBCblocks;


public class EventListener {


	Random random = new Random();
	public static EntityPlayer thePlayer;

	@SubscribeEvent
	public void getPlayer (PlayerInteractEvent event){
		if (WTFCaveBiomesConfig.logDungeons){
			thePlayer = event.entityPlayer;
		}
	}

	@SubscribeEvent
	public void SpawnReplacer (LivingSpawnEvent event)
	{
		if (WTFCaveBiomesConfig.customMobs && !event.world.isRemote){

			//Zombies
			if (event.entityLiving instanceof EntityZombie){
				EntityZombie oldentity = (EntityZombie) event.entityLiving;
				BiomeGenBase biome = event.world.getBiomeGenForCoords(MathHelper.floor_float(event.x), MathHelper.floor_float(event.z));
				if (oldentity.getCanSpawnHere())
				{
					if (BiomeDictionary.isBiomeOfType(biome, Type.SANDY)){
						if (event.y < 32 && random.nextInt(50)==0){
							replaceEntity(oldentity, new ZombiePharoh(event.world));
						} else {
							replaceEntity(oldentity, new ZombieMummy(event.world));
						}
					}
					else if (BiomeDictionary.isBiomeOfType(biome, Type.SNOWY)){
						replaceEntity(event.entity, new ZombieFrozen(event.world));
					}
				}
			}
			//Skeletons
			if (event.entityLiving instanceof EntitySkeleton){
				EntitySkeleton oldentity = (EntitySkeleton) event.entityLiving;


				BiomeGenBase biome = event.world.getBiomeGenForCoords(MathHelper.floor_float(event.x), MathHelper.floor_float(event.z));
				if (oldentity.getCanSpawnHere()){

					if (event.y < 32  && random.nextInt(50) == 0){
						replaceEntity(oldentity, new SkeletonMage(event.world));
					}
					if (event.y < 32  && random.nextInt(25) == 0){
						replaceEntity(oldentity, new SkeletonKnight(event.world));
					}


					if (BiomeDictionary.isBiomeOfType(biome, Type.SNOWY) && event.y <40){
						replaceEntity(event.entity, new SkeletonIce(event.world));
					}
					else if (event.y < 32 && random.nextBoolean()){
						replaceEntity(event.entity, new SkeletonLava(event.world));

					}
				}
			}
		}

	}


public static void replaceEntity(Entity oldentity, Entity newentity){
	newentity.setLocationAndAngles(oldentity.posX, oldentity.posY, oldentity.posZ, oldentity.rotationYaw, oldentity.rotationPitch);
	oldentity.worldObj.spawnEntityInWorld(newentity);
	oldentity.setDead();
}

@SubscribeEvent
public void yourPlayerHarvestEvent(HarvestDropsEvent event){
	if (Loader.isModLoaded("UndergroundBiomes") && event.block == UBCblocks.SedimentaryStone)
	{
		event.drops.clear();
		int metadata = event.blockMetadata;
		if (metadata > 7){ metadata = metadata - 8;}
		event.drops.add(new ItemStack(UBCSand.sedimentarySand, 1, metadata));
	}
}



}
