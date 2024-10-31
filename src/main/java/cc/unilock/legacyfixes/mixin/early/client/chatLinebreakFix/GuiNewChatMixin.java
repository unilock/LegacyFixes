package cc.unilock.legacyfixes.mixin.early.client.chatLinebreakFix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GuiNewChat.class)
public class GuiNewChatMixin {
    @WrapOperation(method = "Lnet/minecraft/client/gui/GuiNewChat;printChatMessage(Lnet/minecraft/util/IChatComponent;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiNewChat;printChatMessageWithOptionalDeletion(Lnet/minecraft/util/IChatComponent;I)V"))
    private void legacyfixes$printChatMessageWithOptionalDeletion(GuiNewChat instance, IChatComponent component, int chatLineID, Operation<Void> original) {
        if (component.getFormattedText().contains("\n")) {
            for (String msg : component.getFormattedText().split("\n")) {
                original.call(instance, new ChatComponentText(msg), chatLineID);
            }
        } else {
            original.call(instance, component, chatLineID);
        }
    }
}
