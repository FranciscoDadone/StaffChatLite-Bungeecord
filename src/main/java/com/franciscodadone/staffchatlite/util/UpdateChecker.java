package com.franciscodadone.staffchatlite.util;

import com.franciscodadone.staffchatlite.storage.Global;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {

    public UpdateChecker(int resourceId) {
        this.resourceId = resourceId;
    }

    public void getVersion(final Consumer<String> consumer) {
        Global.plugin.getProxy().getScheduler().runAsync(Global.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                Logger.info("Cannot look for updates: " + exception.getMessage());
            }
        });
    }

    private final int resourceId;
    public static String updateString = "";

}
