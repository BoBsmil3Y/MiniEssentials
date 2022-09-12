package me.bobsmiley.miniessentials.commands;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandSetSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;
        Player p = (Player) sender;

        p.getWorld().setSpawnLocation(p.getLocation());

        // Change in config if the world is not the same as the config one
        // if(! p.getWorld().getName().equals(CONFIG.WORLD){ //write in config }

        return true;
    }
}
