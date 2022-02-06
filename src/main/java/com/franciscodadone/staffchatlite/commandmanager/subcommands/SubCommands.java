package com.franciscodadone.staffchatlite.commandmanager.subcommands;

import net.md_5.bungee.api.CommandSender;

import java.util.List;

public abstract class SubCommands {

    public abstract String getName();

    public abstract String syntax();

    public abstract String getPermission();

    public abstract List<String> getSubCommandsArgs(CommandSender sender, String[] args);

    public abstract void perform(CommandSender sender, String[] args);

}
