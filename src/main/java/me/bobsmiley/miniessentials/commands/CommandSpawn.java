package me.bobsmiley.miniessentials.commands;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandSpawn implements CommandExecutor {

    final private Server server;

    public CommandSpawn(Server s) { this.server = s; }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;
        Player p = (Player) sender;

        List<World> worlds = this.server.getWorlds();
        for(World w : worlds){
            if(w.getName().equals("world")) continue; // Get from config
            p.teleport(w.getSpawnLocation());
        }

        return true;
    }
}
