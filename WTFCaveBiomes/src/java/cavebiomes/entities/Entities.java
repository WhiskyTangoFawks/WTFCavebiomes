package cavebiomes.entities;

import java.util.Random;

import cavebiomes.CaveBiomes;
import cavebiomes.entities.skeleton.SkeletonIce;
import cavebiomes.entities.skeleton.SkeletonKnight;
import cavebiomes.entities.skeleton.SkeletonLava;
import cavebiomes.entities.skeleton.SkeletonMage;
import cavebiomes.entities.zombie.ZombieFrozen;
import cavebiomes.entities.zombie.ZombieMummy;
import cavebiomes.entities.zombie.ZombiePharoh;
import net.minecraft.entity.EntityList;
import cpw.mods.fml.common.registry.EntityRegistry;

public class Entities {
	public static void RegisterEntityList()
	{
		registerEntity(ZombieMummy.class, "ZombieMummy", true);
		registerEntity(ZombieFrozen.class, "ZombieFrozen", true);
		registerEntity(ZombiePharoh.class, "ZombiePharoh",true);
		registerEntity(SkeletonLava.class, "SkeletonLava", true);
		registerEntity(SkeletonIce.class, "SkeletonIce", true);
		registerEntity(SkeletonKnight.class, "SkeletonKnight", true);
		registerEntity(SkeletonMage.class, "SkeletonMage", true);
		registerEntity(CustomWolf.class, "HellHound", true);



	}

	public static void registerEntity(Class entityClass, String name, Boolean doEgg)
	{
		int entityId = EntityRegistry.findGlobalUniqueEntityId();
		long x = name.hashCode();
		Random random = new Random(x);
		int mainColor = random.nextInt() * 16777215;
		int subColor = random.nextInt() * 16777215;

		EntityRegistry.registerGlobalEntityID(entityClass, name, entityId);
		EntityRegistry.registerModEntity(entityClass, name, entityId, CaveBiomes.instance, 64, 1, true);
		if (doEgg)
		{
			EntityList.entityEggs.put(Integer.valueOf(entityId), new EntityList.EntityEggInfo(entityId, mainColor, subColor));
		}
	}



}
