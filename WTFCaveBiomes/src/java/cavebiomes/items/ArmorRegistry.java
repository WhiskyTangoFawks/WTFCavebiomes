package cavebiomes.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ArmorRegistry {

	public static ArmorMaterial CLOTHARMOR = EnumHelper.addArmorMaterial("CLOTH", 1, new int[] {0, 1, 1, 0}, 5);
	
	public static Item mummyHelmet;
	public static Item mummyChestplate;
	public static Item mummyLeggings;
	public static Item mummyBoots;
	
	public static Item pharohHelmet;
	public static Item pharohChestplate;
	public static Item pharohLeggings;
	public static Item pharohBoots;	


	
	public static void registerArmorTypes(){
		
		mummyHelmet = new CustomArmor(CLOTHARMOR, 0, "mummy").setUnlocalizedName("mummy_helmet");
		GameRegistry.registerItem(mummyHelmet, "mummy_helmet"); //0 for helmet
		
		mummyChestplate = new CustomArmor(CLOTHARMOR, 1, "mummy").setUnlocalizedName("mummy_chestplate");
		GameRegistry.registerItem(mummyChestplate, "mummy_chestplate"); // 1 for chestplate
		
		mummyLeggings = new CustomArmor(CLOTHARMOR, 2, "mummy").setUnlocalizedName("mummy_leggings");
		GameRegistry.registerItem(mummyLeggings, "mummy_leggings"); // 2 for leggings
		
		mummyBoots = new CustomArmor(CLOTHARMOR, 3, "mummy").setUnlocalizedName("mummy_boots");
		GameRegistry.registerItem(mummyBoots, "mummy_boots"); // 3 for boots
		
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 1, 15), new Object[]{mummyHelmet});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 3, 15), new Object[]{mummyChestplate});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 2, 15), new Object[]{mummyLeggings});
		GameRegistry.addShapelessRecipe(new ItemStack(Items.string, 1, 15), new Object[]{mummyBoots});
		
		pharohHelmet = new CustomArmor(ArmorMaterial.GOLD, 0, "pharoh").setUnlocalizedName("pharoh_helmet");
		GameRegistry.registerItem(pharohHelmet, "pharoh_helmet"); //0 for helmet
		
		pharohChestplate = new CustomArmor(ArmorMaterial.GOLD, 1, "pharoh").setUnlocalizedName("pharoh_chestplate");
		GameRegistry.registerItem(pharohChestplate, "pharoh_chestplate"); // 1 for chestplate
		
		pharohLeggings = new CustomArmor(ArmorMaterial.GOLD, 2, "pharoh").setUnlocalizedName("pharoh_leggings");
		GameRegistry.registerItem(pharohLeggings, "pharoh_leggings"); // 2 for leggings
		
		pharohBoots = new CustomArmor(ArmorMaterial.GOLD, 3, "pharoh").setUnlocalizedName("pharoh_boots");
		GameRegistry.registerItem(pharohBoots, "pharoh_boots"); // 3 for boots

	}
	
}
