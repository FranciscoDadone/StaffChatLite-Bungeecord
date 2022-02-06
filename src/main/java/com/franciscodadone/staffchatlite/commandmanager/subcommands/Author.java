package com.franciscodadone.staffchatlite.commandmanager.subcommands;

import com.franciscodadone.staffchatlite.util.Utils;
import net.md_5.bungee.api.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Author extends SubCommands {

    @Override
    public String getName() {
        return "author";
    }

    @Override
    public String syntax() {
        return "/sc author";
    }

    @Override
    public List<String> getSubCommandsArgs(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }

    @Override
    public String getPermission() {
        return "";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        sender.sendMessage(Utils.Color("&7&m----------&r &9&lStaffChat &7&m----------"));
        sender.sendMessage(Utils.Color("&bMade with &c❤️ &bby DadoGamer13"));
        sender.sendMessage(Utils.Color("&bSpigot: &9..."));
        sender.sendMessage(Utils.Color("&7&m-------------------------------"));
    }
}
