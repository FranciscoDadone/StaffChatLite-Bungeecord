package com.franciscodadone.staffchatlite.api.events;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;

public class StaffSwitchEvent extends Event implements Cancellable {

    public StaffSwitchEvent(ProxiedPlayer player, String serverFrom, Server serverTo) {
        this.player = player;
        this.serverTo = serverTo;
        this.serverFrom = serverFrom;
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

    public String getServerFrom() {
        return serverFrom;
    }

    public Server getServerTo() {
        return serverTo;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    private ProxiedPlayer player;
    private String serverFrom;
    private Server serverTo;
    private boolean cancelled;

}
