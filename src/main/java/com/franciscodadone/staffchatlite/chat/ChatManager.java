package com.franciscodadone.staffchatlite.chat;

import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatManager {

    public static void sendStaffChatMessage(CommandSender sender, String message) {
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            if(p.hasPermission(PermissionTable.chat)) {
                String toSend = Global.plugin.getConfig().getString("chat-style");
                if(sender instanceof Player) toSend = toSend.replace("%player%", ((Player)sender).getPlayerListName());
                else toSend = toSend.replace("%player%", "Console");
                toSend = toSend.replace("%prefix%", Global.langConfig.getConfig().getString("prefix"));
                toSend = toSend.replace("%message%", message);
                p.sendMessage(Utils.Color(toSend));
            }
        }
    }

    public static void toggleStaffChat(Player player) {
        if(!Global.playersToggledStaffChat.contains(player)) {
            Global.playersToggledStaffChat.add(player);
        } else {
            Global.playersToggledStaffChat.remove(player);
        }
    }

}
