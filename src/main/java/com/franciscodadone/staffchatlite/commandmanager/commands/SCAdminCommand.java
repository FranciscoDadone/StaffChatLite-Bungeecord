package com.franciscodadone.staffchatlite.commandmanager.commands;

import com.franciscodadone.staffchatlite.commandmanager.subcommands.*;
import com.franciscodadone.staffchatlite.permissions.PermissionTable;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class SCAdminCommand extends Command {

    ArrayList<SubCommands> subCommands = new ArrayList<>();

    public SCAdminCommand() {
        super("scadmin", null, "staffchatadmin");
        subCommands.add(new Help());
        subCommands.add(new Reload());
        subCommands.add(new Author());
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission(PermissionTable.admin)) return;

        @SuppressWarnings("WriteOnlyObject") AtomicBoolean found = new AtomicBoolean(false);
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
    }

//    @Override
//    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
//
//        if(!command.getName().equals("sc")) {
//            if (args.length == 1) {
//                ArrayList<String> arguments = new ArrayList<>();
//
//                subCommands.forEach((cmd) -> {
//                    if(cmd.getPermission().equals("") || sender.hasPermission(cmd.getPermission()))
//                        arguments.add(cmd.getName());
//                });
//
//                return arguments;
//
//            } else if (args.length == 2) {
//                ArrayList<String> subcommands = new ArrayList<>();
//
//                subCommands.forEach((cmd) -> {
//                    for (String subcommand : cmd.getSubCommandsArgs(sender, args)) {
//                        if (args[0].equalsIgnoreCase(cmd.getName())) {
//                            subcommands.add(subcommand);
//                        }
//                    }
//                });
//
//                return subcommands;
//            }
//            return new ArrayList<>();
//        }
//        return new ArrayList<>();
//    }
}
