package cavebiomes.entities.skeleton;

import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SkeletonKnight extends CustomSkeleton{
	public SkeletonKnight(World p_i1741_1_) {
		super(p_i1741_1_);
		texture = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
		type = SkeletonType.KNIGHT;
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
	}
	@Override
	protected void addRandomArmor()
	{
		if (this.type == SkeletonType.KNIGHT){
			this.setCurrentItemOrArmor(3, new ItemStack(Items.iron_chestplate));
			this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));
		}


	}
}