package com.franciscodadone.staffchatlite.thirdparty.bungeecord.listeners;

import com.franciscodadone.staffchatlite.chat.ChatManager;
import com.franciscodadone.staffchatlite.storage.Global;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class BungeeMessageListener implements PluginMessageListener {

    /**
     * Receives the byte stream from BungeeSendMessage and displays the information
     * on the other server.
     * @param channel
     * @param player
     * @param message
     */
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) { return; }

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subChannel = in.readUTF();
        if(subChannel.equals("GetServer")) {
            String name = in.readUTF();
            Global.serverName = name;
        }

        if(subChannel.equals("StaffChatLite")) {
            short len = in.readShort();
            byte[] msgbytes = new byte[len];
            in.readFully(msgbytes);

            DataInputStream msgin = new DataInputStream(new ByteArrayInputStream(msgbytes));
            try {
                String msg = msgin.readUTF();
                String playerName = msgin.readUTF();
                String serverName = msgin.readUTF();

                ChatManager.sendStaffChatMessageFromOtherServer(playerName, msg, serverName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
