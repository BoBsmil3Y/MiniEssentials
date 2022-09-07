package me.bobsmiley.miniessentials.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class UtilitiesCommand {

    /**
     * Will test if the player has the permission given
     * @param p the player to test permission from
     * @param permission the permission to check
     * @return true if the player doesn't have the permission
     */
    public static boolean checkHasNotPermission(Player p, String permission){
        if(! p.hasPermission(permission)){
            p.sendMessage("You do not have permission to do that.");
            return true;
        }
        return false;
    }

    /**
     * Return true and print a message if the sender is the console.
     * @param sender the CommandSender object to test
     * @return true if the sender is console
     */
    public static boolean checkIfConsole(CommandSender sender){
        if(sender instanceof ConsoleCommandSender) {
            sender.sendMessage("This command can only be executed in game.");
            return true;
        }
        return false;
    }
}
