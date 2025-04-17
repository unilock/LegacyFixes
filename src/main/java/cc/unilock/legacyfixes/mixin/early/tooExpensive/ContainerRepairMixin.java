package cc.unilock.legacyfixes.mixin.early.tooExpensive;

import net.minecraft.inventory.ContainerRepair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerRepair.class)
public class ContainerRepairMixin {
    @ModifyConstant(method = "updateRepairOutput", constant = @Constant(intValue = 40))
    private int legacyfixes$updateRepairOutput(int constant) {
        return Integer.MAX_VALUE;
    }
}
