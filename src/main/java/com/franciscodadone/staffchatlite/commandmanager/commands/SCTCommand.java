package com.franciscodadone.staffchatlite.commandmanager.commands;

import com.franciscodadone.staffchatlite.api.chat.ChatManager;
import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.util.Utils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SCTCommand extends Command {
    public SCTCommand() {
        super("sct", null, "staffchattoggle");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission(PermissionTable.toggle)) return;

        if(sender instanceof ProxiedPlayer) {
            ChatManager.toggleStaffChat((ProxiedPlayer) sender);
            if(Global.playersToggledStaffChat.contains((ProxiedPlayer)sender)) {
                sender.sendMessage(new TextComponent(Utils.Color(Global.langConfig.getString("prefix") + " " + Global.langConfig.getString("toggle-on"))));
            } else {
                sender.sendMessage(Utils.Color(Global.langConfig.getString("prefix") + " " + Global.langConfig.getString("toggle-off")));
            }
        } else {
            Utils.noPermission(getPermission(), sender);
        }

    }
}
