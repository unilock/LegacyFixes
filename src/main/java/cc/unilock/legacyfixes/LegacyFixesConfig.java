package cc.unilock.legacyfixes;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class LegacyFixesConfig {
    public static boolean apatheticMobs = false;
    public static boolean bedSpawnFix = true;
    public static boolean chatLinebreakFix = false;
    public static boolean doubleDoors = true;
    public static boolean jumpClimbing = true;
    public static boolean keepXP = false;
    public static boolean mc5694Fix = true;
    public static boolean nnbspFix = true;
    public static boolean noTrample = false;
    public static boolean rmbClear = true;
    public static boolean slideClimbing = false;
    public static boolean sortedEnchantments = true;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        apatheticMobs = configuration.getBoolean("apatheticMobs", Configuration.CATEGORY_GENERAL, apatheticMobs, "Prevents mobs from attacking / targeting players");
        bedSpawnFix = configuration.getBoolean("bedSpawnFix", Configuration.CATEGORY_GENERAL, bedSpawnFix, "Allows beds to set a player's spawn point during the day (as in 1.15+)");
        chatLinebreakFix = configuration.getBoolean("chatLinebreakFix", Configuration.CATEGORY_GENERAL, chatLinebreakFix, "Fixes line breaks in chat not rendering properly, but breaks certain formatting in fixed chat messages");
        doubleDoors = configuration.getBoolean("doubleDoors", Configuration.CATEGORY_GENERAL, doubleDoors, "Makes double doors open simultaneously");
        jumpClimbing = configuration.getBoolean("jumpClimbing", Configuration.CATEGORY_GENERAL, jumpClimbing, "Allows climbing ladders by jumping (incompat with slideClimbing)");
        keepXP = configuration.getBoolean("keepXP", Configuration.CATEGORY_GENERAL, keepXP, "Players keep their experience level / points on death");
        mc5694Fix = configuration.getBoolean("mc5694Fix", Configuration.CATEGORY_GENERAL, mc5694Fix, "Fix MC-5694 (\"High efficiency tools / fast mining destroys some blocks client-side only\")");
        noTrample = configuration.getBoolean("noTrample", Configuration.CATEGORY_GENERAL, noTrample, "Prevents trampling farmland (completely)");
        nnbspFix = configuration.getBoolean("nnbspFix", Configuration.CATEGORY_GENERAL, nnbspFix, "Fixes the \"NNBSP\" character in DateFormat outputs in Java 20+, as in the singleplayer world selection menu");
        rmbClear = configuration.getBoolean("rmbClear", Configuration.CATEGORY_GENERAL, rmbClear, "Allows clearing text fields by right-clicking them");
        slideClimbing = configuration.getBoolean("slideClimbing", Configuration.CATEGORY_GENERAL, slideClimbing, "Allows traversing ladders by looking up or down (incompat with jumpClimbing)");
        sortedEnchantments = configuration.getBoolean("sortedEnchantments", Configuration.CATEGORY_GENERAL, sortedEnchantments, "Sorts enchantments in item tooltips (alphabetically)");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
