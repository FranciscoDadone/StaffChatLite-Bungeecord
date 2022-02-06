package com.franciscodadone.staffchatlite.config;

import com.franciscodadone.staffchatlite.StaffChatLite;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

public class Config {

    private String fileName = null;
    private Configuration configuration;
    private File configurationFile;
    private File dataFolder;

    public boolean load(String fileName) {
        this.fileName = fileName;
        this.dataFolder = StaffChatLite.instance.getDataFolder();

        if(!dataFolder.exists())
            dataFolder.mkdirs();

        configurationFile = new File(dataFolder, fileName);

        if(!configurationFile.exists()) {
            try {
                InputStream inputStream = StaffChatLite.instance.getResourceAsStream(fileName);
                Files.copy(inputStream, configurationFile.toPath());
                inputStream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }

        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configurationFile);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean reload() {
        return load(fileName);
    }

    public String getString(String path) {
        if(null == configuration)
            return null;
        return configuration.getString(path);
    }

    public Boolean getBoolean(String path) {
        if(null == configuration)
            return null;
        return configuration.getBoolean(path);
    }

    public Integer getInteger(String path) {
        if(null == configuration)
            return null;
        return configuration.getInt(path);
    }

    public List<String> getStringList(String path) {
        if(null == configuration)
            return null;
        return configuration.getStringList(path);
    }

    public int getVersion() {
        if(null == configuration)
            return -1;
        return configuration.getInt("version");
    }

    public String getFileName() {
        return fileName;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public File getConfigurationFile() {
        return configurationFile;
    }

}