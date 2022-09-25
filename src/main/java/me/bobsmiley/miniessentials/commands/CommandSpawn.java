package me.bobsmiley.miniessentials.commands;

import me.bobsmiley.miniessentials.MiniEssentials;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.logging.Level;

public class CommandSpawn implements CommandExecutor {

    //final private FileConfiguration config;
    final private MiniEssentials plugin;

    public CommandSpawn(MiniEssentials p) { this.plugin = p; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;
        Player p = (Player) sender;

        Location loc = this.plugin.getConfig().getLocation("spawnpoint");

        if(loc == null)
            this.plugin.getLogger().log(Level.SEVERE, "The spawn's location has not been set or has been corrupted.");
        else
            p.teleport((Location) loc);

        return true;
    }
}
