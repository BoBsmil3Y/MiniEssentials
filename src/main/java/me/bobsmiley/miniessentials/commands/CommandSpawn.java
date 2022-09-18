package me.bobsmiley.miniessentials.commands;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandSpawn implements CommandExecutor {

    final private FileConfiguration config;

    public CommandSpawn(FileConfiguration c) { this.config = c; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;
        Player p = (Player) sender;

        Object loc = this.config.get("spawnpoint");
        if(loc == null) {
            p.sendMessage("No spawn has been set");
        }else {
            if(loc instanceof Location){
                p.teleport((Location) loc);
            }else {
                p.sendMessage("Not a loc");
            }
        }
        //REnvoi que c'est null alors que non

        return true;
    }
}
