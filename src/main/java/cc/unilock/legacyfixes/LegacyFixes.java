package cc.unilock.legacyfixes;

import cc.unilock.legacyfixes.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = "legacyfixes", version = Tags.VERSION, name = "LegacyFixes", acceptedMinecraftVersions = "[1.7.10]")
public class LegacyFixes {
    public static final Logger LOGGER = LogManager.getLogger("LegacyFixes");

    @SidedProxy(clientSide = "cc.unilock.legacyfixes.proxy.ClientProxy", serverSide = "cc.unilock.legacyfixes.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }
}
