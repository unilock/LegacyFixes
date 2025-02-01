package cc.unilock.legacyfixes.mixin.early.apatheticMobs;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import org.objectweb.asm.Opcodes;
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

    @WrapWithCondition(method = "attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/monster/EntityEnderman;setScreaming(Z)V"))
    private boolean legacyfixes$attackEntityFrom$setScreaming(EntityEnderman instance, boolean p_70819_1_, DamageSource source) {
        return !(source.getEntity() instanceof EntityPlayer);
    }

    @WrapWithCondition(method = "attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/monster/EntityEnderman;isAggressive:Z", opcode = Opcodes.PUTFIELD, ordinal = 0))
    private boolean legacyfixes$attackEntityFrom$isAggressive(EntityEnderman instance, boolean value, DamageSource source) {
        return !(source.getEntity() instanceof EntityPlayer);
    }
}
