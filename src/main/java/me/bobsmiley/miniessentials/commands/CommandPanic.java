package me.bobsmiley.miniessentials.commands;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import me.bobsmiley.miniessentials.utils.DiscordHook;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CommandPanic implements CommandExecutor {

    private final int shutdownWaitSeconds = 5;
    private final Server server;
    private final Logger logger;


    public CommandPanic(Server s) {this.server = s; this.logger = s.getLogger();}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(UtilitiesCommand.checkIfConsole(sender)) return true;

        Player p = (Player) sender;

        if(UtilitiesCommand.checkHasNotPermission(p, "mini.panic")) return true;


        this.server.broadcastMessage("Server is shutting down du to abnormal player behaviour. " +
                "It will be back when an admin will be connected. Stay informed on our discord !");

        this.server.setWhitelist(true);

        // Log UUID, name of player that executed command
        String message = "Information of" + p.getDisplayName() + " taht executed the panic command"
                        + "\n  &8» &7UUID :" + p.getUniqueId()
                        + "\n  &8» &7Name :" + p.getName()
                        + "\n  &7---------- :";

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        df.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));

        Player target;
        // Send UUID, name, ip, other info, time played, first connected
        for(int i = 0; i < args.length; i++){

            target = Bukkit.getPlayer(args[i]);
            if(target == null)
                this.logger.warning("Player not found for name : " + args[i]);
            else {
                //Get information for log and ban ip the player
                message += "\nInformation on the n°" + i + " reported player : " + target.getDisplayName()
                        + "\n  &8» &7UUID &8:&7 " + target.getUniqueId()
                        + "\n  &8» &7Name &8:&7 " + target.getName()
                        + "\n  &8» &7IP &8:&7 " + target.getAddress().getAddress().getHostAddress()
                        + "\n  &8» &7Time played &8:&7 " + target.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20
                        + "\n  &8» &7First connected on &8:&7 " + df.format(new Date(target.getFirstPlayed()))
                        + "\n  &7---------- :";

                String address = target.getAddress().getAddress().getHostAddress();
                if(address != null) this.server.banIP(address);
                else sender.sendMessage(target.getName() + " was not ban du to unknown address.");
            }

        }

        p.sendMessage(ChatColor.translateAlternateColorCodes('&', message));

        WebhookClient hook = new DiscordHook().buildHook();
        WebhookEmbed embed = new WebhookEmbedBuilder()
                .setColor(0xe74c3c) //red
                .setTitle(new WebhookEmbed.EmbedTitle("⚠️ Panic command has been trigger ⚠️", null))
                .setDescription(message.replaceAll("&[0-9A-F]", ""))
                .setFooter(new WebhookEmbed.EmbedFooter(df.format(new Date()), null))
                .build();

        hook.send(embed)
                .thenAccept((mess) -> System.out.printf("Message with embed has been sent [%s]%n", mess.getId()));

        new Thread(() -> {
            for(int i = 0; i < shutdownWaitSeconds; i++){
                try {
                    TimeUnit.SECONDS.sleep(1);
                    server.broadcastMessage("Server will shutdown in "
                            + (shutdownWaitSeconds-i) + "seconds.");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            server.shutdown();
        }).start();
        //thread.start();

        return true;
    }

}
