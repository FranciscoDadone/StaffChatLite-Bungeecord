package com.franciscodadone.staffchatlite.listeners;

import com.franciscodadone.staffchatlite.api.chat.ChatManager;
import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.storage.Global;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.TimeUnit;

public class PlayerLeaveEvent implements Listener {

    @EventHandler
    public void onLeave(PlayerDisconnectEvent event) {
        if(!event.getPlayer().hasPermission(PermissionTable.chat)) return;
        ProxyServer.getInstance().getScheduler().schedule(Global.plugin, () -> ChatManager.sendLeaveMessage(event.getPlayer()), 3, TimeUnit.SECONDS);
    }

}
