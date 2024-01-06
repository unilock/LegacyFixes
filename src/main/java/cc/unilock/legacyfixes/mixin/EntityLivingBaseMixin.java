package cc.unilock.legacyfixes.mixin;

import cc.unilock.legacyfixes.LegacyFixesConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin {
    @Shadow
    protected EntityPlayer attackingPlayer;

    @Shadow
    protected boolean isJumping;

    @Shadow
    protected abstract boolean isPlayer();

    @Shadow
    protected abstract int getExperiencePoints(EntityPlayer p_70693_1_);

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

    @Redirect(method = "Lnet/minecraft/entity/EntityLivingBase;moveEntityWithHeading(FF)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;isCollidedHorizontally:Z", opcode = Opcodes.GETFIELD, ordinal = 2))
    private boolean legacyfixes$isCollidedHorizontally(EntityLivingBase instance) {
        return instance.isCollidedHorizontally || (LegacyFixesConfig.jumpClimbing && this.isJumping);
    }

    @Redirect(method = "Lnet/minecraft/entity/EntityLivingBase;moveEntityWithHeading(FF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;isOnLadder()Z"))
    private boolean legacyfixes$isOnLadder(EntityLivingBase instance) {
        if (instance.isOnLadder()) {
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

    @Redirect(method = "Lnet/minecraft/entity/EntityLivingBase;onDeathUpdate()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;getExperiencePoints(Lnet/minecraft/entity/player/EntityPlayer;)I"))
    private int legacyfixes$getExperiencePoints(EntityLivingBase instance, EntityPlayer p_70693_1_) {
        return (LegacyFixesConfig.keepXP && this.isPlayer()) ? 0 : this.getExperiencePoints(this.attackingPlayer);
    }

    @Unique
    private double legacyfixes$calculateSpeed(float pitch) {
        return Math.abs(pitch / 90.0) * 0.4;
    }
}
