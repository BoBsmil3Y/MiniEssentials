package me.bobsmiley.miniessentials.commands;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandSetSpawn implements CommandExecutor {

    final private JavaPlugin plugin;
    final private FileConfiguration config;

    public CommandSetSpawn(JavaPlugin plugin) { this.plugin = plugin; this.config = plugin.getConfig(); }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;
        Player p = (Player) sender;

        this.config.set("spawnpoint", p.getLocation());
        this.plugin.saveConfig();

        return true;
    }
}
