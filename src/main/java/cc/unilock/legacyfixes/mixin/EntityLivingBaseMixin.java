package cc.unilock.legacyfixes.mixin;

import cc.unilock.legacyfixes.LegacyFixesConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

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

    @Redirect(method = "Lnet/minecraft/entity/EntityLivingBase;moveEntityWithHeading(FF)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;isCollidedHorizontally:Z", opcode = Opcodes.GETFIELD, ordinal = 2))
    private boolean legacyfixes$isCollidedHorizontally(EntityLivingBase instance) {
        return instance.isCollidedHorizontally || (LegacyFixesConfig.jumpClimbing && this.isJumping);
    }

    @Redirect(method = "Lnet/minecraft/entity/EntityLivingBase;onDeathUpdate()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;getExperiencePoints(Lnet/minecraft/entity/player/EntityPlayer;)I"))
    private int legacyfixes$getExperiencePoints(EntityLivingBase instance, EntityPlayer p_70693_1_) {
        return (LegacyFixesConfig.keepXP && this.isPlayer()) ? 0 : this.getExperiencePoints(this.attackingPlayer);
    }
}
