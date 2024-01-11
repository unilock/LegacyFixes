package cc.unilock.legacyfixes.mixin.client;

import cc.unilock.legacyfixes.LegacyFixesConfig;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FontRenderer.class)
public class FontRendererMixin {
    @ModifyExpressionValue(method = "Lnet/minecraft/client/gui/FontRenderer;renderStringAtPos(Ljava/lang/String;Z)V", at = @At(value = "INVOKE", target = "Ljava/lang/String;charAt(I)C", ordinal = 0))
    private char legacyfixes$charAt(char c) {
        if (LegacyFixesConfig.nnbspFix) {
            if (c == '\u202F') c = ' '; // NARROW NO-BREAK SPACE
        }
        return c;
    }
}
