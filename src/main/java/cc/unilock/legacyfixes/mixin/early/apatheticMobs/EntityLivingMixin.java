package cc.unilock.legacyfixes.mixin.early.apatheticMobs;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityLiving.class)
public abstract class EntityLivingMixin extends Entity {
    public EntityLivingMixin(World worldIn) {
        super(worldIn);
        throw new AssertionError("EntityLivingMixin instantiated!?");
    }

    @Inject(method = "Lnet/minecraft/entity/EntityLiving;setAttackTarget(Lnet/minecraft/entity/EntityLivingBase;)V", at = @At("HEAD"), cancellable = true)
    private void legacyfixes$setAttackTarget(CallbackInfo ci) {
        ci.cancel();
    }

    @ModifyReturnValue(method = "Lnet/minecraft/entity/EntityLiving;canAttackClass(Ljava/lang/Class;)Z", at = @At("RETURN"))
    private boolean legacyfixes$canAttackClass(boolean original, Class<? extends Entity> p_70686_1_) {
        return original && !EntityPlayer.class.isAssignableFrom(p_70686_1_);
    }
}
