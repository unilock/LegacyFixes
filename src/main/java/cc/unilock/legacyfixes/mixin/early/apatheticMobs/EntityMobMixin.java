package cc.unilock.legacyfixes.mixin.early.apatheticMobs;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EntityMob.class)
public class EntityMobMixin {
    @WrapWithCondition(method = "attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/monster/EntityMob;entityToAttack:Lnet/minecraft/entity/Entity;", opcode = Opcodes.PUTFIELD))
    private boolean legacyfixes$attackEntityFrom(EntityMob instance, Entity value) {
        return !(value instanceof EntityPlayer);
    }
}
