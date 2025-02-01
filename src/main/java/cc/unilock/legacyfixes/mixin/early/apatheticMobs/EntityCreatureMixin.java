package cc.unilock.legacyfixes.mixin.early.apatheticMobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityCreature.class)
public class EntityCreatureMixin {
    @Inject(method = "setTarget(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
    private void legacyfixes$setTarget(Entity p_70784_1_, CallbackInfo ci) {
        if (p_70784_1_ instanceof EntityPlayer) {
            ci.cancel();
        }
    }
}
