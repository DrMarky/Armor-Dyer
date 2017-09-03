package me.drmarky.armordyer.Events;

import me.drmarky.armordyer.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

    private final Main main;

    public InventoryCloseListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        if (main.directory.containsKey(player)) {
            main.directory.remove(player);
        }
    }
}
