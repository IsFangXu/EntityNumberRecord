package main;

import event.*;
import module.ModuleMain;
import module.yamlfile.FileLoaderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static module.yamlfile.ConfigManager.getFileLoaderAPI;

public class Main extends JavaPlugin {

    private static Main plugin = null;
    private static FileLoaderAPI config = null;
    private static FileLoaderAPI pke = null;

    @Override
    public void onEnable () {
        Main.plugin = this;

        Main.config = getFileLoaderAPI(plugin, "\\", "Config.yml", false);
        Main.pke = getFileLoaderAPI(plugin, "\\", "PlayerKillEntity.yml", false);

        for (Player player : Bukkit.getOnlinePlayers()) {
            ModuleMain.addMap(player, null);

            int playerNumber = Main.getPke().getInt(player.getName());
            List<String> prefixS = Main.getConfigA().getStringList("PrefixNumber");
            for (int i = 0; i < prefixS.size(); i++) {
                String prefix = prefixS.get(i);
                String number = prefix.split(":")[0];
                String prefixString = prefix.split(":")[1].replace('&', ChatColor.COLOR_CHAR) + ChatColor.WHITE;
                if (prefixS.size() == 1) {
                    if (playerNumber >= Integer.valueOf(number)) player.setPlayerListName(prefixString + player.getName());
                } else if (i == prefixS.size() - 1) {
                    if (playerNumber >= Integer.valueOf(number)) player.setPlayerListName(prefixString + player.getName());
                } else {
                    String nextPrefix = prefixS.get(i + 1);
                    String nextNumber = nextPrefix.split(":")[0];

                    if (playerNumber >= Integer.valueOf(number) & playerNumber < Integer.valueOf(nextNumber))
                        player.setPlayerListName(prefixString + player.getName());
                }
            }
        }

        Bukkit.getPluginManager().registerEvents(new EntityDeath(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), plugin);
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(), plugin);
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChat(), plugin);
    }

    @Override
    public void onDisable () {

    }

    public static Main getPlugin () {
        return plugin;
    }

    public static FileLoaderAPI getConfigA () {
        return config;
    }

    public static FileLoaderAPI getPke () {
        return pke;
    }
}