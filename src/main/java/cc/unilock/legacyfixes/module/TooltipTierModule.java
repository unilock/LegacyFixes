package cc.unilock.legacyfixes.module;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemTool;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class TooltipTierModule {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onItemTooltip(ItemTooltipEvent event) {
        if (GuiScreen.isShiftKeyDown()) {
            if (event.itemStack.getItem() instanceof ItemTool tool) {
                int harvestLevel = tool.func_150913_i().getHarvestLevel();
                event.toolTip.add("§8Tier: " + switch (harvestLevel) {
                    case 0 -> "0 (wood)";
                    case 1 -> "1 (stone)";
                    case 2 -> "2 (iron)";
                    case 3 -> "3 (diamond)";
                    case 4 -> "4 (obsidian)";
                    case 5 -> "5 (cobalt)";
                    case 6 -> "6 (manyullyn)";
                    default -> harvestLevel + " (silly)";
                } + "§r");
            }
        }
    }
}
