package me.bobsmiley.miniessentials.commands.statistics;

import me.bobsmiley.miniessentials.commands.UtilitiesCommand;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class CommandPlaytime implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;

        Player p = (Player) sender;

        if(UtilitiesCommand.checkHasNotPermission(p, "mini.playtime")) return true;

        DateFormat df = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        df.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        long s = p.getStatistic(Statistic.PLAY_ONE_MINUTE) / (20);

        long sec = s % 60;
        long min = (s / 60) % 60;
        long hours = (s / 60) / 60;

        String message = "\n&9&lYour statistics &8----------"
                + "\n  &8» &7Ping &8: &a" + p.getPing()
                + "\n  &8» &7Exp levels &8: &a" + Integer.toString(p.getLevel())
                + "\n  &8» &7Kills &8: &a" + p.getStatistic(Statistic.PLAYER_KILLS)
                + "\n  &8» &7Death &8: &a" + p.getStatistic(Statistic.DEATHS)
                + "\n  &8» &7Mob kill &8: &a" + p.getStatistic(Statistic.MOB_KILLS)
                + "\n  &8» &7Time played &8: &a" + hours + "&7h&a" + min + "&7m&a" + sec + "&7s."
                + "\n  &8» &7First played &8: &a" + (df.format(new Date(p.getFirstPlayed())) + "\n");

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));

        return true;
    }
}
