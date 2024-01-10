package cc.unilock.legacyfixes.mixin.client;

import cc.unilock.legacyfixes.LegacyFixesConfig;
import cc.unilock.legacyfixes.util.NBTUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "Lnet/minecraft/item/ItemStack;getEnchantmentTagList()Lnet/minecraft/nbt/NBTTagList;", at = @At("RETURN"), cancellable = true)
    private void legacyfixes$getEnchantmentTagList(CallbackInfoReturnable<NBTTagList> cir) {
        if (LegacyFixesConfig.sortedEnchantments && cir.getReturnValue() != null)
            cir.setReturnValue(NBTUtils.toListTag(NBTUtils.sort(cir.getReturnValue())));
    }
}
