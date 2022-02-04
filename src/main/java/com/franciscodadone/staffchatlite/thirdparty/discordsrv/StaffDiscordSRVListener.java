package com.franciscodadone.staffchatlite.thirdparty.discordsrv;

import com.franciscodadone.staffchatlite.api.chat.ChatManager;
import com.franciscodadone.staffchatlite.storage.Global;
import github.scarsz.discordsrv.api.ListenerPriority;
import github.scarsz.discordsrv.api.Subscribe;
import github.scarsz.discordsrv.api.events.DiscordGuildMessageReceivedEvent;

public class StaffDiscordSRVListener {

    @Subscribe(priority = ListenerPriority.MONITOR)
    public void discordMessageReceived(DiscordGuildMessageReceivedEvent event) {

        if(event.getChannel().getId().equals(Global.plugin.getConfig().getString("discord-staff-channel-id"))) {
            ChatManager.sendStaffChatMessageFromDiscord(event.getMessage().getContentDisplay(), event.getAuthor().getName());
        }
    }
}
