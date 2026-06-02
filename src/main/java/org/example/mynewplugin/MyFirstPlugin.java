package org.example.mynewplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class MyFirstPlugin extends JavaPlugin {

    private boolean logToConsole = true;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.logToConsole = getConfig().getBoolean("log-to-console", true);

        LogZipper.zipLogFile(getDataFolder());

        getServer().getPluginManager().registerEvents(new TntListener(this), this);
        getCommand("tntconsole").setExecutor(new TntConsoleCommand(this));

        getLogger().info("Плагин TntTick успешно запущен!");
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
        getLogger().info("Плагин TntTick выключен.");
    }

    public boolean isLogToConsole() {
        return logToConsole;
    }

    public void setLogToConsole(boolean logToConsole) {
        this.logToConsole = logToConsole;
    }
}
