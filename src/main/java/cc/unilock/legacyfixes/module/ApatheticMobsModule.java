package cc.unilock.legacyfixes.module;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;

public class ApatheticMobsModule {
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityLiving living) {
            living.targetTasks.taskEntries.removeIf(entry -> entry.action instanceof EntityAIHurtByTarget);
        }
    }

    @SubscribeEvent
    public void onLivingSetAttackTarget(LivingSetAttackTargetEvent event) {
        if (event.entityLiving instanceof EntityLiving living && event.target instanceof EntityPlayer) {
            living.setAttackTarget(null);
            living.setRevengeTarget(null);
        }
    }
}
