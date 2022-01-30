package com.franciscodadone.staffchatlite.events;

import com.franciscodadone.staffchatlite.chat.ChatManager;
import com.franciscodadone.staffchatlite.storage.Global;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();

        if(Global.playersToggledStaffChat.contains(player)) {
            e.setCancelled(true);
            ChatManager.sendStaffChatMessage(player, e.getMessage());
        }

    }

}
