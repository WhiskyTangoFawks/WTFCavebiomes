package cavebiomes.worldgeneration;

import java.util.HashMap;

import cavebiomes.utilities.BiomeAndHeight;
import cavebiomes.worldgeneration.cavetypes.deep.CaveTypeDeepIce;
import cavebiomes.worldgeneration.cavetypes.deep.CaveTypeVolcanic;
import cavebiomes.worldgeneration.cavetypes.deep.CaveTypeWetDeep;
import cavebiomes.worldgeneration.cavetypes.mid.CaveTypeDesertMid;
import cavebiomes.worldgeneration.cavetypes.mid.CaveTypeMidIce;
import cavebiomes.worldgeneration.cavetypes.mid.CaveTypeNormalMid;
import cavebiomes.worldgeneration.cavetypes.mid.CaveTypeWetMid;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeConifer;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeForest;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeFungal;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeIceMountain;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeJungleVolcano;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeMountain;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeNormalShallow;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypePlains;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeSandy;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeShallowIce;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeSwamp;
import cavebiomes.worldgeneration.cavetypes.shallow.CaveTypeWetShallow;
import cavebiomes.worldgeneration.dungeontypes.DungeonBlaze;
import cavebiomes.worldgeneration.dungeontypes.DungeonCaveOasis;
import cavebiomes.worldgeneration.dungeontypes.DungeonClassicSkeleton;
import cavebiomes.worldgeneration.dungeontypes.DungeonClassicSpider;
import cavebiomes.worldgeneration.dungeontypes.DungeonClassicZombie;
import cavebiomes.worldgeneration.dungeontypes.DungeonForest;
import cavebiomes.worldgeneration.dungeontypes.DungeonMagmaSlime;
import cavebiomes.worldgeneration.dungeontypes.DungeonPigman;
import cavebiomes.worldgeneration.dungeontypes.DungeonSlime;
import cavebiomes.worldgeneration.dungeontypes.DungeonSpeleothemGrotto;
import cavebiomes.worldgeneration.dungeontypes.DungeonTypeCaveIn;
import cavebiomes.worldgeneration.dungeontypes.DungeonTypeCinderShroom;
import cavebiomes.worldgeneration.dungeontypes.DungeonTypeFoxfire;
import cavebiomes.worldgeneration.dungeontypes.DungeonTypeFrozenSolid;
import cavebiomes.worldgeneration.dungeontypes.DungeonTypeMummysTomb;
import cavebiomes.worldgeneration.dungeontypes.DungeonTypeRain;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class CaveTypeRegister {

	public static HashMap<BiomeAndHeight, CaveType> cavebiomemap = new HashMap <BiomeAndHeight, CaveType>();

	public static void addDungeonTypes()
	{
		Spider = new DungeonClassicSpider("Classic Spider");
		Skeleton = new DungeonClassicSkeleton("Classic Skeleton");
		Zombie = new DungeonClassicZombie(" Classic Zombie");
		Forest = new DungeonForest("Forest");
		MagmaSlime =new DungeonMagmaSlime("MagmaSlime");
		Pigman = new DungeonPigman("Pigman");
		Slime = new DungeonSlime("Slime Dungeon");
		Blaze =  new DungeonBlaze("Blaze Dungeon");
		Oasis = new DungeonCaveOasis("Oasis");
		Foxfire = new DungeonTypeFoxfire("Foxfire");
		cindershroom = new DungeonTypeCinderShroom("Cindershroom");
		Rain = new DungeonTypeRain("Rainstone");
		Speleothem = new DungeonSpeleothemGrotto("Stalactite Grotto");
		CaveIn = new DungeonTypeCaveIn("CaveIn");
		FrozenSolid = new DungeonTypeFrozenSolid("Frozen Solid");
		Mummy  = new DungeonTypeMummysTomb("Mummy's Tomb");
		Pharoh = new DungeonTypeMummysTomb("Pharoh's Tomb");
	}

	public static DungeonType Mummy;
	public static DungeonType Pharoh;
	public static DungeonType Spider;
	public static DungeonType Skeleton;
	public static DungeonType Zombie;
	public static DungeonType MagmaSlime;
	public static DungeonType Pigman;
	public static DungeonType Slime;
	public static DungeonType Blaze;

	public static DungeonType Forest;
	public static DungeonType Oasis;
	public static DungeonType Foxfire;
	public static DungeonType Rain;
	public static DungeonType Speleothem;
	public static DungeonType FrozenSolid;
	public static DungeonType CaveIn;
	public static DungeonType cindershroom;

	private static DungeonType[] defaultlist = {CaveIn, Speleothem, Spider, Skeleton, Zombie};

	private static DungeonType[] forestList= {CaveIn, Speleothem, Spider, Skeleton, Zombie, Forest, Foxfire};
	private static DungeonType[] fungalList= {CaveIn, Skeleton, Zombie, Slime, Foxfire};

	private static DungeonType[] coldList = {FrozenSolid, Speleothem, Skeleton, Zombie};
	private static DungeonType[] swampList= {CaveIn, Speleothem, Rain, Zombie, Slime, Foxfire};
	private static DungeonType[] wetList= {CaveIn, Speleothem, Rain, Skeleton, Zombie, Slime};

	private static DungeonType[] jungleVolcanoList= {Spider, Skeleton, Zombie, MagmaSlime, cindershroom};
	private static DungeonType[] volcanicList= {MagmaSlime, Pigman, Blaze, cindershroom};

	private static DungeonType[] desertList= {CaveIn, Spider, Skeleton, Oasis, Mummy, Pharoh};
	private static DungeonType[] deepDesertList= {CaveIn, Pigman, MagmaSlime, Blaze, Pharoh};



	//jacko lantern
	//mineshaft, nether portal, mushroom, cave in,
	//carved stone, carved sandstone

	//shallow caves
	public static CaveType normalShallow = new CaveTypeNormalShallow("Normal", 1, defaultlist);
	public static CaveType wetShallow =new CaveTypeWetShallow ("WetShallow", 1, wetList);
	public static CaveType swamp = new CaveTypeSwamp ("Swamp", 1, swampList);
	public static CaveType sandyShallow = new CaveTypeSandy ("Sandy",1, desertList);
	public static CaveType jungleVolcano = new CaveTypeJungleVolcano ("Overgrown",1, jungleVolcanoList);
	public static CaveType mountains = new CaveTypeMountain ("Mountains",1, defaultlist);
	public static CaveType iceMountain = new CaveTypeIceMountain ("IceMountains",1, coldList);
	public static CaveType iceShallow = new CaveTypeShallowIce ("Ice",1, coldList);
	public static CaveType fungal = new CaveTypeFungal ("Fungal",1, fungalList);
	public static CaveType plains = new CaveTypePlains ("Plains",1, defaultlist);
	public static CaveType coniferous = new CaveTypeConifer ("Coniferous",1, forestList);
	public static CaveType forest = new CaveTypeForest("Forest", 1, forestList);

	// mid caves
	public static CaveType normalMid = new CaveTypeNormalMid("NormalMid", 2, defaultlist);
	public static CaveType wetMid = new CaveTypeWetMid ("WetMid", 2, wetList);
	public static CaveType iceMid = new CaveTypeMidIce ("MidIce", 2, coldList);
	public static CaveType desertMid = new CaveTypeDesertMid ("DesertMid", 2, deepDesertList);

	//Deep caves
	public static CaveType wetDeep = new CaveTypeWetDeep ("WetDeep", 3, wetList);
	public static CaveType iceDeep = new CaveTypeDeepIce ("DeepIce", 3, coldList);
	//public static CaveType desertDeep = new CaveTypeDesertDeep ("DesertDeep", 3, volcanicList);
	public static CaveType volcanic = new CaveTypeVolcanic ("Volcanic", 3, volcanicList);



	public static CaveType getCaveType(BiomeAndHeight biomeandheight){
		CaveType cavetype = cavebiomemap.get(biomeandheight);
		if (cavetype == null){
			putBiomeAndHeightInMap(biomeandheight);
			cavetype = cavebiomemap.get(biomeandheight);
		}
		return cavetype;
		//return  forest;
	}



	public static CaveType GetShallowCaveType(World world, int x, int z)
	{
		//BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		return getCaveType(new BiomeAndHeight(world.getBiomeGenForCoords(x, z), BiomeAndHeight.SHALLOW));
	}
	public static CaveType GetMidCaveType(World world, int x, int z)
	{
		//BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		return getCaveType(new BiomeAndHeight(world.getBiomeGenForCoords(x, z), BiomeAndHeight.MID));
	}
	public static CaveType GetDeepCaveType(World world, int x, int z)
	{
		//BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
		return getCaveType(new BiomeAndHeight(world.getBiomeGenForCoords(x, z), BiomeAndHeight.DEEP));
	}

	public static void putBiomeAndHeightInMap(BiomeAndHeight biomeandheight){

		CaveType cavetypetoset = null;

		//ShallowCaveTypes
		if (biomeandheight.depth == BiomeAndHeight.SHALLOW){

			cavetypetoset = normalShallow;
			if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.OCEAN)){cavetypetoset = wetShallow;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SWAMP)){cavetypetoset = swamp;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.MESA)){cavetypetoset = plains;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SANDY)){cavetypetoset = sandyShallow;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.JUNGLE)){cavetypetoset = jungleVolcano;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.MOUNTAIN)){cavetypetoset = mountains;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.MOUNTAIN)&&BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SNOWY)){cavetypetoset = iceMountain;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SNOWY)){cavetypetoset = iceShallow;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.MUSHROOM)){cavetypetoset = fungal;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.PLAINS)){cavetypetoset = plains;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.FOREST)){
				if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.CONIFEROUS)){cavetypetoset =coniferous;}
				else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.LUSH)){cavetypetoset =swamp;}
				else if (!BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.JUNGLE) && !BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SAVANNA)){
					cavetypetoset = forest;
				}
			}

		}
		//Mid-depth cave types
		else if (biomeandheight.depth == BiomeAndHeight.MID){
			//int depth = 2;
			cavetypetoset = normalMid;
			if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.OCEAN)){cavetypetoset = iceMid;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SNOWY)){cavetypetoset = iceMid;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SANDY)){cavetypetoset = desertMid;}
			//
		}

		//Deep cave types
		else if (biomeandheight.depth == BiomeAndHeight.DEEP){
			//int depth = 3;
			cavetypetoset = volcanic;
			if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.OCEAN)){cavetypetoset = wetDeep;}
			else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SNOWY)){cavetypetoset = iceDeep;}
			//else if (BiomeDictionary.isBiomeOfType(biomeandheight.biome, Type.SANDY)){cavetypetoset = desertDeep;}
			//add deep sandy
		}


		if (cavetypetoset != null){
			cavebiomemap.put(biomeandheight, cavetypetoset);

		}
		else {
			System.out.println("Cave Type height outside parameters");
		}
	}


}
