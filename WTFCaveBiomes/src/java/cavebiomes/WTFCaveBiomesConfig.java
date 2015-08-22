package cavebiomes;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class WTFCaveBiomesConfig {


	public static boolean EnableMobSpawners;

	public static boolean enableDrippingBlocks;
	public static int ceilingAddonChance;
	public static int floorAddonChance;
	public static boolean enableMossyStone;
	public static int dungeonChance;

	public static int magmaCrustGenRate;

	public static boolean foxfireGlow;
	public static boolean foxfireAnimations;
	public static boolean cindershroomGlow;
	public static boolean cindershroomAnimations;
	public static boolean lavacrustGlow;
	public static boolean lavacrustAnimations;

	public static boolean generateCaveSubtypes;
	public static boolean logDungeons;
	public static boolean customMobs;

	public static void customConfig() {

		Configuration config = new Configuration(new File("config/WTFCaveBiomesConfig.cfg"));

		config.load();
		EnableMobSpawners = config.get("Cave Subtype Generation", "EnableMobSpawners", true).getBoolean();
		dungeonChance = config.get("Cave Subtype Generation", "Subtype rarity- Set higher for fewer cave subtypes, lower for more", 4096).getInt();
		logDungeons=config.get("Cave Subtype Generation", "Log subtypes in chat", false).getBoolean();

		ceilingAddonChance = config.get("Cave Generation Options", "base frequency of stalactites, and other ceiling addons", 5).getInt();
		floorAddonChance = config.get("CaveGeneration Options", "base frequency of stalagmite, and other floor addons", 5).getInt();

		enableMossyStone = config.get("Block Options", "Enable Additional Mossy Stone Blocks", true).getBoolean();
		enableDrippingBlocks = config.get("Block Options", "Enable modded dripping blocks", true).getBoolean();
		foxfireGlow = config.get("Block Options", "Enable foxfire glowing", true).getBoolean();
		foxfireAnimations = config.get("Block Options", "Enable foxfire animations", true).getBoolean();
		cindershroomGlow = config.get("Block Options", "Enable cindershroom glowing", true).getBoolean();
		cindershroomAnimations = config.get("Block Options", "Enable cindershroom animations", true).getBoolean();
		lavacrustGlow = config.get("Block Options", "Enable Lava Crust Glows", true).getBoolean();
		lavacrustAnimations = config.get("Block Options", "Enable Lava Crust Animations", true).getBoolean();

		//magmaCrustGenRate =  config.get("Ore style generation", "Generation rate of magma crust as ore- to disable set to 0", 15).getInt();

		generateCaveSubtypes = config.get("Cave Subtype Generation", "Generate Cave Subtypes", true).getBoolean();
		customMobs = config.get("Mobs Settings", "Enable replacement of zombies and skeletons with biome specific versions", false).getBoolean();

		config.save();
	}
}
