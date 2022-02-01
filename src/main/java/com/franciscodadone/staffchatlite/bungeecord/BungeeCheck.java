package com.franciscodadone.staffchatlite.bungeecord;

import com.franciscodadone.staffchatlite.bungeecord.senders.BungeeGetServerName;
import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.util.Logger;

public class BungeeCheck {

    public static void check() {
        Logger.info(String.valueOf(Global.plugin.getServer().getOnlinePlayers().size()));
        if(Global.plugin.getServer().getOnlinePlayers().size() > 0) {
            BungeeGetServerName.getName();
            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(Global.serverName.equals("Unknown")) Global.bungeeEnabled = false;
                else Logger.info("BungeeCord enabled!");
            }).start();
        }

    }

}
