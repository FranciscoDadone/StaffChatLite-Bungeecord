package com.franciscodadone.staffchatlite.api.chat;

import com.franciscodadone.staffchatlite.api.events.*;
import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.thirdparty.discord.DiscordHandler;
import com.franciscodadone.staffchatlite.util.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;

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
            String toSend = Global.langConfig.getString("chat-style");

            toSend = toSend.replace("%player%", playerName);
            toSend = toSend.replace("%prefix%", Global.langConfig.getString("prefix"));
            toSend = toSend.replace("%message%", message);
            toSend = toSend.replace("%server%", (!playerName.equals("Console")) ? "(" + serverName + ")" : "");
            for(ProxiedPlayer p : Global.plugin.getProxy().getPlayers()) {
                if(p.hasPermission(PermissionTable.chat) && !Global.playersToggledSCMute.contains(p)) {
                    p.sendMessage(new TextComponent(Utils.Color(toSend)));
                }
            }
            Global.plugin.getProxy().getConsole().sendMessage(new TextComponent(Utils.Color(toSend)));
            DiscordHandler.sendMessage(message, playerName, (!playerName.equals("Console")) ? serverName : "");
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
            ProxyServer.getInstance().getPluginManager().callEvent(toggleEvent);
            if(toggleEvent.isCancelled()) return;
            Global.playersToggledStaffChat.add(player);
            player.sendMessage(new TextComponent(Utils.Color(Global.langConfig.getString("prefix") + " " + Global.langConfig.getString("toggle-on"))));
        } else {
            toggleEvent = new StaffToggleEvent(player, false);
            ProxyServer.getInstance().getPluginManager().callEvent(toggleEvent);
            if(toggleEvent.isCancelled()) return;
            Global.playersToggledStaffChat.remove(player);
            player.sendMessage(new TextComponent(Utils.Color(Global.langConfig.getString("prefix") + " " + Global.langConfig.getString("toggle-off"))));
        }
    }

    public static void sendJoinMessage(ProxiedPlayer player) {

        StaffJoinEvent joinEvent = new StaffJoinEvent(player, player.getServer());
        ProxyServer.getInstance().getPluginManager().callEvent(joinEvent);
        if(joinEvent.isCancelled()) return;

        String toSend = Global.langConfig.getString("chat-style-join-message");

        toSend = toSend.replace("%player%", player.getDisplayName());
        toSend = toSend.replace("%prefix%", Global.langConfig.getString("prefix"));
        toSend = toSend.replace("%server%", player.getServer().getInfo().getName());

        for(ProxiedPlayer p : Global.plugin.getProxy().getPlayers()) {
            if(p.hasPermission(PermissionTable.chat) && !Global.playersToggledSCMute.contains(p)) {
                p.sendMessage(new TextComponent(Utils.Color(toSend)));
            }
        }
        Global.plugin.getProxy().getConsole().sendMessage(new TextComponent(Utils.Color(toSend)));
        DiscordHandler.sendJoinMessage(player.getDisplayName(), player.getServer().getInfo().getName());
    }

    public static void sendLeaveMessage(ProxiedPlayer player) {

        StaffLeaveEvent leaveEvent = new StaffLeaveEvent(player, player.getServer());
        ProxyServer.getInstance().getPluginManager().callEvent(leaveEvent);
        if(leaveEvent.isCancelled()) return;

        String toSend = Global.langConfig.getString("chat-style-leave-message");

        toSend = toSend.replace("%player%", player.getDisplayName());
        toSend = toSend.replace("%prefix%", Global.langConfig.getString("prefix"));

        for(ProxiedPlayer p : Global.plugin.getProxy().getPlayers()) {
            if(p.hasPermission(PermissionTable.chat) && !Global.playersToggledSCMute.contains(p)) {
                p.sendMessage(new TextComponent(Utils.Color(toSend)));
            }
        }
        Global.plugin.getProxy().getConsole().sendMessage(new TextComponent(Utils.Color(toSend)));
        DiscordHandler.sendLeaveMessage(player.getDisplayName());
    }

    public static void sendSwitchMessage(ProxiedPlayer player, String serverFrom, Server serverTo) {

        StaffSwitchEvent leaveEvent = new StaffSwitchEvent(player, serverFrom, serverTo);
        ProxyServer.getInstance().getPluginManager().callEvent(leaveEvent);
        if(leaveEvent.isCancelled()) return;

        String toSend = Global.langConfig.getString("chat-style-switch-message");

        toSend = toSend.replace("%player%", player.getDisplayName());
        toSend = toSend.replace("%prefix%", Global.langConfig.getString("prefix"));
        toSend = toSend.replace("%serverTo%", serverTo.getInfo().getName());
        toSend = toSend.replace("%serverFrom%", serverFrom);

        for(ProxiedPlayer p : Global.plugin.getProxy().getPlayers()) {
            if(p.hasPermission(PermissionTable.chat) && !Global.playersToggledSCMute.contains(p)) {
                p.sendMessage(new TextComponent(Utils.Color(toSend)));
            }
        }
        Global.plugin.getProxy().getConsole().sendMessage(new TextComponent(Utils.Color(toSend)));
        DiscordHandler.sendSwitchMessage(player.getDisplayName(), serverFrom, serverTo.getInfo().getName());
    }

    public static void toggleStaffChatMute(ProxiedPlayer player) {
        StaffToggleMuteEvent toggleEvent;
        if(!Global.playersToggledSCMute.contains(player)) {
            toggleEvent = new StaffToggleMuteEvent(player, true);
            ProxyServer.getInstance().getPluginManager().callEvent(toggleEvent);
            if(toggleEvent.isCancelled()) return;
            Global.playersToggledSCMute.add(player);
            player.sendMessage(new TextComponent(Utils.Color(Global.langConfig.getString("prefix") + " " + Global.langConfig.getString("sc-mute-toggle-on"))));
        } else {
            toggleEvent = new StaffToggleMuteEvent(player, false);
            ProxyServer.getInstance().getPluginManager().callEvent(toggleEvent);
            if(toggleEvent.isCancelled()) return;
            Global.playersToggledSCMute.remove(player);
            player.sendMessage(new TextComponent(Utils.Color(Global.langConfig.getString("prefix") + " " + Global.langConfig.getString("sc-mute-toggle-off"))));
        }
    }

    public static void sendStaffChatMessageFromDiscord(String message, String username) {
        String toSend = Global.langConfig.getString("chat-style-from-discord");
        toSend = toSend.replace("%username%", username);
        toSend = toSend.replace("%prefix%", Global.langConfig.getString("prefix"));
        toSend = toSend.replace("%message%", message);
        for(ProxiedPlayer p : Global.plugin.getProxy().getPlayers()) {
            if(p.hasPermission(PermissionTable.chat) && !Global.playersToggledSCMute.contains(p)) {
                p.sendMessage(new TextComponent(Utils.Color(toSend)));
            }
        }
        Global.plugin.getProxy().getConsole().sendMessage(new TextComponent(Utils.Color(toSend)));
    }
}
