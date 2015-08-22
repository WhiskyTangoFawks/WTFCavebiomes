package cavebiomes.entities.skeleton;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SkeletonIce extends CustomSkeleton{
	public SkeletonIce(World p_i1741_1_) {
		super(p_i1741_1_);
		texture = new ResourceLocation("cavebiomes:textures/entity/ice_skeleton.png");
		type = SkeletonType.ICE;
	}
}

