package cc.unilock.legacyfixes.proxy;

import cc.unilock.legacyfixes.LegacyFixes;
import cc.unilock.legacyfixes.LegacyFixesConfig;
import cc.unilock.legacyfixes.module.DoubleDoorsModule;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        LegacyFixesConfig.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        if (LegacyFixesConfig.doubleDoors) {
            MinecraftForge.EVENT_BUS.register(new DoubleDoorsModule());
        }

        LegacyFixes.LOGGER.info("LegacyFixes pre-initialized!");
    }
}
