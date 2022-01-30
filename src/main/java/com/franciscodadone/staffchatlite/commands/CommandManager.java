package com.franciscodadone.staffchatlite.commands;

import com.franciscodadone.staffchatlite.chat.ChatManager;
import com.franciscodadone.staffchatlite.commands.subcommands.*;
import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.util.Utils;
import com.sun.istack.internal.NotNull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommandManager implements TabExecutor {

    ArrayList<SubCommands> subCommands = new ArrayList<>();

    public CommandManager() {
        subCommands.add(new Help());
        subCommands.add(new Toggle());
        subCommands.add(new Reload());
        subCommands.add(new Author());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(command.getName().equals("sc")) {
            if(sender.hasPermission(PermissionTable.chat)) {
                if(args.length > 0) {
                    String message = "";
                    for(String arg : args) {
                        message += arg + " ";
                    }
                    ChatManager.sendStaffChatMessage(sender, message);
                    return true;
                }
            }
        }

        if(command.getName().equals("schelp") || command.getName().equals("staffchatlite")) {
            if(sender.hasPermission(PermissionTable.help)) Utils.sendConfigMultilineMessage("help-message", sender);
            else Utils.noPermission(PermissionTable.help, sender);
            return true;
        }

        if(command.getName().equals("sct")) {
            if(sender.hasPermission(PermissionTable.toggle)) {
                ChatManager.toggleStaffChat((Player) sender);
                if(Global.playersToggledStaffChat.contains((Player)sender)) {
                    sender.sendMessage(Utils.Color(Global.langConfig.getConfig().getString("prefix") + Global.langConfig.getConfig().getString("toggle-on")));
                } else {
                    sender.sendMessage(Utils.Color(Global.langConfig.getConfig().getString("prefix") + Global.langConfig.getConfig().getString("toggle-off")));
                }
            } else {
                Utils.noPermission(PermissionTable.toggle, sender);
            }
            return true;
        }

        AtomicBoolean found = new AtomicBoolean(false);
        if (args.length >= 1) {
            subCommands.forEach((cmd) -> {
                if (args[0].equalsIgnoreCase(cmd.getName())) {
                    cmd.perform(sender, args);
                    found.set(true);
                }
            });
        } else {
            new Help().perform(sender, args);
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {

        if(!command.getName().equals("sc")) {
            if (args.length == 1) {
                ArrayList<String> arguments = new ArrayList<>();

                subCommands.forEach((cmd) -> {
                    if(cmd.getPermission().equals("") || sender.hasPermission(cmd.getPermission()))
                        arguments.add(cmd.getName());
                });

                return arguments;

            } else if (args.length == 2) {
                ArrayList<String> subcommands = new ArrayList<>();

                subCommands.forEach((cmd) -> {
                    for (String subcommand : cmd.getSubCommandsArgs(sender, args)) {
                        if (args[0].equalsIgnoreCase(cmd.getName())) {
                            subcommands.add(subcommand);
                        }
                    }
                });

                return subcommands;
            }
            return new ArrayList<>();
        }
        return new ArrayList<>();
    }
}
