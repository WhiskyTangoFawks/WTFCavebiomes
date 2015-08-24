package cavebiomes.worldgeneration;

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

public class DungeonTypeRegister {
	
	
	public static DungeonType CaveIn = new DungeonTypeCaveIn();
	public static DungeonType Grotto = new DungeonSpeleothemGrotto();
	private static DungeonType Spider = new DungeonClassicSpider();
	private static DungeonType Skeleton = new DungeonClassicSkeleton();
	private static DungeonType Zombie = new DungeonClassicZombie();
	private static DungeonType Forest = new DungeonForest();
	private static DungeonType Foxfire = new DungeonTypeFoxfire();
	private static DungeonType Frozen = new DungeonTypeFrozenSolid();
	private static DungeonType Rainstone = new DungeonTypeRain();
	private static DungeonType Slime = new DungeonSlime();
	private static DungeonType MagmaSlime = new DungeonMagmaSlime();
	private static DungeonType Cindershroom = new DungeonTypeCinderShroom();
	private static DungeonType Oasis = new DungeonCaveOasis();
	private static DungeonType Mummy = new DungeonTypeMummysTomb();
	private static DungeonType Pharoh = new DungeonTypePharohTomb();
	private static DungeonType Pigman = new DungeonPigman();
	private static DungeonType Blaze = new DungeonBlaze();
	
	private static DungeonType[] defaultlist= {CaveIn, Grotto, Spider, Skeleton, Zombie};
	private static DungeonType[] forestlist = {Forest, Foxfire};
	private static DungeonType[] coldlist = {Frozen};
	private static DungeonType[] wetlist = {Rainstone, Slime};
	private static DungeonType[] volcaniclist = {MagmaSlime, Cindershroom};
	private static DungeonType[] desertlist = {Mummy, Pharoh, Oasis};
	private static DungeonType[] netherlist = {Pigman, Blaze};
	
	
	public static DungeonSet defaultSet = new DungeonSet();
	public static DungeonSet forestSet = new DungeonSet();
	public static DungeonSet wetSet = new DungeonSet();
	public static DungeonSet swampSet = new DungeonSet();
	public static DungeonSet desertSet = new DungeonSet();
	public static DungeonSet jungleSet = new DungeonSet();
	public static DungeonSet coldSet = new DungeonSet();
	public static DungeonSet volcanicSet = new DungeonSet();
	
	public static void AddDungeonTypes(){
		defaultSet.addDungeons(defaultlist);
		forestSet.addDungeons(defaultlist, forestlist);
		coldSet.addDungeons(defaultlist, coldlist);
		wetSet.addDungeons(defaultlist, wetlist);
		swampSet.addDungeons(defaultlist, wetlist, forestlist);
		desertSet.addDungeons(defaultlist, desertlist);
		jungleSet.addDungeons(defaultlist,volcaniclist);
		volcanicSet.addDungeons(defaultlist, netherlist);
		
	}
	
	
	
}
