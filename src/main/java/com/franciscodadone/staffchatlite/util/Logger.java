package com.franciscodadone.staffchatlite.util;

import com.franciscodadone.staffchatlite.storage.Global;
import net.md_5.bungee.api.chat.TextComponent;

public class Logger {

    public static void info(String str) {
        Global.plugin.getProxy().getConsole().sendMessage(new TextComponent(prefix + Utils.Color(str)));
    }

    public static void severe(String str) {
        Global.plugin.getProxy().getConsole().sendMessage(new TextComponent(prefix + Utils.Color("&4" + str)));
    }

    public static void warning(String str) {
        Global.plugin.getProxy().getConsole().sendMessage(new TextComponent(prefix + Utils.Color("&e" + str)));
    }

    private static final String prefix = Utils.Color("&7[&bStaffChatLite&7]&f ");

}