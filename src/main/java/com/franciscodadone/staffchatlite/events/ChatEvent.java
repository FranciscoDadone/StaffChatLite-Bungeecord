package com.franciscodadone.staffchatlite.events;

import com.franciscodadone.staffchatlite.api.chat.ChatManager;
import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.storage.Global;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Objects;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(net.md_5.bungee.api.event.ChatEvent e) {
        if(!(e.getSender() instanceof ProxiedPlayer) || e.isCommand()) return;

        ProxiedPlayer player = (ProxiedPlayer) e.getSender();
        if(player.hasPermission(PermissionTable.chat)) {
            // Send message when sc toggle
            if(Global.playersToggledStaffChat.contains(player)) {
                e.setCancelled(true);
                ChatManager.sendStaffChatMessage(player, e.getMessage(), ((ProxiedPlayer) e.getSender()).getServer().getInfo().getName());
            }

            // Send message when #
            if(e.getMessage().startsWith(Objects.requireNonNull(Global.config.getString("sc-send-alias")))) {
                e.setCancelled(true);
                String message = e.getMessage().replaceFirst(Objects.requireNonNull(Global.config.getString("sc-send-alias")), "");
                ChatManager.sendStaffChatMessage(player, message, ((ProxiedPlayer) e.getSender()).getServer().getInfo().getName());
            }
        }
    }
}
