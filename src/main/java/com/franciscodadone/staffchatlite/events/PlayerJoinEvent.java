package com.franciscodadone.staffchatlite.events;

import com.franciscodadone.staffchatlite.api.chat.ChatManager;
import com.franciscodadone.staffchatlite.storage.Global;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.TimeUnit;

public class PlayerJoinEvent implements Listener {
    @EventHandler
    public void onJoin(PostLoginEvent event) {
        ProxyServer.getInstance().getScheduler().schedule(Global.plugin, () -> ChatManager.sendJoinMessage(event.getPlayer()), 3, TimeUnit.SECONDS);
    }
}
