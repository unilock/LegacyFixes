package cc.unilock.legacyfixes;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class LegacyFixesConfig {
    public static boolean bedSpawnFix = true;
    public static boolean doubleDoors = true;
    public static boolean jumpClimbing = true;
    public static boolean keepXP = false;
    public static boolean noTrample = false;
    public static boolean rmbClear = true;
    public static boolean slideClimbing = false;
    public static boolean sortedEnchantments = true;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        bedSpawnFix = configuration.getBoolean("bedSpawnFix", Configuration.CATEGORY_GENERAL, bedSpawnFix, "Allows beds to set a player's spawn point during the day (as in 1.15+)");
        doubleDoors = configuration.getBoolean("doubleDoors", Configuration.CATEGORY_GENERAL, doubleDoors, "Makes double doors open simultaneously");
        jumpClimbing = configuration.getBoolean("jumpClimbing", Configuration.CATEGORY_GENERAL, jumpClimbing, "Allows climbing ladders by jumping (incompat with slideClimbing)");
        keepXP = configuration.getBoolean("keepXP", Configuration.CATEGORY_GENERAL, keepXP, "Players keep their experience level / points on death");
        noTrample = configuration.getBoolean("noTrample", Configuration.CATEGORY_GENERAL, noTrample, "Prevents trampling farmland (completely)");
        rmbClear = configuration.getBoolean("rmbClear", Configuration.CATEGORY_GENERAL, rmbClear, "Allows clearing text fields by right-clicking them");
        slideClimbing = configuration.getBoolean("slideClimbing", Configuration.CATEGORY_GENERAL, slideClimbing, "Allows traversing ladders by looking up or down (incompat with jumpClimbing)");
        sortedEnchantments = configuration.getBoolean("sortedEnchantments", Configuration.CATEGORY_GENERAL, sortedEnchantments, "Sorts enchantments in item tooltips (alphabetically)");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
