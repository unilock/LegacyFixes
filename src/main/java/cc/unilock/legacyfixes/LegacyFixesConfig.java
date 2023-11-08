package cc.unilock.legacyfixes;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class LegacyFixesConfig {
    public static boolean jumpClimbing = true;
    public static boolean rmbClear = true;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        jumpClimbing = configuration.getBoolean("jumpClimbing", Configuration.CATEGORY_GENERAL, jumpClimbing, "Allows climbing ladders by jumping");
        rmbClear = configuration.getBoolean("rmbClear", Configuration.CATEGORY_GENERAL, rmbClear, "Allows clearing text fields by right-clicking");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
