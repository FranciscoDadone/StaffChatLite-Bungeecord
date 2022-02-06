package com.franciscodadone.staffchatlite.commandmanager.subcommands;

import com.franciscodadone.staffchatlite.StaffChatLite;
import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.util.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.List;

public class Reload extends SubCommands {
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
        return PermissionTable.admin;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(sender.hasPermission(getPermission())) {
            Global.config.reload();
            Global.langConfig.reload();
            new Global();
            sender.sendMessage(new TextComponent(Utils.Color(Global.langConfig.getString("prefix") + Global.langConfig.getString("reload-message"))));
        } else {
            Utils.noPermission(getPermission(), sender);
        }
    }
}
