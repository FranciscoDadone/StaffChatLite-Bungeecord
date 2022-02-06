package com.franciscodadone.staffchatlite.api.chat;

import com.franciscodadone.staffchatlite.api.events.StaffToggleEvent;
import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.util.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.concurrent.TimeUnit;

public class ChatManager {

    /**
     * Sends a Staff Chat message, if it detects bungee enabled it adds the server name too.
     * @param sender
     * @param message
     */
    public static void sendStaffChatMessage(CommandSender sender, String message, String serverName) {
        String playerName = (sender instanceof ProxiedPlayer) ? ((ProxiedPlayer)sender).getDisplayName() : "Console";
        String toSend = Global.config.getString("chat-style");

        toSend = toSend.replace("%player%", playerName);
        toSend = toSend.replace("%prefix%", Global.langConfig.getString("prefix"));
        toSend = toSend.replace("%message%", message);
        toSend = toSend.replace("%server%", (!playerName.equals("Console")) ? "(" + serverName + ")" : "");
        for(ProxiedPlayer p : Global.plugin.getProxy().getPlayers()) {
            if(p.hasPermission(PermissionTable.chat)) {
                p.sendMessage(new TextComponent(Utils.Color(toSend)));
            }
        }
        Global.plugin.getProxy().getConsole().sendMessage(new TextComponent(Utils.Color(toSend)));
    }

    /**
     * Toggles staff chat. /sct
     * @param player
     */
    public static void toggleStaffChat(ProxiedPlayer player) {
        if(!Global.playersToggledStaffChat.contains(player)) {
            Global.playersToggledStaffChat.add(player);
            Global.plugin.getProxy().getScheduler().schedule(Global.plugin, () -> Global.plugin.getProxy().getPluginManager().callEvent(new StaffToggleEvent(player, true)), 1, TimeUnit.MILLISECONDS);
        } else {
            Global.playersToggledStaffChat.remove(player);
            Global.plugin.getProxy().getScheduler().schedule(Global.plugin, () -> Global.plugin.getProxy().getPluginManager().callEvent(new StaffToggleEvent(player, false)), 1, TimeUnit.MILLISECONDS);
        }
    }

    public static void sendStaffChatMessageFromDiscord(String message, String username) {
        String toSend = Global.config.getString("chat-style-from-discord");
        toSend = toSend.replace("%username%", username);
        toSend = toSend.replace("%prefix%", Global.langConfig.getString("prefix"));
        toSend = toSend.replace("%message%", message);
        for(ProxiedPlayer p : Global.plugin.getProxy().getPlayers()) {
            if(p.hasPermission(PermissionTable.chat)) {
                p.sendMessage(new TextComponent(Utils.Color(toSend)));
            }
        }
        Global.plugin.getProxy().getConsole().sendMessage(new TextComponent(Utils.Color(toSend)));
    }

    public static void sendJoinMessage(ProxiedPlayer player) {
        String toSend = Global.config.getString("chat-style-join-message");

        toSend = toSend.replace("%player%", player.getDisplayName());
        toSend = toSend.replace("%prefix%", Global.langConfig.getString("prefix"));
        toSend = toSend.replace("%server%", player.getServer().getInfo().getName());

        for(ProxiedPlayer p : Global.plugin.getProxy().getPlayers()) {
            if(p.hasPermission(PermissionTable.chat)) {
                p.sendMessage(new TextComponent(Utils.Color(toSend)));
            }
        }
        Global.plugin.getProxy().getConsole().sendMessage(new TextComponent(Utils.Color(toSend)));
    }

    public static void sendLeaveMessage(ProxiedPlayer player) {
        String toSend = Global.config.getString("chat-style-leave-message");

        toSend = toSend.replace("%player%", player.getDisplayName());
        toSend = toSend.replace("%prefix%", Global.langConfig.getString("prefix"));

        for(ProxiedPlayer p : Global.plugin.getProxy().getPlayers()) {
            if(p.hasPermission(PermissionTable.chat)) {
                p.sendMessage(new TextComponent(Utils.Color(toSend)));
            }
        }
        Global.plugin.getProxy().getConsole().sendMessage(new TextComponent(Utils.Color(toSend)));
    }

    public static void sendSwitchMessage(ProxiedPlayer player, String serverFrom, String serverTo) {
        String toSend = Global.config.getString("chat-style-switch-message");

        toSend = toSend.replace("%player%", player.getDisplayName());
        toSend = toSend.replace("%prefix%", Global.langConfig.getString("prefix"));
        toSend = toSend.replace("%serverTo%", serverTo);
        toSend = toSend.replace("%serverFrom%", serverFrom);

        for(ProxiedPlayer p : Global.plugin.getProxy().getPlayers()) {
            if(p.hasPermission(PermissionTable.chat)) {
                p.sendMessage(new TextComponent(Utils.Color(toSend)));
            }
        }
        Global.plugin.getProxy().getConsole().sendMessage(new TextComponent(Utils.Color(toSend)));
    }

}
