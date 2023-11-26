package cc.unilock.legacyfixes;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class LegacyFixesConfig {
    public static boolean bedSpawnFix = true;
    public static boolean jumpClimbing = true;
    public static boolean noTrample = false;
    public static boolean rmbClear = true;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        bedSpawnFix = configuration.getBoolean("bedSpawnFix", Configuration.CATEGORY_GENERAL, bedSpawnFix, "Allows beds to set a player's spawn point during the day (as in 1.15+)");
        jumpClimbing = configuration.getBoolean("jumpClimbing", Configuration.CATEGORY_GENERAL, jumpClimbing, "Allows climbing ladders by jumping");
        noTrample = configuration.getBoolean("noTrample", Configuration.CATEGORY_GENERAL, noTrample, "Prevents trampling farmland (completely)");
        rmbClear = configuration.getBoolean("rmbClear", Configuration.CATEGORY_GENERAL, rmbClear, "Allows clearing text fields by right-clicking them");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
