package cavebiomes.worldgeneration;

import java.util.ArrayList;
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
import cavebiomes.worldgeneration.dungeontypes.DungeonTypePharohTomb;
import cavebiomes.worldgeneration.dungeontypes.DungeonTypeRain;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class CaveTypeRegister {

	public static HashMap<BiomeAndHeight, CaveType> cavebiomemap = new HashMap <BiomeAndHeight, CaveType>();

	private static DungeonType[] defaultDungeons = {new DungeonTypeCaveIn(), new DungeonSpeleothemGrotto(), new DungeonClassicSpider(), new DungeonClassicSkeleton(), new DungeonClassicZombie()};
	private static DungeonType[] forestDungeons= {new DungeonForest(), new DungeonTypeFoxfire()};
	private static DungeonType[] coldDungeons = {new DungeonTypeFrozenSolid()};
	private static DungeonType[] wetDungeons= {new DungeonTypeRain(), new DungeonSlime()};
	private static DungeonType[] volcanicDungeons= {new DungeonMagmaSlime(), new DungeonTypeCinderShroom()};
	private static DungeonType[] desertDungeons= {new DungeonCaveOasis(), new DungeonTypeMummysTomb(), new DungeonTypePharohTomb()};
	private static DungeonType[] netherDungeons= {new DungeonPigman(), new DungeonBlaze()};

	public static ArrayList<DungeonType> defaultlist;// = dungeonList(defaultDungeons);
	public static ArrayList<DungeonType> forestlist;// = dungeonList(defaultDungeons, forestDungeons);
	public static ArrayList<DungeonType> wetlist;// = dungeonList(defaultDungeons, wetDungeons);
	public static ArrayList<DungeonType> swamplist;// = dungeonList(defaultDungeons, wetDungeons, forestDungeons);
	public static ArrayList<DungeonType> desertlist;// = dungeonList(defaultDungeons, desertDungeons);
	public static ArrayList<DungeonType> junglelist;// = dungeonList(defaultDungeons, volcanicDungeons);
	public static ArrayList<DungeonType> coldlist;// = dungeonList(defaultDungeons, coldDungeons);
	public static ArrayList<DungeonType> volcaniclist;// = dungeonList(netherDungeons, volcanicDungeons);
	public static ArrayList<DungeonType> deepdesertlist;// = dungeonList(volcanicDungeons, netherDungeons, desertDungeons);
	
	public static void addDungeonTypes(){

		defaultlist = dungeonList(defaultDungeons);
		forestlist = dungeonList(defaultDungeons, forestDungeons);
		wetlist = dungeonList(defaultDungeons, wetDungeons);
		swamplist = dungeonList(defaultDungeons, wetDungeons, forestDungeons);
		desertlist = dungeonList(defaultDungeons, desertDungeons);
		junglelist = dungeonList(defaultDungeons, volcanicDungeons);
		coldlist = dungeonList(defaultDungeons, coldDungeons);
		volcaniclist = dungeonList(netherDungeons, volcanicDungeons);
		deepdesertlist = dungeonList(volcanicDungeons, netherDungeons, desertDungeons);
	}

	//jacko lantern
	//mineshaft, nether portal, mushroom, cave in,
	//carved stone, carved sandstone

	//shallow caves
	public static CaveType normalShallow = new CaveTypeNormalShallow("Normal", 1, defaultlist);
	public static CaveType wetShallow =new CaveTypeWetShallow ("WetShallow", 1, wetlist);
	public static CaveType swamp = new CaveTypeSwamp ("Swamp", 1, swamplist);
	public static CaveType sandyShallow = new CaveTypeSandy ("Sandy",1, desertlist);
	public static CaveType jungleVolcano = new CaveTypeJungleVolcano ("Overgrown",1, junglelist);
	public static CaveType mountains = new CaveTypeMountain ("Mountains",1, defaultlist);
	public static CaveType iceMountain = new CaveTypeIceMountain ("IceMountains",1, coldlist);
	public static CaveType iceShallow = new CaveTypeShallowIce ("Ice",1, coldlist);
	public static CaveType fungal = new CaveTypeFungal ("Fungal",1, swamplist);
	public static CaveType plains = new CaveTypePlains ("Plains",1, defaultlist);
	public static CaveType coniferous = new CaveTypeConifer ("Coniferous",1, forestlist);
	public static CaveType forest = new CaveTypeForest("Forest", 1, forestlist);

	// mid caves
	public static CaveType normalMid = new CaveTypeNormalMid("NormalMid", 2, defaultlist);
	public static CaveType wetMid = new CaveTypeWetMid ("WetMid", 2, wetlist);
	public static CaveType iceMid = new CaveTypeMidIce ("MidIce", 2, coldlist);
	public static CaveType desertMid = new CaveTypeDesertMid ("DesertMid", 2, deepdesertlist);

	//Deep caves
	public static CaveType wetDeep = new CaveTypeWetDeep ("WetDeep", 3, wetlist);
	public static CaveType iceDeep = new CaveTypeDeepIce ("DeepIce", 3, coldlist);
	//public static CaveType desertDeep = new CaveTypeDesertDeep ("DesertDeep", 3, volcaniclist);
	public static CaveType volcanic = new CaveTypeVolcanic ("Volcanic", 3, volcaniclist);



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

	public static ArrayList<DungeonType> dungeonList(DungeonType[] dungeonlist){
		ArrayList<DungeonType> arraylist = new ArrayList<DungeonType>();
		for (int loop = 0; loop < dungeonlist.length; loop++){
			arraylist.add(dungeonlist[loop]);
		}
		return arraylist;
	}
	public static ArrayList<DungeonType> dungeonList(DungeonType[] dungeonlist1, DungeonType[] dungeonlist2){
		ArrayList<DungeonType> arraylist = new ArrayList<DungeonType>();
		for (int loop = 0; loop < dungeonlist1.length; loop++){
			arraylist.add(dungeonlist1[loop]);
		}
		for (int loop = 0; loop < dungeonlist2.length; loop++){
			arraylist.add(dungeonlist2[loop]);
		}
		return arraylist;
	}
	public static ArrayList<DungeonType> dungeonList(DungeonType[] dungeonlist1, DungeonType[] dungeonlist2, DungeonType[] dungeonlist3){
		ArrayList<DungeonType> arraylist = new ArrayList<DungeonType>();
		for (int loop = 0; loop < dungeonlist1.length; loop++){
			arraylist.add(dungeonlist1[loop]);
		}
		for (int loop = 0; loop < dungeonlist2.length; loop++){
			arraylist.add(dungeonlist2[loop]);
		}
		for (int loop = 0; loop < dungeonlist3.length; loop++){
			arraylist.add(dungeonlist3[loop]);
		}
		return arraylist;
	}

}
