package cavebiomes.entities.skeleton;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SkeletonMiner extends EntitySkeleton{

	public SkeletonMiner(World p_i1741_1_) {
		super(p_i1741_1_);
	}

	@Override
	protected void addRandomArmor()
	{
		this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_pickaxe));
		this.setCurrentItemOrArmor(4, new ItemStack(Items.iron_helmet));
	}
}
