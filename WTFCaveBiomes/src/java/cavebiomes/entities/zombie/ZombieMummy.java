package cavebiomes.entities.zombie;

import java.util.Random;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

public class ZombieMummy extends EntityZombie{

	
	public ZombieMummy(World world) {
		super(world);
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setAvoidSun(true);
		this.setSize(0.6F, 1.8F);
	}
 
}
