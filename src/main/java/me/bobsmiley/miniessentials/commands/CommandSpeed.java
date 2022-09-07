package me.bobsmiley.miniessentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpeed implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;

        Player p = (Player) sender;

        if(UtilitiesCommand.checkHasNotPermission(p, "mini.speed")) return true;

        if(args.length != 1) {
            sender.sendMessage("Usage: /speed <1 to 10>.");
            return true;
        }

        final int speed = Integer.parseInt(args[0]);

        if(speed < 0 || speed > 10){
            p.sendMessage("Speed has to be a whole number between 1 and 10.");
            return true;
        }

        if(p.isFlying()) p.setFlySpeed(speed/10);
        else p.setWalkSpeed(speed/10);

        return true;
    }
}
