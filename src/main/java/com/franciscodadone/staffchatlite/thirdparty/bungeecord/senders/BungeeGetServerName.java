package com.franciscodadone.staffchatlite.thirdparty.bungeecord.senders;

import com.franciscodadone.staffchatlite.storage.Global;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.io.ByteArrayOutputStream;

public class BungeeGetServerName {

    /**
     * Sends a GetServer to get the current server name (in bungee)
     */
    public static void getName() {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("GetServer");

        ByteArrayOutputStream msgbytes = new ByteArrayOutputStream();

        out.writeShort(msgbytes.toByteArray().length);
        out.write(msgbytes.toByteArray());

        ((Player) Bukkit.getOnlinePlayers().toArray()[0]).sendPluginMessage(Global.plugin, "BungeeCord", out.toByteArray());
    }
}
