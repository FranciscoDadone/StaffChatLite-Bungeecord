package com.franciscodadone.staffchatlite.thirdparty.bungeecord.senders;

import com.franciscodadone.staffchatlite.storage.Global;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BungeeSendMessage {

    /**
     * Sends a byte stream of the message, player name and current server name
     * to all servers in the network.
     * @param playerName
     * @param message
     * @param serverName
     */
    public static void send(String playerName, String message, String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Forward");
        out.writeUTF("ALL");
        out.writeUTF("StaffChatLite");

        ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();
        DataOutputStream msgout = new DataOutputStream(msgbytes);
        try {
            msgout.writeUTF(message);
            msgout.writeUTF(playerName);
            msgout.writeUTF(serverName);
        } catch (IOException exception){
            exception.printStackTrace();
        }

        out.writeShort(msgbytes.toByteArray().length);
        out.write(msgbytes.toByteArray());

        ((Player)Bukkit.getOnlinePlayers().toArray()[0]).sendPluginMessage(Global.plugin, "BungeeCord", out.toByteArray());
    }

}
