package me.drmarky.armordyer;

import me.drmarky.armordyer.Utilities.PlayerObject;
import me.drmarky.armordyer.Utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

public class ArmorCommand implements CommandExecutor {

    private final Main main;

    public ArmorCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

        // CHECK: sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage("Temp. Error ID: 1 --- " + main.invalid);
            return true;
        }

        // TRANSFORM: sender into player
        Player player = (Player) sender;

        // CHECK: proper number of arguments
        if (!(args.length == 0 || args.length == 1 || args.length == 3)) {
            player.sendMessage("Temp. Error ID: 2 --- " + main.invalid);
            return true;
        }

        // CHECK: has permission
        if (!(player.hasPermission("armor.create") || player.isOp())) {
            player.sendMessage(ChatColor.RED + "Sorry! You do not have sufficient permissions.");
            return true;
        }

        // CHECK: all args numerals
        for (String arg : args) {
            if (!(arg.matches("[0-9]+"))) {
                player.sendMessage("Temp. Error ID: 3 --- " + main.invalid);
                return true;
            }
        }

        if (args.length == 0) {
            // SETUP: default armor

            main.directory.put(player, new PlayerObject(Bukkit.createInventory(null, 45, Utils.upperCaseFirst(string)), 0, 160, 101, 38));
            Utils.arrangeGUI(main.directory.get(player).gui);
            player.openInventory(main.directory.get(player).gui);

        } else if (args.length == 1) {
            // SETUP: custom armor through hex

            String hex = "#" + args[0];

            // CHECK: hex is 6 digits plus the "#"
            if (!(String.valueOf(hex).length() == 7)) {
                player.sendMessage("Temp. Error ID: 4 --- " + main.invalid);
                return true;
            }

            int r = Color.decode(hex).getRed();
            int g = Color.decode(hex).getGreen();
            int b = Color.decode(hex).getBlue();

            main.directory.put(player, new PlayerObject(Bukkit.createInventory(null, 45, Utils.upperCaseFirst(string)), 0, r, g, b));
            Utils.arrangeGUI(main.directory.get(player).gui);
            Utils.forceColorUpdate(main.directory.get(player).gui, r, g, b);
            main.directory.get(player).layerCount++;
            player.openInventory(main.directory.get(player).gui);

        } else {
            // SETUP: custom armor through RGB

            // CHECK: each value 3 digits or less
            for (String arg : args) {
                if (!(String.valueOf(arg).length() <= 3 && Integer.valueOf(arg) < 256)) {
                    player.sendMessage("Temp. Error ID: 5 --- " + main.invalid);
                    return true;
                }
            }

            int r = Integer.valueOf(args[0]);
            int g = Integer.valueOf(args[1]);
            int b = Integer.valueOf(args[2]);

            main.directory.put(player, new PlayerObject(Bukkit.createInventory(null, 45, Utils.upperCaseFirst(string)), 0, r, g, b));
            Utils.arrangeGUI(main.directory.get(player).gui);
            Utils.forceColorUpdate(main.directory.get(player).gui, r, g, b);
            main.directory.get(player).layerCount++;
            player.openInventory(main.directory.get(player).gui);
        }
        
        return true;
    }
}
