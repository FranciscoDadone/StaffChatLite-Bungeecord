package com.franciscodadone.staffchatlite.commands.subcommands;

import com.franciscodadone.staffchatlite.api.chat.ChatManager;
import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.util.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Toggle extends SubCommands {
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getName() {
        return "toggle";
    }

    @Override
    public String syntax() {
        return "/sct";
    }

    @Override
    public List<String> getSubCommandsArgs(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public String getPermission() {
        return PermissionTable.toggle;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if(sender instanceof Player && sender.hasPermission(getPermission())) {
            ChatManager.toggleStaffChat((Player) sender);
            if(Global.playersToggledStaffChat.contains((Player)sender)) {
                sender.sendMessage(Utils.Color(Global.langConfig.getConfig().getString("prefix") + " " + Global.langConfig.getConfig().getString("toggle-on")));
            } else {
                sender.sendMessage(Utils.Color(Global.langConfig.getConfig().getString("prefix") + " " + Global.langConfig.getConfig().getString("toggle-off")));
            }
        } else {
            Utils.noPermission(getPermission(), sender);
        }
    }
}
