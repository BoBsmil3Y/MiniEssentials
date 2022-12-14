package me.bobsmiley.miniessentials;

import me.bobsmiley.miniessentials.commands.*;
import me.bobsmiley.miniessentials.commands.statistics.CommandPlaytime;
import me.bobsmiley.miniessentials.commands.time.CommandDay;
import me.bobsmiley.miniessentials.commands.time.CommandNight;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MiniEssentials extends JavaPlugin {

    final private Server server = this.getServer();
    final private FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        // Time related command
        this.getCommand("day").setExecutor(new CommandDay());
        this.getCommand("night").setExecutor(new CommandNight());

        // Player related command
        this.getCommand("repair").setExecutor(new CommandRepair());
        this.getCommand("teleport").setExecutor(new CommandTeleport(this.server));
        this.getCommand("clearinventory").setExecutor(new CommandClearInventory(this.server));
        this.getCommand("speed").setExecutor(new CommandSpeed());
        this.getCommand("playtime").setExecutor(new CommandPlaytime());
        this.getCommand("panic").setExecutor(new CommandPanic(this.server));
        this.getCommand("workbench").setExecutor(new CommandWorkbench());
        this.getCommand("enderchest").setExecutor(new CommandEnderchest());
        this.getCommand("spawn").setExecutor(new CommandSpawn(this));
        this.getCommand("setspawn").setExecutor(new CommandSetSpawn(this));
        this.getCommand("feed").setExecutor(new CommandFeed());
        this.getCommand("heal").setExecutor(new CommandHeal());

        // Server related commands
        this.getCommand("list").setExecutor(new CommandList(this.server));
        this.getCommand("panic").setExecutor(new CommandPanic(server));
        this.getCommand("serverinfo").setExecutor(new CommandServerInfo());
        this.getCommand("mreload").setExecutor(new CommandReload(this));

        // Server related commands
        this.getCommand("list").setExecutor(new CommandList(server));

        //getServer().getPluginManager().registerEvent(new ManageChatAndCommandListener(), this);

    }

    @Override
    public void onDisable() {}

}
