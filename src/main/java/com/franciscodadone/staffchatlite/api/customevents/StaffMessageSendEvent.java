package com.franciscodadone.staffchatlite.api.customevents;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class StaffMessageSendEvent extends Event {

    public StaffMessageSendEvent(String message, CommandSender sender, String serverName) {
        this.message = message;
        this.sender = sender;
        this.serverName = serverName;
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

    public String getMessage() {
        return message;
    }

    public String getServerName() {
        return serverName;
    }

    public CommandSender getSender() {
        return sender;
    }

    private String message;
    private String serverName;
    private CommandSender sender;
    private boolean cancelled;
    private static final HandlerList handlers = new HandlerList();
}
