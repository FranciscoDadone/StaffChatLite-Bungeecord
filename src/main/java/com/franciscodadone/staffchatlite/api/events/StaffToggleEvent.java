package com.franciscodadone.staffchatlite.api.events;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;

public class StaffToggleEvent extends Event implements Cancellable {
    public StaffToggleEvent(ProxiedPlayer sender, boolean isToggled) {
        this.player = sender;
        this.isToggled = isToggled;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

    public boolean isToggled() {
        return isToggled;
    }

    private ProxiedPlayer player;
    private boolean isToggled;
    private boolean cancelled;
}
