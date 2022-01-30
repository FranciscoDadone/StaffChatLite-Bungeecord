package com.franciscodadone.staffchatlite.chat;

import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatManager {

    public static void sendStaffChatMessage(CommandSender sender, String message) {
        Player playerSender = (Player) sender;
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            if(p.hasPermission(PermissionTable.chat)) {
                String toSend = Global.plugin.getConfig().getString("chat-style");
                toSend = toSend.replace("%player%", playerSender.getPlayerListName());
                toSend = toSend.replace("%prefix%", Global.langConfig.getString("prefix"));
                toSend = toSend.replace("%message%", message);
                p.sendMessage(Utils.Color(toSend));
            }
        }
    }

}
