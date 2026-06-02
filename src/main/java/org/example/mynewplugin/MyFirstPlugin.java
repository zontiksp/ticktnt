package org.example.mynewplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class MyFirstPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new TntListener(this), this);
        getLogger().info("Плагин TntTick успешно запущен! Обратный отсчет работает.");
    }

    @Override
    public void onDisable() {

        getServer().getScheduler().cancelTasks(this);
        getLogger().info("Плагин TntTick выключен, задачи остановлены.");
    }
}
