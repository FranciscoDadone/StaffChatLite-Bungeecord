package com.franciscodadone.staffchatlite.storage;

import com.franciscodadone.staffchatlite.StaffChatLite;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Global {

    public Global() {
        Global.langConfig =  new LangConfig();
        playersToggledStaffChat = new ArrayList<>();
        bungeeEnabled = true;
        serverName = "Unknown";
    }

    public static StaffChatLite plugin;
    public static LangConfig langConfig;
    public static ArrayList<Player> playersToggledStaffChat;
    public static String serverName;
    public static boolean bungeeEnabled;

}
