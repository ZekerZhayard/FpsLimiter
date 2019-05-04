package io.github.zekerzhayard.fpslimiter.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigLoader {
    private static Configuration config;
    private static int fpslimiteramount = 5;
    
    public static void setConfig(File file) {
        ConfigLoader.config = new Configuration(file);
    }
    
    public static Configuration getConfig() {
        return ConfigLoader.config;
    }
    
    public static void loadConfig() {
        ConfigLoader.config.load();
        ConfigLoader.saveConfig();
    }
    
    public static void saveConfig() {
        ConfigLoader.config.get(Configuration.CATEGORY_GENERAL, "fpslimiteramount", 5, null, 5, Integer.MAX_VALUE).set(ConfigLoader.fpslimiteramount);
        ConfigLoader.config.save();
    }
    
    public static int getFpslimiteramount() {
        return ConfigLoader.fpslimiteramount;
    }
    
    public static void setFpslimiteramount(int amount) {
        ConfigLoader.fpslimiteramount = amount;
    }
}
