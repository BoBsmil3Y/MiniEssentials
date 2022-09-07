package me.bobsmiley.miniessentials.commands;

import me.bobsmiley.miniessentials.utils.WaitClass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Date;
import java.util.TimerTask;

public class CommandTeleport implements CommandExecutor {

    private Server server;

    public CommandTeleport (Server s){
        this.server = s;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;

        if(args.length == 0) {
            sender.sendMessage("Usage: /tp <player>");
            return true;
        }

        Player p = (Player) sender;

        if(UtilitiesCommand.checkHasNotPermission(p, "mini.teleport")) return true;

        Player target = Bukkit.getPlayer(args[0]);

        if(target == null){
            sender.sendMessage("Target not found.");
            return true;
        }

        Location loc = target.getLocation();
        if(target.isFlying()){
            p.setInvulnerable(true);
            new WaitClass(p).run();
        }
        p.teleport(loc);
        p.sendMessage("Teleportation done.");

        return true;

    }
}
