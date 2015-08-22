package cavebiomes.worldgeneration.cavetypes.deep;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cavebiomes.worldgeneration.CaveType;
import cavebiomes.worldgeneration.DungeonType;
import wtfcore.utilities.BlockSets;

public class CaveTypeDeepIce extends CaveType{

		public CaveTypeDeepIce(String name, int cavedepth, ArrayList<DungeonType> coldlist) {
		super(name, cavedepth, coldlist);
	}

		@Override
		public void generateCeilingAddons(World world, Random random, int x, int y, int z)
		{
			gen.genStalactite(world, x, y, z, depth, Blocks.ice);
		}

		@Override
		public void generateFloor(World world, Random random, int x, int y, int z)
		{
			if (world.getBlock(x, y, z)==Blocks.lava){world.setBlock(x,y,z, Blocks.obsidian);}

			int height = 2*MathHelper.abs_int((MathHelper.abs_int(x/2+z)/2 % 10) -5)-3;
			
			if (height < -1){
				gen.replaceBlock(world, x, y, z, Blocks.obsidian, 0);
				gen.freezeBlock(world, x, y, z);

			}
			else{
				if (random.nextInt(4)==0){
					gen.setStoneAddon(world, x, y, z, BlockSets.Modifier.cobblestone);
					gen.freezeBlock(world, z, y+1, z);
				}
				else if (shouldGenFloorAddon()){
					gen.genStalagmite(world, x, y, z, depth, Blocks.ice);
				}
			}



		}




}
