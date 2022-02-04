package com.franciscodadone.staffchatlite.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class StaffToggleEvent extends Event {
    public StaffToggleEvent(Player sender, boolean isToggled) {
        this.player = sender;
        this.isToggled = isToggled;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isToggled() {
        return isToggled;
    }

    private Player player;
    private boolean isToggled;
    private boolean cancelled;
    private static final HandlerList handlers = new HandlerList();
}
