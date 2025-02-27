package cc.unilock.legacyfixes.mixin.early.keepXP;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin {
    @Shadow
    protected abstract boolean isPlayer();

    @ModifyExpressionValue(method = "onDeathUpdate()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;getExperiencePoints(Lnet/minecraft/entity/player/EntityPlayer;)I"))
    private int legacyfixes$getExperiencePoints(int original) {
        return this.isPlayer() ? 0 : original;
    }
}
