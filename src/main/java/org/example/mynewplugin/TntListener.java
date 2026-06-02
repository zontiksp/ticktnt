package org.example.mynewplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        logTntToFile(tnt);

        if (plugin.isLogToConsole()) {
            String who = tnt.getSource() instanceof org.bukkit.entity.Player ? tnt.getSource().getName() : "Редстоун/ТНТ";
            plugin.getLogger().info("=== ТАЙМЕР ОБНАРУЖИЛ ТНТ (Активатор: " + who + ") ===");
            plugin.getLogger().info("Координаты: " + tnt.getLocation().toVector());
        }

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
        }.runTaskTimer(plugin, 0L, 2L);
    }

    private void logTntToFile(TNTPrimed tnt) {
        try {
            if (!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdirs();
            }

            File logFile = new File(plugin.getDataFolder(), "tnt-log.txt");

            try (FileWriter fw = new FileWriter(logFile, true);
                 PrintWriter pw = new PrintWriter(fw)) {

                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                String worldName = tnt.getWorld().getName();
                int x = tnt.getLocation().getBlockX();
                int y = tnt.getLocation().getBlockY();
                int z = tnt.getLocation().getBlockZ();

                String activatorName = "Неизвестно (Редстоун/Огонь)";
                org.bukkit.entity.Entity source = tnt.getSource();

                if (source instanceof org.bukkit.entity.Player) {
                    activatorName = "Игрок: " + source.getName();
                } else if (source instanceof TNTPrimed) {
                    activatorName = "Взрыв от другого ТНТ";
                } else if (source != null) {
                    activatorName = "Сущность: " + source.getType().name();
                }

                pw.println("[" + timeStamp + "] ТНТ взведён. " + activatorName + " | Мир: '" + worldName + "' | Координаты X:" + x + " Y:" + y + " Z:" + z);
            }
        } catch (IOException e) {
            plugin.getLogger().severe("Не удалось записать лог ТНТ в файл!");
            e.printStackTrace();
        }
    }
}
