package me.bobsmiley.miniessentials.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandWeather implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;

        Player p = (Player) sender;

        if(UtilitiesCommand.checkHasNotPermission(p, "mini.weather")) return true;

        World world = p.getWorld();

        if(args[0] == "clear") world.setClearWeatherDuration(36000); //30 minutes at least of clear weather
        else if (args[0] == "rain") world.setStorm(true);
        else if (args[0] == "thunder") world.setThundering(true);
        else if (args[0] == "change") world.setWeatherDuration(20);

        return true;
    }
}
