package com.franciscodadone.staffchatlite.api.chat;

import com.franciscodadone.staffchatlite.api.events.*;
import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.util.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;

import java.util.concurrent.TimeUnit;

public class ChatManager {

    /**
     * Sends a Staff Chat message, if it detects bungee enabled it adds the server name too.
     * @param sender
     * @param message
     */
    public static void sendStaffChatMessage(CommandSender sender, String message, String serverName) {
        String playerName = (sender instanceof ProxiedPlayer) ? ((ProxiedPlayer)sender).getDisplayName() : "Console";

        StaffMessageSendEvent sendEvent = new StaffMessageSendEvent(message, sender, serverName);
        Global.plugin.getProxy().getPluginManager().callEvent(sendEvent);

        if(!sendEvent.isCancelled()) {
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
    }

    /**
     * Toggles staff chat. /sct
     * @param player
     */
    public static void toggleStaffChat(ProxiedPlayer player) {
        StaffToggleEvent toggleEvent;
        if(!Global.playersToggledStaffChat.contains(player)) {
            toggleEvent = new StaffToggleEvent(player, true);
            if(toggleEvent.isCancelled()) return;
            Global.playersToggledStaffChat.add(player);
            Global.plugin.getProxy().getScheduler().schedule(Global.plugin, () -> Global.plugin.getProxy().getPluginManager().callEvent(new StaffToggleEvent(player, true)), 1, TimeUnit.MILLISECONDS);
            player.sendMessage(new TextComponent(Utils.Color(Global.langConfig.getString("prefix") + " " + Global.langConfig.getString("toggle-on"))));
        } else {
            toggleEvent = new StaffToggleEvent(player, false);
            if(toggleEvent.isCancelled()) return;
            Global.playersToggledStaffChat.remove(player);
            Global.plugin.getProxy().getScheduler().schedule(Global.plugin, () -> Global.plugin.getProxy().getPluginManager().callEvent(new StaffToggleEvent(player, false)), 1, TimeUnit.MILLISECONDS);
            player.sendMessage(new TextComponent(Utils.Color(Global.langConfig.getString("prefix") + " " + Global.langConfig.getString("toggle-off"))));
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

        StaffJoinEvent joinEvent = new StaffJoinEvent(player, player.getServer());
        ProxyServer.getInstance().getPluginManager().callEvent(joinEvent);
        if(joinEvent.isCancelled()) return;

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

        StaffLeaveEvent leaveEvent = new StaffLeaveEvent(player, player.getServer());
        ProxyServer.getInstance().getPluginManager().callEvent(leaveEvent);
        if(leaveEvent.isCancelled()) return;

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

    public static void sendSwitchMessage(ProxiedPlayer player, String serverFrom, Server serverTo) {

        StaffSwitchEvent leaveEvent = new StaffSwitchEvent(player, serverFrom, serverTo);
        ProxyServer.getInstance().getPluginManager().callEvent(leaveEvent);
        if(leaveEvent.isCancelled()) return;

        String toSend = Global.config.getString("chat-style-switch-message");

        toSend = toSend.replace("%player%", player.getDisplayName());
        toSend = toSend.replace("%prefix%", Global.langConfig.getString("prefix"));
        toSend = toSend.replace("%serverTo%", serverTo.getInfo().getName());
        toSend = toSend.replace("%serverFrom%", serverFrom);

        for(ProxiedPlayer p : Global.plugin.getProxy().getPlayers()) {
            if(p.hasPermission(PermissionTable.chat)) {
                p.sendMessage(new TextComponent(Utils.Color(toSend)));
            }
        }
        Global.plugin.getProxy().getConsole().sendMessage(new TextComponent(Utils.Color(toSend)));
    }

}
