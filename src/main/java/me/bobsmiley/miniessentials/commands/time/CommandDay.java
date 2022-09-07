package me.bobsmiley.miniessentials.commands.time;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.bobsmiley.miniessentials.commands.UtilitiesCommand;

public class CommandDay implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;

        Player p = (Player) sender;

        if(UtilitiesCommand.checkHasNotPermission(p, "mini.time")) return true;

        p.getWorld().setTime(0);

        return true;
    }
}
