package me.bobsmiley.miniessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class CommandList implements CommandExecutor {

    private Server server;

    public CommandList(Server s){ this.server = s; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(! (sender instanceof ConsoleCommandSender))
            if(UtilitiesCommand.checkHasNotPermission((Player) sender, "mini.list")) return true;

        String list = "List of onlines : \n";

        Collection<Player> players = (Collection<Player>) this.server.getOnlinePlayers();

        for (Player p: players) list += p.getDisplayName() + ChatColor.GRAY + ", ";

        sender.sendMessage(list.substring(0, list.length()-2));

        return true;
    }
}
