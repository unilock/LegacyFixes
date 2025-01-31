package cc.unilock.legacyfixes.mixin.early.hungerless;

import net.minecraft.util.FoodStats;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FoodStats.class)
public class FoodStatsMixin {
    @Redirect(method = "onUpdate(Lnet/minecraft/entity/player/EntityPlayer;)V", at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;difficultySetting:Lnet/minecraft/world/EnumDifficulty;", opcode = Opcodes.GETFIELD), require = 0)
    private EnumDifficulty legacyfixes$difficultySetting(World instance) {
        return EnumDifficulty.PEACEFUL;
    }
}
