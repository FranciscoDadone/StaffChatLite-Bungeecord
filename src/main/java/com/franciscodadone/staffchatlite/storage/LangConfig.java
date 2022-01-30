package com.franciscodadone.staffchatlite.storage;

import com.franciscodadone.staffchatlite.StaffChatLite;
import com.franciscodadone.staffchatlite.util.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LangConfig {
    protected LangConfig() {
        this.plugin = Global.plugin;
        this.langFileStr = Global.plugin.getConfig().getString("lang-file");
        saveDefaultConfig();
    }

    /**
     * Reloads a user config.
     */
    protected void reloadConfig() {
        if(this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder() + File.separator + "lang", langFileStr);
        }
        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defaultStream = this.plugin.getResource(langFileStr);

        if(defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    /**
     * Gets a user config.
     * @return FileConfiguration
     */
    protected FileConfiguration getConfig() {
        if(this.dataConfig == null) {
            reloadConfig();
        }
        return this.dataConfig;
    }

    /**
     * Saves a user config.
     */
    protected void saveConfig() {
        if(this.dataConfig == null || this.configFile == null) {
            return;
        }
        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            Logger.severe("Could not save config to " + this.configFile);
        }
    }

    /**
     * Saves a new user config.
     */
    protected void saveDefaultConfig() {

        if(this.configFile == null) {
            this.configFile = new File(
                    this.plugin.getDataFolder() + File.separator + "lang",
                    langFileStr
            );
        }
        if(!this.configFile.exists()) {
            new File(this.plugin.getDataFolder() + File.separator + "lang",
                    langFileStr
            );
        }
    }

    private final StaffChatLite plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;
    private String langFileStr;
}
