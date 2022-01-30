package com.franciscodadone.staffchatlite.util;

import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.storage.LangConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Objects;

public class Utils {

    /**
     * Translates the color codes.
     * @param str string
     * @return formatted
     */
    public static String Color(String str) {
        return ChatColor.translateAlternateColorCodes('§', str.replace("&", "§"));
    }

    /**
     * Same as above but with a list.
     * @param strList string list
     * @return formatted
     */
    public static List<String> Color(List<String> strList) {
        for(String string: strList) {
            strList.set(strList.indexOf(string), Color(string));
        }
        return strList;
    }

    /**
     * Sends config message and requires strings to replace
     * @param str main string
     * @param toBeReplaced string to be replaced (placeholder)
     * @param toReplace string to replace
     * @param sender command sender
     */
    public static void sendConfigMessageF(String str, String toBeReplaced, String toReplace, CommandSender sender) {
        try {
            sender.sendMessage(Color(Objects.requireNonNull(Global.plugin.getConfig().getString(str)).replaceAll(toBeReplaced, toReplace)));
        } catch (Exception e) {
            Logger.severe("Check for config updates: &ahttps://github.com/FranciscoDadone/AnchorSell/blob/main/src/main/resources/config.yml");
        }
    }

    /**
     * Sends a config message
     * @param path path in config
     * @param sender command sender
     */
    public static void sendConfigMessage(String path, CommandSender sender) {
        try {
            sender.sendMessage(Color(Objects.requireNonNull(Global.plugin.getConfig().getString(path))));
        } catch (Exception e) {
            Logger.severe("Check for config updates: &ahttps://github.com/FranciscoDadone/AnchorSell/blob/main/src/main/resources/config.yml");
        }
    }

    /**
     * Sends a config message (multiline)
     * @param path path in config
     * @param sender command sender
     */
    public static void sendConfigMultilineMessage(String path, CommandSender sender) {
        try {
            for(String line: Global.langConfig.getStringList(path)) {
                sender.sendMessage(Utils.Color(line));
            }
        } catch (Exception e) {
            Logger.severe("Check for config updates: &ahttps://github.com/FranciscoDadone/AnchorSell/blob/main/src/main/resources/config.yml");
        }
    }

    /**
     * No permission error
     * @param permissionNode permission
     * @param sender command sender
     */
    public static void noPermission(String permissionNode, CommandSender sender) {
        sendConfigMessageF("no-permissions", "%permissionNode%", permissionNode, sender);
    }

}
