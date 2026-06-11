package cc.unilock.legacyfixes;

import com.gtnewhorizon.gtnhlib.config.Config;

@Config(modid = "legacyfixes")
@Config.RequiresMcRestart
public class LegacyFixesConfig {
    @Config.Comment("Prevents mobs from attacking / targeting players [server]")
    @Config.DefaultBoolean(false)
    public static boolean apatheticMobs;

    @Config.Comment("Fixes line breaks in chat not rendering properly, but breaks certain formatting in fixed chat messages [client]")
    @Config.DefaultBoolean(false)
    public static boolean chatLinebreakFix;

    @Config.Comment("Makes double doors open simultaneously [server]")
    @Config.DefaultBoolean(true)
    public static boolean doubleDoors;

    @Config.Comment("Allows climbing ladders by jumping (incompat with slideClimbing) [client]")
    @Config.DefaultBoolean(true)
    public static boolean jumpClimbing;

    @Config.Comment("Players keep their experience level / points on death [server]")
    @Config.DefaultBoolean(false)
    public static boolean keepXP;

    @Config.Comment("Grant the \"Getting Wood\" achievement for any log, including modded [server]")
    @Config.DefaultBoolean(true)
    public static boolean mineWoodFix;

    @Config.Comment("Fixes the \"NNBSP\" character in DateFormat outputs in Java 20+, as in the singleplayer world selection menu [client]")
    @Config.DefaultBoolean(true)
    public static boolean nnbspFix;

    @Config.Comment("Prevents trampling farmland (completely) [server]")
    @Config.DefaultBoolean(false)
    public static boolean noTrample;

    @Config.Comment("Allows clearing text fields by right-clicking them [client]")
    @Config.DefaultBoolean(true)
    public static boolean rmbClear;

    @Config.Comment("Allows traversing ladders by looking up or down (incompat with jumpClimbing) [client]")
    @Config.DefaultBoolean(false)
    public static boolean slideClimbing;

    @Config.Comment("Sorts enchantments in item tooltips (alphabetically) [client]")
    @Config.DefaultBoolean(true)
    public static boolean sortedEnchantments;

    @Config.Comment("Prevents \"Too Expensive!\" from appearing in vanilla anvils, allowing very expensive enchanting or repairing [both]")
    @Config.DefaultBoolean(false)
    public static boolean tooExpensive;

    @Config.Comment("Shows the harvest level (tier) of a tool in its tooltip when holding SHIFT [client-ish]")
    @Config.DefaultBoolean(false)
    public static boolean tooltipTier;
}
