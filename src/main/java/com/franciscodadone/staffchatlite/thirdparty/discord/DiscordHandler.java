package com.franciscodadone.staffchatlite.thirdparty.discord;

import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.thirdparty.discord.listeners.DiscordListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import java.awt.*;

public class DiscordHandler {

    private static JDA jda;

    public void build() {
        try {
            JDA jda = JDABuilder.createDefault(Global.config.getString("discord-bot-token"))
                    .addEventListeners(new DiscordListener())
                    .build();
            jda.awaitReady();
            this.jda = jda;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(String message, String playerName, String serverName) {
        if(!Global.config.getBoolean("discord-enabled") && Global.config.getBoolean("discord-send-staff-messages")) return;

        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(Color.CYAN);
        eb.setTitle(playerName, null);
        eb.setDescription(message);
        eb.addField("", ":desktop: " + serverName, false);
        eb.setFooter("StaffChatLite - BungeeCord", "https://www.spigotmc.org/data/resource_icons/99/99628.jpg?1643550102");

        jda.getTextChannelById(Global.config.getString("discord-staff-channel-id")).sendMessageEmbeds(eb.build()).queue();
    }

    public static void sendJoinMessage(String playerName, String server) {
        if(!Global.config.getBoolean("discord-enabled") && Global.config.getBoolean("discord-send-join-leave-messages")) return;

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.GREEN);

        String toSend = Global.langConfig.getString("discord-join-message");
        toSend = toSend.replace("%player%", playerName);
        toSend = toSend.replace("%server%", server);

        eb.setTitle(toSend, null);
        eb.setFooter("StaffChatLite - BungeeCord", "https://www.spigotmc.org/data/resource_icons/99/99628.jpg?1643550102");

        jda.getTextChannelById(Global.config.getString("discord-staff-channel-id")).sendMessageEmbeds(eb.build()).queue();

    }

    public static void sendLeaveMessage(String playerName) {
        if(!Global.config.getBoolean("discord-enabled") && Global.config.getBoolean("discord-send-join-leave-messages")) return;

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.RED);

        String toSend = Global.langConfig.getString("discord-leave-message");
        toSend = toSend.replace("%player%", playerName);

        eb.setTitle(toSend, null);
        eb.setFooter("StaffChatLite - BungeeCord", "https://www.spigotmc.org/data/resource_icons/99/99628.jpg?1643550102");

        jda.getTextChannelById(Global.config.getString("discord-staff-channel-id")).sendMessageEmbeds(eb.build()).queue();
    }

    public static void sendSwitchMessage(String playerName, String serverFrom, String serverTo) {
        if(!Global.config.getBoolean("discord-enabled") && Global.config.getBoolean("discord-send-join-leave-messages")) return;

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.CYAN);

        String toSend = Global.langConfig.getString("discord-switch-message");
        toSend = toSend.replace("%player%", playerName);
        toSend = toSend.replace("%serverFrom%", serverFrom);
        toSend = toSend.replace("%serverTo%", serverTo);

        eb.setTitle(toSend, null);
        eb.setFooter("StaffChatLite - BungeeCord", "https://www.spigotmc.org/data/resource_icons/99/99628.jpg?1643550102");

        jda.getTextChannelById(Global.config.getString("discord-staff-channel-id")).sendMessageEmbeds(eb.build()).queue();

    }

}
