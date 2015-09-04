package cavebiomes.entities.zombie;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ZombiePharoh extends ZombieMummy{

	public ZombiePharoh(World world) {
		super(world);
		addRandomArmor();
	}

	@Override
	protected void addRandomArmor()
	{
		this.setCurrentItemOrArmor(4, new ItemStack(Items.golden_chestplate));
		this.setCurrentItemOrArmor(3, new ItemStack(Items.golden_chestplate));
		this.setCurrentItemOrArmor(2, new ItemStack(Items.golden_chestplate));
		this.setCurrentItemOrArmor(1, new ItemStack(Items.golden_chestplate));
	}


}
