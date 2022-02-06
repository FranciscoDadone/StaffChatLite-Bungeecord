package com.franciscodadone.staffchatlite.events;

import com.franciscodadone.staffchatlite.api.chat.ChatManager;
import com.franciscodadone.staffchatlite.storage.Global;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.ServerSwitchEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.TimeUnit;

public class PlayerSwitchEvent implements Listener {

    @EventHandler
    public void onSwitch(ServerSwitchEvent event) {
        if(event.getFrom() == null) return;
        ProxyServer.getInstance().getScheduler().schedule(Global.plugin, () -> ChatManager.sendSwitchMessage(event.getPlayer(), event.getFrom().getName(), event.getPlayer().getServer().getInfo().getName()), 3, TimeUnit.SECONDS);
    }

}
