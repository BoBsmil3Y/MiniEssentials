package me.bobsmiley.miniessentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandHeal implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;

        Player p = (Player) sender;

        if(UtilitiesCommand.checkHasNotPermission(p, "mini.feed")) return true;

        p.setHealth(20);

        return true;
    }
}