package cc.unilock.legacyfixes.mixin.early.slideClimbing;

import cc.unilock.legacyfixes.LegacyFixesConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin {
    @Shadow
    protected abstract boolean isPlayer();

    @Inject(method = "Lnet/minecraft/entity/EntityLivingBase;moveEntityWithHeading(FF)V", at = @At("TAIL"))
    private void legacyFixes$moveEntityWithHeading(float moveStrafing, float moveForward, CallbackInfo ci) {
        if (LegacyFixesConfig.slideClimbing) {
            EntityLivingBase instance = (EntityLivingBase) (Object) this;

            if (!instance.isInWater() && !instance.handleLavaMovement() && instance.isOnLadder() && !instance.isSneaking() && this.isPlayer()) {
                if (instance.moveForward == 0) { // Not moving?
                    if (instance.rotationPitch < 0) { // Looking up?
                        instance.motionY = this.legacyfixes$calculateSpeed(instance.rotationPitch);
                    } else if (instance.rotationPitch > 0) { // Looking down?
                        instance.motionY = this.legacyfixes$calculateSpeed(instance.rotationPitch) * -1.0;
                    }
                }
            }
        }
    }

    @ModifyExpressionValue(method = "Lnet/minecraft/entity/EntityLivingBase;moveEntityWithHeading(FF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isOnLadder()Z"))
    private boolean legacyfixes$isOnLadder(boolean original) {
        if (original) {
            if (!LegacyFixesConfig.slideClimbing) return true;

            EntityLivingBase instance = (EntityLivingBase) (Object) this;

            if (instance.motionX < -0.15) {
                instance.motionX = -0.15;
            }

            if (instance.motionX > 0.15) {
                instance.motionX = 0.15;
            }

            if (instance.motionZ < -0.15) {
                instance.motionZ = -0.15;
            }

            if (instance.motionZ > 0.15) {
                instance.motionZ = 0.15;
            }

            instance.fallDistance = 0.0F;

            if (instance.isSneaking() && this.isPlayer() && instance.motionY < 0.0D) {
                instance.motionY = 0.0;
            }

            return instance.moveForward != 0;
        }

        return false;
    }

    @Unique
    private double legacyfixes$calculateSpeed(float pitch) {
        return Math.abs(pitch / 90.0) * 0.4;
    }
}
