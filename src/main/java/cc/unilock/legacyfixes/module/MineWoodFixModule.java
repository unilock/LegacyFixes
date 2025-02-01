package cc.unilock.legacyfixes.module;

import cc.unilock.legacyfixes.LegacyFixes;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.oredict.OreDictionary;

public class MineWoodFixModule {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onItemPickup(PlayerEvent.ItemPickupEvent event) {
        if (event.player instanceof EntityPlayerMP entityPlayerMP && !(entityPlayerMP instanceof FakePlayer) && !entityPlayerMP.func_147099_x().hasAchievementUnlocked(AchievementList.mineWood)) {
            for (int id : OreDictionary.getOreIDs(event.pickedUp.getEntityItem())) {
                if (LegacyFixes.logWood == id) {
                    entityPlayerMP.triggerAchievement(AchievementList.mineWood);
                    break;
                }
            }
        }
    }
}
