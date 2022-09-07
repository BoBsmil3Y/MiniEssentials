package me.bobsmiley.miniessentials.utils;

import org.bukkit.entity.Player;

import java.util.Date;
import java.util.TimerTask;

public class WaitClass extends TimerTask {

    private Player player;

    public WaitClass(Player p){
        this.player = p;
    }

    @Override
    public void run() {
        System.out.println("Debut execution tache " + new Date());
        try {
            Thread.sleep(5000);
            player.setInvulnerable(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Fin execution tache " + new Date());
    }
}
