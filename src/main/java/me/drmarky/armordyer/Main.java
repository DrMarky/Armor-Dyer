package me.drmarky.armordyer;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {

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
        //pm.registerEvents(new PlayerMoveListener(new GenerateWinner(new StopGame()), new StopGame()), this);

    }

    private void registerCommands() {
        //getCommand("armor").setExecutor(new Command(this));
    }

}