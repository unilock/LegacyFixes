package cc.unilock.legacyfixes.mixin.early.mineWoodFix;

import cc.unilock.legacyfixes.LegacyFixes;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.oredict.OreDictionary;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityItem.class)
public class EntityItemMixin {
    @Inject(method = "onCollideWithPlayer(Lnet/minecraft/entity/player/EntityPlayer;)V", at = @At(value = "INVOKE", target = "Lcpw/mods/fml/common/FMLCommonHandler;firePlayerItemPickupEvent(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/entity/item/EntityItem;)V"))
    private void legacyfixes$onCollideWithPlayer(EntityPlayer entityIn, CallbackInfo ci, @Local ItemStack itemstack) {
        if (entityIn instanceof EntityPlayerMP entityPlayerMP && !(entityIn instanceof FakePlayer) && !entityPlayerMP.func_147099_x().hasAchievementUnlocked(AchievementList.mineWood)) {
            for (int id : OreDictionary.getOreIDs(itemstack)) {
                if (LegacyFixes.logWood == id) {
                    entityIn.triggerAchievement(AchievementList.mineWood);
                    break;
                }
            }
        }
    }
}
