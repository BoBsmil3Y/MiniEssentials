package me.bobsmiley.miniessentials.commands.statistics;

import me.bobsmiley.miniessentials.commands.UtilitiesCommand;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;


public class CommandPlaytime implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;

        Player p = (Player) sender;

        if(UtilitiesCommand.checkHasNotPermission(p, "mini.playtime")) return true;

        String message = "";

        message += "&7Your statistics &8----------"
                + "\n  &8» &7Ping &8: &a" + p.getPing()
                + "\n  &8» &7Exp levels &8: &a" + Integer.toString(p.getLevel())
                + "\n  &8» &7Exp to next level &8: &a";


        int exp = Math.round(p.getExpToLevel() * 1.5f / 10);

        for(int i = 0; i < exp; i++)
            message += ChatColor.GREEN + "■";

        for(int i = 0; i < (15-exp); i++)
            message += ChatColor.GRAY + "■";

        message += "\n  &8» &7First played &8: &a" + (new Date (p.getFirstPlayed()) )
                + "\n  &8» &7Death &8: &a" + p.getStatistic(Statistic.DEATHS)
                + "\n  &8» &7Mob kill &8: &a" + p.getStatistic(Statistic.MOB_KILLS)
                + "\n  &8» &7Time played &8: &a" + p.getStatistic(Statistic.PLAY_ONE_MINUTE) / (20) + "s";

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));

        return true;
    }
}
