package com.franciscodadone.staffchatlite.commands.subcommands;

import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.util.Utils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Reload extends SubCommands {
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String syntax() {
        return "/sc reload";
    }

    @Override
    public List<String> getSubCommandsArgs(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public String getPermission() {
        return PermissionTable.reload;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(sender.hasPermission(getPermission())) {
            Global.plugin.reloadConfig();
            Global.langConfig.reloadConfig();
            sender.sendMessage(Utils.Color(Global.langConfig.getConfig().getString("prefix") + Global.langConfig.getConfig().getString("reload-message")));
        } else {
            Utils.noPermission(getPermission(), sender);
        }
    }
}
