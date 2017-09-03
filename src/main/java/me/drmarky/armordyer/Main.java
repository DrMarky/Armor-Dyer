package me.drmarky.armordyer;

import me.drmarky.armordyer.Commands.ArmorCommand;
import me.drmarky.armordyer.Commands.ArmourCommand;
import me.drmarky.armordyer.Commands.Command;
import me.drmarky.armordyer.Events.InventoryClickListener;
import me.drmarky.armordyer.Events.InventoryCloseListener;
import me.drmarky.armordyer.Utilities.PlayerObject;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    public HashMap<Player, PlayerObject> directory = new HashMap<>();
    public final String invalid = ChatColor.RED + "To work from the default armor, use /armor. To work from a hex value, use /armor <hex>. To work from an RGB value, use /armor <red> <green> <blue>. You can always substitute armor with armour.";

    @Override
    public void onEnable() {

        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = getLogger();

        logger.info(pdfFile.getName() + " has been enabled. (V." + pdfFile.getVersion() + ")");

        registerEvents();
        registerCommands();
    }

    @Override
    public void onDisable() {
        PluginDescriptionFile pdfFile = getDescription();
        Logger logger = getLogger();

        logger.info(pdfFile.getName() + " has been disabled. (V." + pdfFile.getVersion() + ")");
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new InventoryClickListener(this), this);
        pm.registerEvents(new InventoryCloseListener(this), this);

    }

    private void registerCommands() {
        getCommand("armor").setExecutor(new ArmorCommand(new Command(this)));
        getCommand("armour").setExecutor(new ArmourCommand(new Command(this)));
    }

}