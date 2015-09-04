package cavebiomes.worldgeneration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import cavebiomes.WTFCaveBiomesConfig;
import net.minecraft.world.World;
import wtfcore.WorldGenListener;
import wtfcore.utilities.CavePosition;
import wtfcore.worldgen.OverworldScanner;

public class CaveBiomesWorldScanner extends OverworldScanner{

	@Override
	public void generate(World world, Random rand, int chunkX, int chunkZ)
	{		
		ArrayList<CavePosition> cavepositions = new ArrayList<CavePosition>();
		ArrayList<CavePosition> dungeonposition = new ArrayList<CavePosition>();

		int lastY = 70;

		int surfaceaverage = 0;

		for (int xloop = 0; xloop < 16; xloop++)
		{
			int x = chunkX + xloop;
			for (int zloop = 0; zloop < 16; zloop++){

				int z = chunkZ + zloop;
				
				
				
				
				
				int y = scanForSurface(world, x, lastY, z);
				
				lastY = y;

				surfaceaverage += y;

				int ceiling = -1;
				boolean wasAir = false;

				while (y > 10){

					if (world.isAirBlock(x, y, z)){

						if (!wasAir && ceiling == -1){
							ceiling = y + 1;
						}
						wasAir = true;
					}
					else{

						if (ceiling != -1){
							cavepositions.add(new CavePosition(x, ceiling, y, z));

							dungeonposition.add(new CavePosition(x, ceiling, y, z));

							ceiling = -1;
							y--;
						}
						wasAir = false;
					}
					y--;
				}
			}
		}

		surfaceaverage /= 256;
		if (surfaceaverage < 64){ surfaceaverage = 64;}

		//Additional Generators called herer- such as WTFOres
		if (WorldGenListener.generator != null){
			WorldGenListener.generator.generate(world, surfaceaverage, chunkX, chunkZ, rand);
		}

		int deepmax = surfaceaverage/3;
		int midmax = surfaceaverage*2/3;

		//iterators for CaveBiomes cave generation
		CaveType shallowtype = CaveTypeRegister.GetShallowCaveType(world, chunkX, chunkZ);
		CaveType midtype = CaveTypeRegister.GetMidCaveType(world, chunkX, chunkZ);
		CaveType deeptype = CaveTypeRegister.GetDeepCaveType(world, chunkX, chunkZ);


		CavePosition position;
		if (WTFCaveBiomesConfig.generateCaveSubtypes){
			Iterator<CavePosition> dungeoniterator = dungeonposition.iterator();
			while (dungeoniterator.hasNext()) {
				position = dungeoniterator.next();

				if (position.floor < deepmax){
					if (rand.nextInt(deeptype.DungeonWeight) == 1){
						deeptype.generateDungeon(world, rand, position.x, position.floor+5+rand.nextInt(3), position.z, position.ceiling, position.floor);
					}
				}
				else if (position.floor < midmax ){
					if (rand.nextInt(midtype.DungeonWeight) == 1){
						midtype.generateDungeon(world, rand, position.x,  position.floor+5+rand.nextInt(3), position.z, position.ceiling, position.floor);
					}
				}
				else {
					if (rand.nextInt(shallowtype.DungeonWeight) == 1){
						shallowtype.generateDungeon(world, rand, position.x,  position.floor+5+rand.nextInt(3), position.z, position.ceiling, position.floor);
					}
				}
			}
		}

		Iterator<CavePosition> caveiterator= cavepositions.iterator();
		while (caveiterator.hasNext()) {
			position = caveiterator.next();


			if (position.floor < deepmax){

				deeptype.generate(world, rand, position.x, position.floor, position.ceiling, position.z);
			}
			else if (position.floor < midmax){
				midtype.generate(world, rand, position.x, position.floor, position.ceiling, position.z);
			}
			else {
				shallowtype.generate(world, rand, position.x, position.floor, position.ceiling, position.z);
			}
		}

	}

}
