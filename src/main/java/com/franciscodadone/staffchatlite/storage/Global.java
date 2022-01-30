package com.franciscodadone.staffchatlite.storage;

import com.franciscodadone.staffchatlite.StaffChatLite;
import org.bukkit.configuration.file.FileConfiguration;

public class Global {

    public Global() {
        Global.langConfig =  new LangConfig().getConfig();
    }

    public static StaffChatLite plugin;
    public static FileConfiguration langConfig;
}
