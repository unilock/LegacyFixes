package cc.unilock.legacyfixes.mixin.client;

import cc.unilock.legacyfixes.LegacyFixesConfig;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiTextField.class)
public abstract class GuiTextFieldMixin {
    @Shadow
    public int xPosition;
    @Shadow
    public int yPosition;
    @Shadow
    public int width;
    @Shadow
    public int height;
    @Shadow
    private boolean canLoseFocus;
    @Shadow
    private boolean isFocused;
    @Shadow
    private boolean isEnabled;
    @Shadow
    private boolean visible;

    @Shadow
    public abstract void setText(String text);

    @Inject(method = "Lnet/minecraft/client/gui/GuiTextField;mouseClicked(III)V", at = @At(value = "HEAD"), cancellable = true)
    private void legacyfixes$mouseClicked(int mouseX, int mouseY, int button, CallbackInfo ci) {
        if (!LegacyFixesConfig.rmbClear) return;
        if (button == 1 && this.visible && this.isEnabled && (this.isFocused || this.canLoseFocus) && (mouseX >= this.xPosition && mouseX < this.xPosition + this.width && mouseY >= this.yPosition && mouseY < this.yPosition + this.height)) {
            this.setText("");
            ci.cancel();
        }
    }
}
