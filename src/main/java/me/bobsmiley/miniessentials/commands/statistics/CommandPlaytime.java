package me.bobsmiley.miniessentials.commands.statistics;

import me.bobsmiley.miniessentials.commands.UtilitiesCommand;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class CommandPlaytime implements CommandExecutor {

    private Server server;

     public CommandPlaytime(Server s){
         this.server = s;
     }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;

        Player p = (Player) sender;

        if(UtilitiesCommand.checkHasNotPermission(p, "mini.playtime")) return true;

        p.sendMessage("Ping : " + p.getPing());
        p.sendMessage("Exp : " + p.getExp());
        p.sendMessage("Level : " + p.getLevel());
        p.sendMessage("ExpToLevel : " + p.getExpToLevel());
        p.sendMessage("First played : " + p.getFirstPlayed());
        p.sendMessage("Death : " + p.getStatistic(Statistic.DEATHS));
        p.sendMessage("Craft : " + p.getStatistic(Statistic.CRAFT_ITEM, Material.ACACIA_DOOR));
        p.sendMessage("Temps : " + p.getStatistic(Statistic.PLAY_ONE_MINUTE) / (20 * 60) + "s.");

        return true;
    }
}
