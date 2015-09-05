package cavebiomes.entities;

import java.util.Random;

import cavebiomes.entities.skeleton.SkeletonIce;
import cavebiomes.entities.skeleton.SkeletonKnight;
import cavebiomes.entities.skeleton.SkeletonLava;
import cavebiomes.entities.skeleton.SkeletonMage;
import cavebiomes.entities.skeleton.SkeletonMiner;
import cavebiomes.entities.zombie.ZombieFrozen;
import cavebiomes.entities.zombie.ZombieMummy;
import cavebiomes.entities.zombie.ZombiePharoh;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class SpawnHandler {

	static Random random = new Random();

	public static void replaceEntity(Entity oldentity, Entity newentity){
		newentity.setLocationAndAngles(oldentity.posX, oldentity.posY, oldentity.posZ, oldentity.rotationYaw, oldentity.rotationPitch);
		oldentity.worldObj.spawnEntityInWorld(newentity);
		oldentity.setDead();
	}

	public static void zombieSpawn(LivingSpawnEvent event){
		EntityZombie oldentity = (EntityZombie) event.entityLiving;
		if (oldentity.getCanSpawnHere()){
			BiomeGenBase biome = event.world.getBiomeGenForCoords(MathHelper.floor_float(event.x), MathHelper.floor_float(event.z));
			Block baseBlock = event.world.getBlock(MathHelper.floor_float(event.x), MathHelper.floor_float(event.y)-1, MathHelper.floor_float(event.z));

			if (baseBlock == Blocks.rail){
				replaceEntity(oldentity, new SkeletonMiner(event.world));
			}
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

	public static void skeletonSpawn(LivingSpawnEvent event){
		EntitySkeleton oldentity = (EntitySkeleton) event.entityLiving;

		if (oldentity.getCanSpawnHere()){
			BiomeGenBase biome = event.world.getBiomeGenForCoords(MathHelper.floor_float(event.x), MathHelper.floor_float(event.z));
			Block baseBlock = event.world.getBlock(MathHelper.floor_float(event.x), MathHelper.floor_float(event.y)-1, MathHelper.floor_float(event.z));

			if (baseBlock == Blocks.stonebrick){
				if(random.nextInt(5) == 0){
					replaceEntity(oldentity, new SkeletonMage(event.world));
				}
				else if (random.nextInt(3) == 0){
					replaceEntity(oldentity, new SkeletonKnight(event.world));
				}
			}
			else if (baseBlock == Blocks.rail){
				replaceEntity(oldentity, new SkeletonMiner(event.world));
			}

			if (event.y < 40){
				if(random.nextInt(50) == 0){
					replaceEntity(oldentity, new SkeletonMage(event.world));
				}
				else if (random.nextInt(25) == 0){
					replaceEntity(oldentity, new SkeletonKnight(event.world));
				}
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
