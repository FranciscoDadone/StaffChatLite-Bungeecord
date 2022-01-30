package com.franciscodadone.staffchatlite.commands.subcommands;

import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.util.Utils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Help extends SubCommands {
    @Override
    public String getDescription() {
        return "Shows the help";
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String syntax() {
        return "/anchor";
    }

    @Override
    public List<String> getSubCommandsArgs(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public String getPermission() {
        return PermissionTable.help;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(sender.hasPermission(getPermission())) {
            Utils.sendConfigMultilineMessage("help-message", sender);
        } else {
            Utils.noPermission(getPermission(), sender);
        }
    }
}

