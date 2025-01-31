package cc.unilock.legacyfixes.mixin.early.apatheticMobs;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public class EntityLivingBaseMixin {
    @Inject(method = "setRevengeTarget(Lnet/minecraft/entity/EntityLivingBase;)V", at = @At("HEAD"), cancellable = true)
    private void legacyfixes$setRevengeTarget(EntityLivingBase p_70604_1_, CallbackInfo ci) {
        if (p_70604_1_ instanceof EntityPlayer) {
            ci.cancel();
        }
    }
}
