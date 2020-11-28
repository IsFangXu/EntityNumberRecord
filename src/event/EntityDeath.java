package event;

import main.Main;
import module.ModuleMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.List;

public class EntityDeath implements Listener {
    @EventHandler
    public void EntityDeath (EntityDeathEvent e) {
        List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
        for (Player player : players) {

            if (e.getEntity().getKiller() == player) {
                Location playerLocation = ModuleMain.getValue(player);
                if (playerLocation == null) {
                    ModuleMain.addMap(player, player.getLocation());
                } else {
                    Location newLocation = player.getLocation();
                    int range = Main.getConfigA().getInt("Range");
                    if (newLocation.distance(playerLocation) > range) ModuleMain.addMap(player, player.getLocation());
                    else return;
                }

                List<String> entityTypes = Main.getConfigA().getStringList("EntityType");
                String deadEntity = e.getEntity().getName().toLowerCase();
                for (String entityType : entityTypes) {
                    if (entityType.equalsIgnoreCase(deadEntity)) {
                        int entityNumber = Main.getConfigA().getInt("Entity." + deadEntity);
                        int playerNumber = Main.getPke().getInt(e.getEntity().getKiller().getName());
                        int newPlayerNumber = playerNumber + entityNumber;
                        Main.getPke().set(e.getEntity().getKiller().getName(), newPlayerNumber);
                        Main.getPke().save();
                    }
                }
            }
        }
    }
}
