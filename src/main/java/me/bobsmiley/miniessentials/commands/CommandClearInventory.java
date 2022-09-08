package me.bobsmiley.miniessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class CommandClearInventory implements CommandExecutor {

    private Server server;

    public CommandClearInventory (Server s){
        this.server = s;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;

        Player p = (Player) sender;

        if(args.length == 1){
            if(UtilitiesCommand.checkHasNotPermission(p, "mini.clearinventory.other")) return true;

            Player target = Bukkit.getPlayer(args[0]);

            if(target == null){
                p.sendMessage("Target not found.");
                return true;
            }
            p = target;
        }

        p.getInventory().setContents(new ItemStack[p.getInventory().getSize()]);
        p.updateInventory();

        return true;

    }

}
