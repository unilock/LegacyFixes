package cc.unilock.legacyfixes.mixin.early.apatheticMobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.entity.monster.EntitySpider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({EntityCreature.class, EntityEnderman.class, EntityMob.class, EntityPigZombie.class, EntitySilverfish.class, EntitySpider.class})
public class EntityCreatureMultiMixin {
    @Inject(method = "findPlayerToAttack()Lnet/minecraft/entity/Entity;", at = @At("HEAD"), cancellable = true)
    private void legacyfixes$findPlayerToAttack(CallbackInfoReturnable<Entity> cir) {
        cir.setReturnValue(null);
    }
}
