package event;

import main.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class AsyncPlayerChat implements Listener {
    @EventHandler
    public void AsyncPlayerChat (AsyncPlayerChatEvent e) {
        int playerNumber = Main.getPke().getInt(e.getPlayer().getName());
        List<String> prefixS = Main.getConfigA().getStringList("PrefixNumber");
        for (int i = 0; i < prefixS.size(); i++) {
            String prefix = prefixS.get(i);
            String number = prefix.split(":")[0];
            String prefixString = prefix.split(":")[1].replace('&', ChatColor.COLOR_CHAR) + ChatColor.WHITE;
            if (prefixS.size() == 1) {
                if (playerNumber >= Integer.valueOf(number)) e.setFormat("<" + prefixString + "%1$s> %2$s");
            } else if (i == prefixS.size() - 1) {
                if (playerNumber >= Integer.valueOf(number)) e.setFormat("<" + prefixString + "%1$s> %2$s");
            } else {
                String nextPrefix = prefixS.get(i + 1);
                String nextNumber = nextPrefix.split(":")[0];

                if (playerNumber >= Integer.valueOf(number) & playerNumber < Integer.valueOf(nextNumber))
                    e.setFormat("<" + prefixString + "%1$s> %2$s");
            }
        }
    }
}
