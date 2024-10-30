package cc.unilock.legacyfixes;

import com.gtnewhorizon.gtnhlib.config.Config;

@Config(modid = "legacyfixes")
@Config.RequiresMcRestart
public class LegacyFixesConfig {
    @Config.Comment("Prevents mobs from attacking / targeting players")
    @Config.DefaultBoolean(false)
    public static boolean apatheticMobs;

    @Config.Comment("Allows beds to set a player's spawn point during the day (as in 1.15+)")
    @Config.DefaultBoolean(true)
    public static boolean bedSpawnFix;

    @Config.Comment("Fixes line breaks in chat not rendering properly, but breaks certain formatting in fixed chat messages")
    @Config.DefaultBoolean(false)
    public static boolean chatLinebreakFix;

    @Config.Comment("Makes double doors open simultaneously")
    @Config.DefaultBoolean(true)
    public static boolean doubleDoors;

    @Config.Comment("Makes the hunger system always act as if the difficulty is set to Peaceful (incompat with AppleCore)")
    @Config.DefaultBoolean(false)
    public static boolean hungerless;

    @Config.Comment("Allows climbing ladders by jumping (incompat with slideClimbing)")
    @Config.DefaultBoolean(true)
    public static boolean jumpClimbing;

    @Config.Comment("Players keep their experience level / points on death")
    @Config.DefaultBoolean(false)
    public static boolean keepXP;

    @Config.Comment("Try to fix MC-5694 (\"High efficiency tools / fast mining destroys some blocks client-side only\")")
    @Config.DefaultBoolean(true)
    public static boolean mc5694Fix;

    @Config.Comment("Fixes the \"NNBSP\" character in DateFormat outputs in Java 20+, as in the singleplayer world selection menu")
    @Config.DefaultBoolean(true)
    public static boolean nnbspFix;

    @Config.Comment("Prevents trampling farmland (completely)")
    @Config.DefaultBoolean(false)
    public static boolean noTrample;

    @Config.Comment("Allows clearing text fields by right-clicking them")
    @Config.DefaultBoolean(true)
    public static boolean rmbClear;

    @Config.Comment("Allows traversing ladders by looking up or down (incompat with jumpClimbing)")
    @Config.DefaultBoolean(false)
    public static boolean slideClimbing;

    @Config.Comment("Sorts enchantments in item tooltips (alphabetically)")
    @Config.DefaultBoolean(true)
    public static boolean sortedEnchantments;
}
