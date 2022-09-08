package me.bobsmiley.miniessentials.utils;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;

public class DiscordHook {

    public WebhookClient buildHook(){
        String url = "https://discord.com/api/webhooks/1017381265521004545/vDrgGyh67rEFF6e-7bcD4bJsBxRy7ZEVsZ6clfO7hKki3Efs3-_bcqnGVfvMjObhzbx-";

        // Using the builder
        WebhookClientBuilder hook = new WebhookClientBuilder(url);
        hook.setThreadFactory((job) -> {
                Thread thread = new Thread(job);
                thread.setName("WebhookThread");
                thread.setDaemon(true);
                return thread;
            });
        hook.setWait(true);
        WebhookClient client = hook.build();

        return client;
    }


}
