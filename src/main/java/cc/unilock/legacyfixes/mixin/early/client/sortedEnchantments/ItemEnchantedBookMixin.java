package cc.unilock.legacyfixes.mixin.early.client.sortedEnchantments;

import cc.unilock.legacyfixes.util.NBTUtils;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.nbt.NBTTagList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemEnchantedBook.class)
public class ItemEnchantedBookMixin {
    @ModifyReturnValue(method = "func_92110_g(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/nbt/NBTTagList;", at = @At("RETURN"))
    private NBTTagList legacyfixes$func_92110_g(NBTTagList original) {
        return original == null ? null : NBTUtils.toListTag(NBTUtils.sort(original));
    }
}
