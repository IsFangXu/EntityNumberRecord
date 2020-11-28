package module;

import module.yamlfile.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ModuleMain extends ConfigManager {
    private static Map<Player, Location> playerLocationMap = new HashMap<>();

    public static void addMap(Player player, Location Loc) {
        playerLocationMap.put(player, Loc);
    }

    public static void delMap(Player player) {
        playerLocationMap.remove(player);
    }

    public static Location getValue(Player player) {
        return playerLocationMap.get(player);
    }

    public static String color (String msg) {
        if (msg.contains("&"))
            return msg.replace('&', ChatColor.COLOR_CHAR);
        return msg;
    }
}