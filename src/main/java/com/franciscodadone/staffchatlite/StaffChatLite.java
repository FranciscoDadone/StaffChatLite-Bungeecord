package com.franciscodadone.staffchatlite;

import com.franciscodadone.staffchatlite.commandmanager.commands.SCCommand;
import com.franciscodadone.staffchatlite.commandmanager.commands.SCTCommand;
import com.franciscodadone.staffchatlite.config.Config;
import com.franciscodadone.staffchatlite.commandmanager.commands.SCAdminCommand;
import com.franciscodadone.staffchatlite.events.ChatEvent;
import com.franciscodadone.staffchatlite.events.PlayerJoinEvent;
import com.franciscodadone.staffchatlite.events.PlayerLeaveEvent;
import com.franciscodadone.staffchatlite.events.PlayerSwitchEvent;
import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.thirdparty.bstats.Metrics;
import com.franciscodadone.staffchatlite.util.Logger;
import com.franciscodadone.staffchatlite.util.UpdateChecker;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import java.io.File;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class StaffChatLite extends Plugin {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void onEnable() {

        // // Global variables // //
        instance = this;

        // Events
        getProxy().getPluginManager().registerListener(this, new ChatEvent());
        getProxy().getPluginManager().registerListener(this, new PlayerJoinEvent());
        getProxy().getPluginManager().registerListener(this, new PlayerLeaveEvent());
        getProxy().getPluginManager().registerListener(this, new PlayerSwitchEvent());

        // Loading Lang config
        // Getting all lang files from jar
        ArrayList<String> langFiles = new ArrayList<>();
        try {
            // Creating lang folder if it doesn't exists.
            File langFolder = new File(getDataFolder() + File.separator + "lang");
            if(!langFolder.isDirectory()) {
                langFolder.mkdirs();
            }

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

        // Iterating over lang files found in jar to if it doesn't exist, create it.
        for(String langFile : langFiles) {
            File file = new File(getDataFolder() + File.separator + "lang", langFile);
            if(!file.exists()) {
                new Config().load("lang" + File.separator + langFile);
            }
        }

        //noinspection InstantiationOfUtilityClass
        new Global();

        // // Loading commands // //
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new SCAdminCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new SCCommand());
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new SCTCommand());

        // // Update checker // //
        new UpdateChecker(99628).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                Logger.info("Up to date!");
            } else {
                Logger.warning("Plugin Outdated!");
                Logger.warning("&eDownload the new version from: &9https://www.spigotmc.org/resources/staffchatlite.99628/");
                UpdateChecker.updateString = version;
            }
        });

        // Metrics and lang file chart
        Metrics metrics = new Metrics(this, 14124);
        metrics.addCustomChart(new Metrics.SimplePie("lang_file", () -> Global.config.getString("lang-file")));

    }

    @Override
    public void onDisable() {}

    public static StaffChatLite instance;
}
