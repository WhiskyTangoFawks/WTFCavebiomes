package cavebiomes.entities;

import java.util.HashMap;

import cavebiomes.CaveBiomes;
import cavebiomes.entities.skeleton.SkeletonIce;
import cavebiomes.entities.skeleton.SkeletonKnight;
import cavebiomes.entities.skeleton.SkeletonLava;
import cavebiomes.entities.skeleton.SkeletonMage;
import cavebiomes.entities.skeleton.SkeletonMiner;
import cavebiomes.entities.zombie.ZombieFrozen;
import cavebiomes.entities.zombie.ZombieMiner;
import cavebiomes.entities.zombie.ZombieMummy;
import cavebiomes.entities.zombie.ZombiePharoh;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class Entities {
	
	public static HashMap<String, Block> spawners = new HashMap<String, Block>();
	static int counter = 1;
	
	public static void RegisterEntityList()
	{	
		registerEntity(ZombieMummy.class, "ZombieMummy");
		registerEntity(ZombieFrozen.class, "ZombieFrozen");
		registerEntity(ZombiePharoh.class, "ZombiePharoh");
		registerEntity(ZombieMiner.class, "ZombieMiner");
		registerEntity(SkeletonLava.class, "SkeletonLava");
		registerEntity(SkeletonIce.class, "SkeletonIce");
		registerEntity(SkeletonKnight.class, "SkeletonKnight");
		registerEntity(SkeletonMage.class, "SkeletonMage");
		registerEntity(SkeletonMiner.class, "SkeletonMiner");
		//registerEntity(CustomWolf.class, "HellHound");
		registerEntity(CustomIronGolem.class, "DerangedGolem");

		registerSpawner("Witch");

	}

	public static void registerEntity(Class entityClass, String name)
	{
		int entityId = counter;
		counter++;
		EntityRegistry.registerModEntity(entityClass, name, entityId, CaveBiomes.instance, 64, 1, true);
		registerSpawner(name);
	}
	
	public static void registerSpawner(String name){
		Block spawner = new SpawnerBlock(name).setBlockName(name+"Spawner");
		GameRegistry.registerBlock(spawner, name+"Spawner");
		spawners.put(name, spawner);
	}


}
