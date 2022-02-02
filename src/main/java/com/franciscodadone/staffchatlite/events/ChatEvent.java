package com.franciscodadone.staffchatlite.events;

import com.franciscodadone.staffchatlite.chat.ChatManager;
import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.thirdparty.discordsrv.StaffDiscordHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.util.Objects;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if(player.hasPermission(PermissionTable.chat)) {
            // Send message when sc toggle
            if(Global.playersToggledStaffChat.contains(player)) {
                e.setCancelled(true);
                ChatManager.sendStaffChatMessage(player, e.getMessage());
                if(Global.discordSRVEnabled) StaffDiscordHandler.sendStaffMessage(e.getMessage(), player.getPlayerListName(), Global.serverName);
            }

            // Send message when #
            if(e.getMessage().startsWith(Objects.requireNonNull(Global.plugin.getConfig().getString("sc-send-alias")))) {
                e.setCancelled(true);
                String message = e.getMessage().replaceFirst(Objects.requireNonNull(Global.plugin.getConfig().getString("sc-send-alias")), "");
                ChatManager.sendStaffChatMessage(player, message);
                if(Global.discordSRVEnabled) StaffDiscordHandler.sendStaffMessage(message, player.getPlayerListName(), Global.serverName);
            }
        }
    }
}
