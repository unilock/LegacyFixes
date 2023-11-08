package cc.unilock.legacyfixes;

import cc.unilock.legacyfixes.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.7.10]")
public class LegacyFixes {
    //public static final Logger LOGGER = LogManager.getLogger(Tags.MODID);

    @SidedProxy(clientSide = "cc.unilock.legacyfixes.proxy.ClientProxy", serverSide = "cc.unilock.legacyfixes.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }
}
