package me.bobsmiley.miniessentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommandEnderchest implements CommandExecutor {

    private ArrayList<Player> players;

    public CommandEnderchest() { this.players = new ArrayList<>(); }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(UtilitiesCommand.checkIfConsole(sender)) return true;
        Player p = (Player) sender;
        this.players.add(p);
        if(UtilitiesCommand.checkHasNotPermission(p, "mini.enderchest")) return true;

        p.openInventory(p.getEnderChest());
        return true;
    }

    @EventHandler
    public void onCloseEnderchest(InventoryCloseEvent event){
        Player p = (Player) event.getPlayer();
        if(! this.players.contains(p)) return;
        Inventory newEnderchest = event.getInventory();
        if(! newEnderchest.getType().equals(InventoryType.ENDER_CHEST)) return;
        event.getPlayer().getEnderChest().setContents(newEnderchest.getContents());
    }

}
