package me.bobsmiley.miniessentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class CommandReload implements CommandExecutor {

    final private JavaPlugin plugin;

    public CommandReload(JavaPlugin plugin) { this.plugin = plugin; }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(! (sender instanceof ConsoleCommandSender)){
            Player p = (Player) sender;
            if(UtilitiesCommand.checkHasNotPermission(p, "mini.reload")) return true;
        }

        this.plugin.reloadConfig();

        return true;
    }
}
