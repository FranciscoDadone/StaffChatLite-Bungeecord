package com.franciscodadone.staffchatlite;

import com.franciscodadone.staffchatlite.commands.CommandManager;
import com.franciscodadone.staffchatlite.storage.Global;
import com.tchristofferson.configupdater.ConfigUpdater;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class StaffChatLite extends JavaPlugin {

    @Override
    public void onEnable() {

        // // Global variables // //
        Global.plugin = this;

        // // Global Config // //
        this.saveDefaultConfig();
        File configFile = new File(getDataFolder(), "config.yml");
        try {
            ConfigUpdater.update(this, "config.yml", configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        reloadConfig();

        // Loading Lang config
        // Getting all lang files from jar
        ArrayList<String> langFiles = new ArrayList<>();
        try {
            final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
            final JarFile jar = new JarFile(jarFile);
            final Enumeration<JarEntry> entries = jar.entries();
            while(entries.hasMoreElements()) {
                String name = entries.nextElement().getName();
                if (name.startsWith("lang/")) {
                    name = name.replaceAll("lang/", "");
                    if(!name.equals("")) langFiles.add(name);
                }
            }
            jar.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Creating lang folder if it doesn't exists.
        File langFolder = new File(getDataFolder() + File.separator + "lang");
        if(!langFolder.isDirectory()) {
            langFolder.mkdir();
        }
        // Iterating over lang files found in jar to if it doesn't exist, create it or
        // check for updates.
        for(String langFile : langFiles) {
            File file = new File(getDataFolder() + File.separator + "lang", langFile);
            if(!file.exists()) {
                try {
                    new File(getDataFolder() + File.separator + "lang", langFile).createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                ConfigUpdater.update(this, "lang" + File.separator + langFile, new File(getDataFolder() + File.separator + "lang", langFile), Collections.emptyList());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // // Loading commands // //
        Objects.requireNonNull(this.getCommand("sc")).setExecutor(new CommandManager());
        Objects.requireNonNull(this.getCommand("staffchat")).setExecutor(new CommandManager());


        // Setup global variables such as lang config.
        new Global();

    }

    @Override
    public void onDisable() {}
}
