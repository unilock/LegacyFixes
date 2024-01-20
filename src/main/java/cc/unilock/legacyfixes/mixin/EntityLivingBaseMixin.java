package cc.unilock.legacyfixes.mixin;

import cc.unilock.legacyfixes.LegacyFixesConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin {
    @Shadow
    protected boolean isJumping;

    @Shadow
    protected abstract boolean isPlayer();

    @Unique
    private final EntityLivingBase INSTANCE = (EntityLivingBase) (Object) this;

    @Inject(method = "Lnet/minecraft/entity/EntityLivingBase;moveEntityWithHeading(FF)V", at = @At("TAIL"))
    private void legacyFixes$moveEntityWithHeading(float moveStrafing, float moveForward, CallbackInfo ci) {
        if (LegacyFixesConfig.slideClimbing && !INSTANCE.isInWater() && !INSTANCE.handleLavaMovement() && INSTANCE.isOnLadder() && !INSTANCE.isSneaking() && this.isPlayer()) {
            if (INSTANCE.moveForward == 0) { // Not moving?
                if (INSTANCE.rotationPitch < 0) { // Looking up?
                    INSTANCE.motionY = this.legacyfixes$calculateSpeed(INSTANCE.rotationPitch);
                } else if (INSTANCE.rotationPitch > 0) { // Looking down?
                    INSTANCE.motionY =  this.legacyfixes$calculateSpeed(INSTANCE.rotationPitch) * -1.0;
                }
            }
        }
    }

    @WrapOperation(method = "Lnet/minecraft/entity/EntityLivingBase;moveEntityWithHeading(FF)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;isCollidedHorizontally:Z", opcode = Opcodes.GETFIELD, ordinal = 2))
    private boolean legacyfixes$isCollidedHorizontally(EntityLivingBase instance, Operation<Boolean> original) {
        return original.call(instance) || (LegacyFixesConfig.jumpClimbing && this.isJumping);
    }

    @WrapOperation(method = "Lnet/minecraft/entity/EntityLivingBase;moveEntityWithHeading(FF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isOnLadder()Z"))
    private boolean legacyfixes$isOnLadder(EntityLivingBase instance, Operation<Boolean> original) {
        if (original.call(instance)) {
            if (!LegacyFixesConfig.slideClimbing) return true;

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

    @WrapOperation(method = "Lnet/minecraft/entity/EntityLivingBase;onDeathUpdate()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;getExperiencePoints(Lnet/minecraft/entity/player/EntityPlayer;)I"))
    private int legacyfixes$getExperiencePoints(EntityLivingBase instance, EntityPlayer p_70693_1_, Operation<Integer> original) {
        return (LegacyFixesConfig.keepXP && this.isPlayer()) ? 0 : original.call(instance, p_70693_1_);
    }

    @Unique
    private double legacyfixes$calculateSpeed(float pitch) {
        return Math.abs(pitch / 90.0) * 0.4;
    }
}
