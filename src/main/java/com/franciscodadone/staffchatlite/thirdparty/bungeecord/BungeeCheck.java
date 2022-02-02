package com.franciscodadone.staffchatlite.thirdparty.bungeecord;

import com.franciscodadone.staffchatlite.thirdparty.bungeecord.senders.BungeeGetServerName;
import com.franciscodadone.staffchatlite.storage.Global;
import com.franciscodadone.staffchatlite.util.Logger;

public class BungeeCheck {

    /**
     * Checks if this server is connected via bungee cord with another and saves to
     * Global.serverName the name.
     */
    public static void check() {
        if(Global.plugin.getServer().getOnlinePlayers().size() > 0) {
            new Thread(() -> {
                // Sleeps 3 seconds to wait the player join.
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignored) {}
                BungeeGetServerName.getName();
                // Sleeps 10 seconds to wait for the response.
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ignored) {}

                if(Global.serverName.equals("Unknown")) Global.bungeeEnabled = false;
                else Logger.info("BungeeCord enabled!");
            }).start();
        }
    }
}
