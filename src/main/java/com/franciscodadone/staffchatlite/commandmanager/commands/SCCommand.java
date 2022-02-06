package com.franciscodadone.staffchatlite.commandmanager.commands;

import com.franciscodadone.staffchatlite.api.chat.ChatManager;
import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SCCommand extends Command {
    public SCCommand() {
        super("sc", null, "staffchat");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender.hasPermission(PermissionTable.chat)) {
            if(args.length > 0) {
                StringBuilder message = new StringBuilder();
                for(String arg : args) {
                    message.append(arg).append(" ");
                }
                ChatManager.sendStaffChatMessage(sender, message.toString(), ((ProxiedPlayer)sender).getServer().getInfo().getName());
            }
        }
    }
}
