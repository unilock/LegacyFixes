package cc.unilock.legacyfixes.mixin.early.apatheticMobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPigZombie.class)
public class EntityPigZombieMixin {
    @Inject(method = "Lnet/minecraft/entity/monster/EntityPigZombie;becomeAngryAt(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
    private void legacyfixes$becomeAngryAt(Entity p_70835_1_, CallbackInfo ci) {
        if (p_70835_1_ instanceof EntityPlayer) {
            ci.cancel();
        }
    }
}
