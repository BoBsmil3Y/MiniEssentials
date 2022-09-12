package me.bobsmiley.miniessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.List;

public class CommandServerInfo implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p;

        if(! (sender instanceof ConsoleCommandSender)){
            p = (Player) sender;
            if(UtilitiesCommand.checkHasNotPermission(p, "mini.panic")) return true;
        }

        // Get hardware info
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        CentralProcessor cpu = hal.getProcessor();
        GlobalMemory memory = hal.getMemory();
        List<HWDiskStore> disks = hal.getDiskStores();

        String message = "\n&9&lServer information &8----------"
                + "\n&9&lCPU"
                + "\n  &8» &7Name &8: &a" + cpu.getProcessorIdentifier().getName()
                + "\n  &8» &7Physical cores &8: &a" + cpu.getPhysicalProcessorCount() + "&7Logical cores &8: &a" + cpu.getLogicalProcessorCount()
                + "\n  &8» &7Max frequency &8: &a" + String.format("%.2f", (float) cpu.getMaxFreq()/1000000000) + "GHz"
                + "\n&9&lMemory"
                + "\n  &8» &7Available / max &8: &a" + memory.getAvailable() + " &8/ &a" + memory.getTotal()
                + "\n  &8» &7Physical memory &8: &a" + memory.getPhysicalMemory().toString()
                + "\n&9&lDisks (may not contain only yours)";

        short index = 1;
        for(HWDiskStore disk : disks){
            if(disk.getModel().equals("unknown")) continue;

            message += "\n&9&lDisk n°" + index
                    + "\n  &8» &7Name &8: &a" + disk.getName() + " &7Model &8: &a" + disk.getModel()
                    + "\n  &8» &7Size &8: &a" + String.format("%.2f", (float) (disk.getSize()/1000000000)) + "Gb";

            index++;
        }

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));

        return true;
    }
}
