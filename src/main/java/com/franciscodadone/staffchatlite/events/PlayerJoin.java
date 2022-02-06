package com.franciscodadone.staffchatlite.events;

import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onJoin(PostLoginEvent ignored) {
        System.out.println("player joined");
    }
}
