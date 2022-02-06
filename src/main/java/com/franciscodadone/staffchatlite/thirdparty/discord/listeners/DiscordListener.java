package com.franciscodadone.staffchatlite.thirdparty.discord.listeners;

import com.franciscodadone.staffchatlite.api.chat.ChatManager;
import com.franciscodadone.staffchatlite.storage.Global;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DiscordListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();

        if(!author.isBot() && channel.getId().equals(Global.config.getString("discord-staff-channel-id"))) {
            ChatManager.sendStaffChatMessageFromDiscord(message.getContentDisplay(), author.getName());
        }
    }
}
