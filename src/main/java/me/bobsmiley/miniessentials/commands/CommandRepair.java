package me.bobsmiley.miniessentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandRepair implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;

        Player p = (Player) sender;

        if(UtilitiesCommand.checkHasNotPermission(p, "mini.repair")) return true;

        ItemStack item = p.getInventory().getItemInMainHand();

        if(item == null) {
            p.sendMessage("You must have an item in your hand to do that.");
            return true;
        }

        ItemMeta meta = item.getItemMeta();

        if(meta instanceof org.bukkit.inventory.meta.Damageable){
            org.bukkit.inventory.meta.Damageable dMeta = (org.bukkit.inventory.meta.Damageable) meta;
            dMeta.setDamage(0);

            p.sendMessage("Your item has been repaired.");
            item.setItemMeta(dMeta);

            p.getInventory().setItemInMainHand(item);
        } else {
            p.sendMessage("An error occurred while getting the item's meta.");
        }

        return true;
    }
}
