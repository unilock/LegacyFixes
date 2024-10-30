package cc.unilock.legacyfixes.mixin.early.client.sortedEnchantments;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(NBTTagList.class)
public interface NBTTagListAccessor {
    @Accessor
    List<NBTBase> getTagList();
}
