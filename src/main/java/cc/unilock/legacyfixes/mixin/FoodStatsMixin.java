package cc.unilock.legacyfixes.mixin;

import cc.unilock.legacyfixes.LegacyFixesConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.util.FoodStats;
import net.minecraft.world.EnumDifficulty;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FoodStats.class)
public class FoodStatsMixin {
    @ModifyExpressionValue(method = "Lnet/minecraft/util/FoodStats;onUpdate(Lnet/minecraft/entity/player/EntityPlayer;)V", at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;difficultySetting:Lnet/minecraft/world/EnumDifficulty;", opcode = Opcodes.GETFIELD))
    private EnumDifficulty legacyfixes$difficultySetting(EnumDifficulty original) {
        return LegacyFixesConfig.hungerless ? EnumDifficulty.PEACEFUL : original;
    }
}
