package cavebiomes.entities.zombie;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ZombieMiner extends EntityZombie{

	public ZombieMiner(World world) {
		super(world);
		
	}
	
	@Override
	protected void addRandomArmor()
	{
		this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_pickaxe));
		this.setCurrentItemOrArmor(4, new ItemStack(Items.iron_helmet));
	}
}
