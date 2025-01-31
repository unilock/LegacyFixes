package cc.unilock.legacyfixes.mixin.early.keepXP;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public class EntityPlayerMixin {
    @Shadow
    public int experienceLevel;

    @Shadow
    public int experienceTotal;

    @Shadow
    public float experience;

    @Inject(method = "clonePlayer(Lnet/minecraft/entity/player/EntityPlayer;Z)V", at = @At("HEAD"))
    private void legacyfixes$clonePlayer(EntityPlayer p_71049_1_, boolean p_71049_2_, CallbackInfo ci) {
        this.experienceLevel = p_71049_1_.experienceLevel;
        this.experienceTotal = p_71049_1_.experienceTotal;
        this.experience = p_71049_1_.experience;
    }
}
