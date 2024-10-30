package cc.unilock.legacyfixes.util;

import cc.unilock.legacyfixes.mixin.early.client.sortedEnchantments.NBTTagListAccessor;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.stream.Stream;

public final class NBTUtils {
    public static Stream<EnchantmentCompound> sort(NBTTagList listTag) {
        return ((NBTTagListAccessor) listTag).getTagList().stream().map(EnchantmentCompound::new).sorted(Comparator.comparing(EnchantmentCompound::getTranslatedName));
    }

    public static NBTTagList toListTag(Stream<EnchantmentCompound> stream) {
        NBTTagList listTag = new NBTTagList();
        stream.forEachOrdered(tag -> listTag.appendTag(tag.asCompoundTag()));
        return listTag;
    }

    public static class EnchantmentCompound {
        @Nonnull private final NBTTagCompound compound;
        private final Enchantment enchantment;
        private String translatedName = null;

        public EnchantmentCompound(@Nonnull NBTBase tag) {
            if (tag.getId() != (byte) 10) {
                throw new AssertionError("tag is not a NBTTagCompound");
            }

            this.compound = (NBTTagCompound) tag;

            int identifier = compound.getInteger("id");
            this.enchantment = Enchantment.enchantmentsList[identifier];

            // Items can have non-registered enchantment tags on them
            if (identifier == 0 || enchantment == null) {
                // dummy comparison values
                this.translatedName = "";
            }
        }

        private void lazyInit() {
            this.translatedName = StatCollector.translateToLocal(this.enchantment.getName());
        }

        @Nonnull
        public NBTTagCompound asCompoundTag() {
            return compound;
        }

        @Nonnull
        public String getTranslatedName() {
            if (this.translatedName == null) {
                lazyInit();
            }

            return translatedName;
        }
    }
}
