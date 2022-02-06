package com.franciscodadone.staffchatlite.storage;

import com.franciscodadone.staffchatlite.StaffChatLite;
import com.franciscodadone.staffchatlite.config.Config;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.io.File;
import java.util.ArrayList;

public class Global {

    public Global() {
        config = new Config();
        config.load("config.yml");

        langConfig = new Config();
        langConfig.load("lang" + File.separator + config.getString("lang-file"));

        playersToggledStaffChat = new ArrayList<>();
        playersToggledSCMute = new ArrayList<>();

        plugin = StaffChatLite.instance;
    }

    public static StaffChatLite plugin;
    public static Config langConfig;
    public static Config config;
    public static ArrayList<ProxiedPlayer> playersToggledStaffChat;
    public static ArrayList<ProxiedPlayer> playersToggledSCMute;

}
