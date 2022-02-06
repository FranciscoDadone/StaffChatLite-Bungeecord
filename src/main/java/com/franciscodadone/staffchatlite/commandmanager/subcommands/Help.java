package com.franciscodadone.staffchatlite.commandmanager.subcommands;

import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.util.Utils;
import net.md_5.bungee.api.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Help extends SubCommands {
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
        return PermissionTable.admin;
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

