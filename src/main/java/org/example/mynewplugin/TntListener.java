package org.example.mynewplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Locale;

public class TntListener implements Listener {

    private final MyFirstPlugin plugin;

    public TntListener(MyFirstPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTntSpawn(EntitySpawnEvent event) {
        if (!(event.getEntity() instanceof TNTPrimed)) return;

        TNTPrimed tnt = (TNTPrimed) event.getEntity();


        new BukkitRunnable() {
            @Override
            public void run() {

                if (!tnt.isValid()) {
                    cancel();
                    return;
                }

                int fuseTicks = tnt.getFuseTicks();

                if (fuseTicks <= 0) {
                    cancel();
                    return;
                }

                double seconds = fuseTicks / 20.0;

                String formattedTime = String.format(Locale.US, "%.1f", seconds);

                String textFormat = plugin.getConfig().getString("tnt-text-format", "&cВзрыв через: &e%seconds%с");
                String finalName = textFormat.replace("%seconds%", formattedTime);
                finalName = ChatColor.translateAlternateColorCodes('&', finalName);

                tnt.setCustomName(finalName);
                tnt.setCustomNameVisible(true);
            }
        }.runTaskTimer(plugin, 0L, 2L); // 2L означает повторение каждые 0.1 сек (каждые два тика)
    }
}
