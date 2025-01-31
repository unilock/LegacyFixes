package cc.unilock.legacyfixes;

import com.gtnewhorizon.gtnhlib.config.ConfigException;
import com.gtnewhorizon.gtnhlib.config.ConfigurationManager;
import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@IFMLLoadingPlugin.MCVersion("1.7.10")
public class EarlyMixinLoader implements IFMLLoadingPlugin, IEarlyMixinLoader {
    static {
        try {
            ConfigurationManager.registerConfig(LegacyFixesConfig.class);
        } catch (ConfigException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getMixinConfig() {
        return "mixins.legacyfixes.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        List<String> mixins = new ArrayList<>();

        if (LegacyFixesConfig.apatheticMobs) {
            mixins.add("apatheticMobs.EntityCreatureMultiMixin");
            mixins.add("apatheticMobs.EntityEndermanMixin");
            mixins.add("apatheticMobs.EntityLivingBaseMixin");
            mixins.add("apatheticMobs.EntityLivingMixin");
            mixins.add("apatheticMobs.EntityPigZombieMixin");
        }
        if (LegacyFixesConfig.bedSpawnFix) {
            mixins.add("bedSpawnFix.BlockBedMixin");
            mixins.add("bedSpawnFix.EntityPlayerAccessor");
        }
        if (LegacyFixesConfig.hungerless) {
            if (loadedCoreMods.contains("squeek.applecore.AppleCore")) {
                LegacyFixes.LOGGER.error("LegacyFixes failed to enable hungerless with AppleCore installed!");
            } else {
                mixins.add("hungerless.FoodStatsMixin");
            }
        }
        if (LegacyFixesConfig.jumpClimbing) {
            if (LegacyFixesConfig.slideClimbing) {
                LegacyFixes.LOGGER.error("LegacyFixes failed to enable jumpClimbing with slideClimbing enabled!");
            } else {
                mixins.add("jumpClimbing.EntityLivingBaseMixin");
            }
        }
        if (LegacyFixesConfig.keepXP) {
            mixins.add("keepXP.EntityLivingBaseMixin");
            mixins.add("keepXP.EntityPlayerMixin");
        }
        if (LegacyFixesConfig.mc5694Fix) {
            mixins.add("mc5694Fix.ItemInWorldManagerMixin");
        }
        if (LegacyFixesConfig.mineWoodFix) {
            mixins.add("mineWoodFix.EntityItemMixin");
        }
        if (LegacyFixesConfig.noTrample) {
            mixins.add("noTrample.BlockFarmlandMixin");
        }
        if (LegacyFixesConfig.slideClimbing) {
            if (LegacyFixesConfig.jumpClimbing) {
                LegacyFixes.LOGGER.error("LegacyFixes failed to enable slideClimbing with jumpClimbing enabled!");
            } else {
                mixins.add("slideClimbing.EntityLivingBaseMixin");
            }
        }

        if (FMLLaunchHandler.side().isClient()) {
            if (LegacyFixesConfig.chatLinebreakFix) {
                mixins.add("client.chatLinebreakFix.GuiNewChatMixin");
            }
            if (LegacyFixesConfig.nnbspFix) {
                mixins.add("client.nnbspFix.FontRendererMixin");
            }
            if (LegacyFixesConfig.rmbClear) {
                mixins.add("client.rmbClear.GuiTextFieldMixin");
            }
            if (LegacyFixesConfig.sortedEnchantments) {
                mixins.add("client.sortedEnchantments.ItemEnchantedBookMixin");
                mixins.add("client.sortedEnchantments.ItemStackMixin");
                mixins.add("client.sortedEnchantments.NBTTagListAccessor");
            }
        }

        return mixins;
    }

    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        // NO-OP
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
