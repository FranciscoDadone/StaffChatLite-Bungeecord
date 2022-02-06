package com.franciscodadone.staffchatlite.commandmanager.commands;

import com.franciscodadone.staffchatlite.api.chat.ChatManager;
import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.util.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SCTCommand extends Command {
    public SCTCommand() {
        super("sct", null, "staffchattoggle");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission(PermissionTable.toggle)) {
            Utils.noPermission(PermissionTable.toggle, sender);
            return;
        }

        if(sender instanceof ProxiedPlayer) {
            ChatManager.toggleStaffChat((ProxiedPlayer) sender);
        }
    }
}
