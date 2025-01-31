package cc.unilock.legacyfixes.mixin.early.apatheticMobs;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLiving.class)
public class EntityLivingMixin {
    @Inject(method = "setAttackTarget(Lnet/minecraft/entity/EntityLivingBase;)V", at = @At("HEAD"), cancellable = true)
    private void legacyfixes$setAttackTarget(EntityLivingBase p_70624_1_, CallbackInfo ci) {
        if (p_70624_1_ instanceof EntityPlayer) {
            ci.cancel();
        }
    }

    @ModifyReturnValue(method = "canAttackClass(Ljava/lang/Class;)Z", at = @At("RETURN"))
    private boolean legacyfixes$canAttackClass(boolean original, Class<? extends Entity> p_70686_1_) {
        return original && !EntityPlayer.class.isAssignableFrom(p_70686_1_);
    }
}
