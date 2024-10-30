package cc.unilock.legacyfixes.mixin;

import cc.unilock.legacyfixes.LegacyFixesConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.ai.EntityAITarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityAITarget.class)
public class EntityAITargetMixin {
    @ModifyExpressionValue(method = "Lnet/minecraft/entity/ai/EntityAITarget;continueExecuting()Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/management/ItemInWorldManager;isCreative()Z"))
    private boolean legacyfixes$isCreative(boolean original) {
        return original || LegacyFixesConfig.apatheticMobs;
    }
}
