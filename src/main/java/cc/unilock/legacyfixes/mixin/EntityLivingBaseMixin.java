package cc.unilock.legacyfixes.mixin;

import cc.unilock.legacyfixes.LegacyFixesConfig;
import net.minecraft.entity.EntityLivingBase;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityLivingBase.class)
public class EntityLivingBaseMixin {
    @Redirect(method = "Lnet/minecraft/entity/EntityLivingBase;moveEntityWithHeading(FF)V", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/EntityLivingBase;isCollidedHorizontally:Z", opcode = Opcodes.GETFIELD, ordinal = 2))
    private boolean isCollidedHorizontally(EntityLivingBase instance) {
        return instance.isCollidedHorizontally || LegacyFixesConfig.jumpClimbing && ((EntityLivingBaseAccessor) instance).getIsJumping();
    }
}
