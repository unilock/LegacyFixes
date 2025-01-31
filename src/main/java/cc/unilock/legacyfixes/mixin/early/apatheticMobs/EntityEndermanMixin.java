package cc.unilock.legacyfixes.mixin.early.apatheticMobs;

import net.minecraft.entity.monster.EntityEnderman;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityEnderman.class)
public class EntityEndermanMixin {
    @Inject(method = "shouldAttackPlayer(Lnet/minecraft/entity/player/EntityPlayer;)Z", at = @At("HEAD"), cancellable = true)
    private void legacyfixes$shouldAttackPlayer(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
