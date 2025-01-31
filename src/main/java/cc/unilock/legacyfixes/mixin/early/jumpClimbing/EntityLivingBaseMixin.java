package cc.unilock.legacyfixes.mixin.early.jumpClimbing;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.EntityLivingBase;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin {
    @Shadow
    protected boolean isJumping;

    @ModifyExpressionValue(method = "moveEntityWithHeading(FF)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;isCollidedHorizontally:Z", opcode = Opcodes.GETFIELD, ordinal = 2))
    private boolean legacyfixes$isCollidedHorizontally(boolean original) {
        return original || this.isJumping;
    }
}
