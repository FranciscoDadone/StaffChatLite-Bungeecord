package com.franciscodadone.staffchatlite.chat;

import com.franciscodadone.staffchatlite.bungeecord.senders.BungeeGetServerName;
import com.franciscodadone.staffchatlite.bungeecord.senders.BungeeSendMessage;
import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatManager {

    public static void sendStaffChatMessage(CommandSender sender, String message) {
        String playerName = (sender instanceof Player) ? ((Player)sender).getPlayerListName() : "Console";

        if(Global.serverName == null) {
            if(Global.bungeeEnabled) {
                new Thread(() -> {
                    BungeeGetServerName.getName();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) { e.printStackTrace(); }

                    if(Global.serverName == null) Global.bungeeEnabled = false;

                    if(Global.bungeeEnabled) {
                        BungeeSendMessage.send(playerName, message, Global.serverName);
                        sendStaffChatMessageFromOtherServer(playerName, message, Global.serverName);
                    } else {
                        sendStaffChatMessage(playerName, message);
                    }
                }).start();
            } else {
                sendStaffChatMessage(playerName, message);
            }
        } else {
            BungeeSendMessage.send(playerName, message, Global.serverName);
            sendStaffChatMessageFromOtherServer(playerName, message, Global.serverName);
        }
    }

    public static void sendStaffChatMessageFromOtherServer(String playerName, String message, String serverName) {
        String toSend = Global.plugin.getConfig().getString("chat-style");
        toSend = toSend.replace("%player%", playerName);
        toSend = toSend.replace("%prefix%", Global.langConfig.getConfig().getString("prefix"));
        toSend = toSend.replace("%message%", message);
        toSend = toSend.replace("%server%", serverName);
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            if(p.hasPermission(PermissionTable.chat)) {
                p.sendMessage(Utils.Color(toSend));
            }
        }
        Bukkit.getConsoleSender().sendMessage(Utils.Color(toSend));
    }

    public static void sendStaffChatMessage(String playerName, String message) {
        String toSend = Global.plugin.getConfig().getString("chat-style");
        toSend = toSend.replace("%player%", playerName);
        toSend = toSend.replace("%prefix%", Global.langConfig.getConfig().getString("prefix"));
        toSend = toSend.replace("%message%", message);
        toSend = toSend.replace("%server%", "");
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            if(p.hasPermission(PermissionTable.chat)) {
                p.sendMessage(Utils.Color(toSend));
            }
        }
        Bukkit.getConsoleSender().sendMessage(Utils.Color(toSend));
    }

    public static void toggleStaffChat(Player player) {
        if(!Global.playersToggledStaffChat.contains(player)) {
            Global.playersToggledStaffChat.add(player);
        } else {
            Global.playersToggledStaffChat.remove(player);
        }
    }

}
