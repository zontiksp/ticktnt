package org.example.mynewplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class TntConsoleCommand implements CommandExecutor {

    private final MyFirstPlugin plugin;

    public TntConsoleCommand(MyFirstPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage("§cЭту команду можно использовать только через консоль сервера!");
            return true;
        }

        if (args.length > 0 && args[0].equalsIgnoreCase("toggle")) {
            boolean currentMode = plugin.isLogToConsole();
            plugin.setLogToConsole(!currentMode);

            sender.sendMessage("[TntTick] Вывод логов ТНТ в консоль теперь: " + (plugin.isLogToConsole() ? "ВКЛЮЧЕН" : "ВЫКЛЮЧЕН"));
            sender.sendMessage("[TntTick] (Логи в файл 'tnt-log.txt' продолжают записываться в любом случае)");
            return true;
        }

        sender.sendMessage("[TntTick] Используйте: /tntconsole toggle");
        return true;
    }
}
