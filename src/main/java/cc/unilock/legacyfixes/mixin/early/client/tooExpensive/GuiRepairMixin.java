package cc.unilock.legacyfixes.mixin.early.client.tooExpensive;

import net.minecraft.client.gui.GuiRepair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiRepair.class)
public class GuiRepairMixin {
    @ModifyConstant(method = "drawGuiContainerForegroundLayer", constant = @Constant(intValue = 40))
    private int legacyfixes$drawGuiContainerForegroundLayer(int constant) {
        return Integer.MAX_VALUE;
    }
}
