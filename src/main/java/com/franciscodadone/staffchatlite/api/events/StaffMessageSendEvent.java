package com.franciscodadone.staffchatlite.api.events;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;

public class StaffMessageSendEvent extends Event implements Cancellable {

    public StaffMessageSendEvent(String message, CommandSender sender, String serverName) {
        this.message = message;
        this.sender = sender;
        this.serverName = serverName;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
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
}
