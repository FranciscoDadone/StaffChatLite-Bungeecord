package com.franciscodadone.staffchatlite.thirdparty.discordsrv;

import com.franciscodadone.staffchatlite.storage.Global;
import github.scarsz.discordsrv.dependencies.jda.api.EmbedBuilder;
import github.scarsz.discordsrv.dependencies.jda.api.entities.MessageEmbed;
import github.scarsz.discordsrv.util.DiscordUtil;

import javax.annotation.Nullable;
import java.awt.*;

public class StaffDiscordHandler {

    public static void init() {
        Global.discordSRVEnabled = true;
    }

    public static void sendStaffMessage(String message, String player, @Nullable String server) {

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(player + (server != null && !server.equals("Unknown") ? " (" + Global.serverName + ")" : ""));
        builder.setDescription(message);
        builder.setFooter("StaffChatLite - Spigot");
        builder.setColor(Color.RED);
        builder.setThumbnail("https://i.imgur.com/Hu6PEG8.jpg");
        MessageEmbed msg = builder.build();

        DiscordUtil.getTextChannelById(Global.plugin.getConfig().getString("discord-staff-channel-id")).sendMessageEmbeds(msg).queue();

    }

}
