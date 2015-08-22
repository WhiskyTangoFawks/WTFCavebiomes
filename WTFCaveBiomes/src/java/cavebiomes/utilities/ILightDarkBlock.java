package cavebiomes.utilities;

import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public interface ILightDarkBlock {

	public IIcon getLitIcon();
	public IIcon getUnlitIcon();
	public int daylightThreshold();
	public int torchlightThreshold();

	public boolean isPlant();

	public boolean shouldBeLit(IBlockAccess world, int x, int y, int z);

}
