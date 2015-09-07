package cavebiomes.entities.zombie;

import cpw.mods.fml.common.eventhandler.Event.Result;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.ZombieEvent.SummonAidEvent;

public class ZombiePharoh extends ZombieMummy{


	public ZombiePharoh(World world) {
		super(world);
		addRandomArmor();
		this.setSize(0.8F, 2F);
		
	}

	@Override
	protected void addRandomArmor()
	{
		this.setCurrentItemOrArmor(4, new ItemStack(Items.golden_chestplate));
		this.setCurrentItemOrArmor(3, new ItemStack(Items.golden_chestplate));
		this.setCurrentItemOrArmor(2, new ItemStack(Items.golden_chestplate));
		this.setCurrentItemOrArmor(1, new ItemStack(Items.golden_chestplate));
	}

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.5D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.5D);
      
    }

    public boolean attackEntityFrom(DamageSource damagesource, float p_70097_2_)
    {
        if (!super.attackEntityFrom(damagesource, p_70097_2_))
        {
            return false;
        }
        else
        {
            EntityLivingBase entitylivingbase = this.getAttackTarget();

            if (entitylivingbase == null && this.getEntityToAttack() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase)this.getEntityToAttack();
            }

            if (entitylivingbase == null && damagesource.getEntity() instanceof EntityLivingBase)
            {
                entitylivingbase = (EntityLivingBase)damagesource.getEntity();
            }


            int i = MathHelper.floor_double(this.posX);
            int j = MathHelper.floor_double(this.posY);
            int k = MathHelper.floor_double(this.posZ);

            SummonAidEvent summonAid = ForgeEventFactory.fireZombieSummonAid(this, worldObj, i, j, k, entitylivingbase, this.getEntityAttribute(field_110186_bp).getAttributeValue());
            
            if (summonAid.getResult() == Result.DENY)
            {
                return true;
            }
            else if (summonAid.getResult() == Result.ALLOW || entitylivingbase != null && this.worldObj.difficultySetting == EnumDifficulty.HARD && (double)this.rand.nextFloat() < this.getEntityAttribute(field_110186_bp).getAttributeValue())
            {
                EntityZombie entitymummy = new ZombieMummy(worldObj);
               
                for (int l = 0; l < 50; ++l)
                {
                    int i1 = i + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
                    int j1 = j + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);
                    int k1 = k + MathHelper.getRandomIntegerInRange(this.rand, 7, 40) * MathHelper.getRandomIntegerInRange(this.rand, -1, 1);

                    if (World.doesBlockHaveSolidTopSurface(this.worldObj, i1, j1 - 1, k1) && this.worldObj.getBlockLightValue(i1, j1, k1) < 10)
                    {
                    	entitymummy.setPosition((double)i1, (double)j1, (double)k1);

                        if (this.worldObj.checkNoEntityCollision(entitymummy.boundingBox) && this.worldObj.getCollidingBoundingBoxes(entitymummy, entitymummy.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(entitymummy.boundingBox))
                        {
                            this.worldObj.spawnEntityInWorld(entitymummy);
                            if (entitylivingbase != null) entitymummy.setAttackTarget(entitylivingbase);
                         
                            this.getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Zombie reinforcement caller charge", -0.05000000074505806D, 0));
                            entitymummy.getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
                            break;
                        }
                    }
                }
            }

            return true;
        }
    }
    
}
