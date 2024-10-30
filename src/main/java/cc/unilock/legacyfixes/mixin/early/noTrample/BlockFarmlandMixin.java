package cc.unilock.legacyfixes.mixin.early.noTrample;

import cc.unilock.legacyfixes.LegacyFixesConfig;
import net.minecraft.block.BlockFarmland;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockFarmland.class)
public class BlockFarmlandMixin {
    @Inject(method = "Lnet/minecraft/block/BlockFarmland;onFallenUpon(Lnet/minecraft/world/World;IIILnet/minecraft/entity/Entity;F)V", at = @At("HEAD"), cancellable = true)
    private void legacyfixes$onFallenUpon(World worldIn, int x, int y, int z, Entity entityIn, float fallDistance, CallbackInfo ci) {
        if (LegacyFixesConfig.noTrample) ci.cancel();
    }
}
