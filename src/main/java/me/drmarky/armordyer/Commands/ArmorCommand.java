package me.drmarky.armordyer.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ArmorCommand implements CommandExecutor {

    private final me.drmarky.armordyer.Commands.Command command;

    public ArmorCommand(me.drmarky.armordyer.Commands.Command command) {
        this.command = command;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        command.command(sender, args, "Armor");
        return true;
    }
}
