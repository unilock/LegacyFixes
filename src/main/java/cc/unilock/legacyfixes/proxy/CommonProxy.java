package cc.unilock.legacyfixes.proxy;

import cc.unilock.legacyfixes.LegacyFixesConfig;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        LegacyFixesConfig.synchronizeConfiguration(event.getSuggestedConfigurationFile());
        //LegacyFixes.LOGGER.info("Initialized!");
    }
}
