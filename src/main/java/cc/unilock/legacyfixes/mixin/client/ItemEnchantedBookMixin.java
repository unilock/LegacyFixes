package cc.unilock.legacyfixes.mixin.client;

import cc.unilock.legacyfixes.LegacyFixesConfig;
import cc.unilock.legacyfixes.util.NBTUtils;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEnchantedBook.class)
public class ItemEnchantedBookMixin {
    @Inject(method = "Lnet/minecraft/item/ItemEnchantedBook;func_92110_g(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/nbt/NBTTagList;", at = @At("RETURN"), cancellable = true)
    private void legacyfixes$func_92110_g(ItemStack stack, CallbackInfoReturnable<NBTTagList> cir) {
        if (LegacyFixesConfig.sortedEnchantments && cir.getReturnValue() != null)
            cir.setReturnValue(NBTUtils.toListTag(NBTUtils.sort(cir.getReturnValue())));
    }
}
