package cc.unilock.legacyfixes;

import cc.unilock.legacyfixes.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = "legacyfixes", version = Tags.VERSION, name = "LegacyFixes", dependencies = "required-after:gtnhlib@[0.2.0,);")
public class LegacyFixes {
    public static final Logger LOGGER = LogManager.getLogger("LegacyFixes");
    public static int logWood;

    @SidedProxy(clientSide = "cc.unilock.legacyfixes.proxy.ClientProxy", serverSide = "cc.unilock.legacyfixes.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
        proxy.loadComplete(event);
    }
}
