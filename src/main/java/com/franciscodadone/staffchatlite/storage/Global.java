package com.franciscodadone.staffchatlite.storage;

import com.franciscodadone.staffchatlite.StaffChatLite;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Global {

    public Global() {
        Global.langConfig =  new LangConfig().getConfig();
        playersToggledStaffChat = new ArrayList<>();
    }

    public static StaffChatLite plugin;
    public static FileConfiguration langConfig;
    public static ArrayList<Player> playersToggledStaffChat;

}
