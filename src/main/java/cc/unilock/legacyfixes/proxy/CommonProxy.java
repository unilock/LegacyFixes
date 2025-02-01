package cc.unilock.legacyfixes.proxy;

import cc.unilock.legacyfixes.LegacyFixes;
import cc.unilock.legacyfixes.LegacyFixesConfig;
import cc.unilock.legacyfixes.module.DoubleDoorsModule;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        if (LegacyFixesConfig.doubleDoors) {
            MinecraftForge.EVENT_BUS.register(new DoubleDoorsModule());
        }
    }

    public void loadComplete(FMLLoadCompleteEvent event) {
        if (LegacyFixesConfig.mineWoodFix) {
            LegacyFixes.logWood = OreDictionary.getOreID("logWood");
        }
    }
}
