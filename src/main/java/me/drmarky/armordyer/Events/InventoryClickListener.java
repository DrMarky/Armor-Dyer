package me.drmarky.armordyer.Events;

import me.drmarky.armordyer.Main;
import me.drmarky.armordyer.Utilities.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    private final Main main;

    public InventoryClickListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        // CHECK: player is using GUI
        if (!(main.directory.containsKey(player))) {
            return;
        }

        e.setCancelled(true);

        // CHECK: clicking in GUI
        if (!(main.directory.get(player).gui.equals(e.getClickedInventory()))) {
            return;
        }

        // CHECK: the player clicked something
        if (e.getCurrentItem() == null) {
            return;
        }

        ItemStack clicked = e.getCurrentItem();

        // CHECK: tbh just to get rid of the unnecessary null pointer exception
        if (!(clicked.hasItemMeta())) {
            return;
        }

        if (clicked.getType().equals(Material.LEATHER_HELMET)
                || clicked.getType().equals(Material.LEATHER_CHESTPLATE)
                || clicked.getType().equals(Material.LEATHER_LEGGINGS)
                || clicked.getType().equals(Material.LEATHER_BOOTS)) {
        // Give the armor

            player.getInventory().addItem(clicked);

        } else if (Utils.colorMap.containsKey(clicked.getItemMeta().getDisplayName())) {
        // Change the color

            main.directory.get(player).layerCount++;

            int r = Utils.colorMap.get(clicked.getItemMeta().getDisplayName()).getColor().getRed();
            int g = Utils.colorMap.get(clicked.getItemMeta().getDisplayName()).getColor().getGreen();
            int b = Utils.colorMap.get(clicked.getItemMeta().getDisplayName()).getColor().getBlue();

            if (main.directory.get(player).layerCount == 1) {
                Utils.forceColorUpdate(main.directory.get(player).gui, r, g, b);
            } else {
                Utils.updatePrevious(main, player);
                Utils.mixUpdateColor(main.directory.get(player).gui, Utils.colorMap.get(clicked.getItemMeta().getDisplayName()));
            }

        } else {
        // Undo

            int r = main.directory.get(player).previousR;
            int g = main.directory.get(player).previousG;
            int b = main.directory.get(player).previousB;

            Utils.forceColorUpdate(main.directory.get(player).gui, r, g, b);

        }
    }
}
