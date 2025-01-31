package cc.unilock.legacyfixes.mixin.early.client.sortedEnchantments;

import cc.unilock.legacyfixes.util.NBTUtils;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @ModifyReturnValue(method = "getEnchantmentTagList()Lnet/minecraft/nbt/NBTTagList;", at = @At("RETURN"))
    private NBTTagList legacyfixes$getEnchantmentTagList(NBTTagList original) {
        return original == null ? null : NBTUtils.toListTag(NBTUtils.sort(original));
    }
}
