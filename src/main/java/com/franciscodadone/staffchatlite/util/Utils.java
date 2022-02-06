package com.franciscodadone.staffchatlite.util;

import com.franciscodadone.staffchatlite.storage.Global;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static String translateHexColorCodes(String message) {
        final char COLOR_CHAR = ChatColor.COLOR_CHAR;
        message = message.replace("{#", "#");
        final Pattern hexPattern = Pattern.compile("#([A-Fa-f0-9]{6})}");
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find())
        {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }

    /**
     * Translates the color codes.
     * @param str string
     * @return formatted
     */
    public static String Color(String str) {
        return translateHexColorCodes(ChatColor.translateAlternateColorCodes('§', str.replace("&", "§")));
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
            sender.sendMessage(new TextComponent(Color(Objects.requireNonNull(Global.langConfig.getString(str)).replaceAll(toBeReplaced, toReplace))));
        } catch (Exception e) {
            Logger.severe("Check for config updates: &ahttps://github.com/FranciscoDadone/StaffChatLite/tree/master/src/main/resources/lang");
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
                sender.sendMessage(new TextComponent(Utils.Color(line)));
            }
        } catch (Exception e) {
            Logger.severe("Check for config updates: &ahttps://github.com/FranciscoDadone/StaffChatLite/tree/master/src/main/resources/lang");
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
