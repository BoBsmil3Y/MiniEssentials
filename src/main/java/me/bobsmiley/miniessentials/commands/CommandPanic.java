package me.bobsmiley.miniessentials.commands;

import club.minnced.discord.webhook.WebhookClient;
import me.bobsmiley.miniessentials.utils.DiscordHook;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class CommandPanic implements CommandExecutor {

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

        // Send UUID, name, of player that executed command
        String message = "Information " + p.getDisplayName() + " that executed the panic command"
                        + "\n  &8» &7UUID :" + p.getUniqueId()
                        + "\n  &8» &7Name :" + p.getName()
                        + "\n  &7---------- :";

        Player target = null;

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
                        + "\n  &8» &7First connected on &8:&7 " + target.getFirstPlayed()
                        + "\n  &7---------- :";

                String address = target.getAddress().getAddress().getHostAddress();
                if(address != null) this.server.banIP(address);
                else sender.sendMessage(target.getName() + " was not ban causing ");
            }

        }

        this.server.setWhitelist(true);

        WebhookClient hook = new DiscordHook().buildHook();
        // Set a wait timeout of 5 seconds with message between second
        // send a mail
        //TODO

        // WEBHOOK : https://discord.com/api/webhooks/1017381265521004545/vDrgGyh67rEFF6e-7bcD4bJsBxRy7ZEVsZ6clfO7hKki3Efs3-_bcqnGVfvMjObhzbx-
        // Send and forget
        hook.send("Hello World");
        p.sendMessage(message);

//// Send and log (using embed)
//        WebhookEmbed embed = new WebhookEmbedBuilder()
//                .setColor(0xFF00EE)
//                .setDescription("Hello World")
//                .build();
//
//        client.send(embed)
//                .thenAccept((message) -> System.out.printf("Message with embed has been sent [%s]%n", message.getId()));
//
//// Change appearance of webhook message
//        WebhookMessageBuilder builder = new WebhookMessageBuilder();
//        builder.setUsername("Minn"); // use this username
//        builder.setAvatarUrl(avatarUrl); // use this avatar
//        builder.setContent("Hello World");
//        client.send(builder.build());

        //this.server.shutdown();


        return false;
    }
}
