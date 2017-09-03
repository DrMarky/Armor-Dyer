package me.drmarky.armordyer.Commands;

import me.drmarky.armordyer.Main;
import me.drmarky.armordyer.Utilities.PlayerObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command {

    private final Main main;

    public Command(Main main) {
        this.main = main;
    }

    public void command(CommandSender sender, String[] args, String spelling) {

        // CHECK: sender is a player
        if (!(sender instanceof Player)) {
            sender.sendMessage(main.invalid);
            return;
        }

        // TRANSFORM: sender into player
        Player player = (Player) sender;

        // CHECK: proper number of arguments
        if (!(args.length == 0 || args.length == 1 || args.length == 3)) {
            player.sendMessage(main.invalid);
            return;
        }

        // CHECK: has permission
        if (!(player.hasPermission("armor.create") || player.isOp())) {
            player.sendMessage(ChatColor.RED + "Sorry! You do not have sufficient permissions.");
            return;
        }

        // CHECK: all args numerals
        for (String arg : args) {
            if (!(arg.matches("[0-9]+"))) {
                player.sendMessage(main.invalid);
                return;
            }
        }

        // SETUP: default armor
        if (args.length == 0) {

            main.directory.put(player, new PlayerObject(Bukkit.createInventory(null, 45, spelling), 0, 160, 101, 38));
            player.openInventory(main.directory.get(player).gui);

        } else if (args.length == 1) {
        // SETUP: custom armor through hex

            int hex = Integer.valueOf(args[0]);

            // CHECK: hex is 6 digits
            if (!(String.valueOf(hex).length() == 6)) {
                player.sendMessage(main.invalid);
                return;
            }

            main.directory.put(player, new PlayerObject(Bukkit.createInventory(null, 45, spelling), 0, 160, 101, 38));
            player.openInventory(main.directory.get(player).gui);

        } else {
        // SETUP: custom armor through RGB

            // CHECK: each value 3 digits or less
            for (String arg : args) {
                if (!(String.valueOf(arg).length() <= 3)) {
                    player.sendMessage(main.invalid);
                    return;
                }
            }

            int r = Integer.valueOf(args[0]);
            int g = Integer.valueOf(args[1]);
            int b = Integer.valueOf(args[2]);

            main.directory.put(player, new PlayerObject(Bukkit.createInventory(null, 45, spelling), 0, r, g, b));
            player.openInventory(main.directory.get(player).gui);

        }
    }
}
