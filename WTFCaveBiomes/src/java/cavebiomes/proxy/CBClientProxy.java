package cavebiomes.proxy;

import net.minecraft.client.model.ModelWolf;
import cavebiomes.entities.CustomWolf;
import cavebiomes.entities.skeleton.SkeletonIce;
import cavebiomes.entities.skeleton.SkeletonKnight;
import cavebiomes.entities.skeleton.SkeletonLava;
import cavebiomes.entities.skeleton.SkeletonMage;
import cavebiomes.entities.zombie.ZombieFrozen;
import cavebiomes.entities.zombie.ZombieMummy;
import cavebiomes.entities.zombie.ZombiePharoh;
import cavebiomes.renderers.RenderCustomSkeleton;
import cavebiomes.renderers.RenderCustomWolf;
import cavebiomes.renderers.RenderCustomZombie;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class CBClientProxy extends CommonProxy {

	public static int renderPass;
	public static int MagmaCrustRenderType;
	public static int FrozenBlockRenderType;;
	public static int LightDarkRenderType;

	@Override
	public void registerRenderers() {
		MagmaCrustRenderType = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new cavebiomes.renderers.MagmaCrustRenderer());

		FrozenBlockRenderType = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new cavebiomes.renderers.FrozenBlockRenderer());

		LightDarkRenderType = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(new cavebiomes.renderers.LightDarkRenderer());

		RenderingRegistry.registerEntityRenderingHandler(ZombieMummy.class, new RenderCustomZombie());
		RenderingRegistry.registerEntityRenderingHandler(ZombiePharoh.class, new RenderCustomZombie());
		RenderingRegistry.registerEntityRenderingHandler(ZombieFrozen.class, new RenderCustomZombie());
		RenderingRegistry.registerEntityRenderingHandler(SkeletonLava.class, new RenderCustomSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(SkeletonIce.class, new RenderCustomSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(SkeletonKnight.class, new RenderCustomSkeleton());
		RenderingRegistry.registerEntityRenderingHandler(SkeletonMage.class, new RenderCustomSkeleton());

		RenderingRegistry.registerEntityRenderingHandler(CustomWolf.class, new RenderCustomWolf(new ModelWolf(), new ModelWolf(), 0.5F));
	}
}