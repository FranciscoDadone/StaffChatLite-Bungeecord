package com.franciscodadone.staffchatlite.api.events;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;

public class StaffLeaveEvent extends Event implements Cancellable {

    public StaffLeaveEvent(ProxiedPlayer player, Server server) {
        this.player = player;
        this.server = server;
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

    public Server getServer() {
        return server;
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
    private Server server;
    private boolean cancelled;

}
