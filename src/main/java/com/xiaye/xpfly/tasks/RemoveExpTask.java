package com.xiaye.xpfly.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class RemoveExpTask extends BukkitRunnable {
    @Override
    public void run() {
        Plugin config = com.xiaye.xpfly.Xpfly.getPlugin(com.xiaye.xpfly.Xpfly.class);
        String out_of_exp = config.getConfig().getString("out_of_exp");
        int price = config.getConfig().getInt("price");
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.isFlying() && (!player.isOp()) && player.getWorld().getName().equals("world") && (!player.hasPermission("xpfly.allow"))) {
                if (com.xiaye.xpfly.commands.flyon.getPlayerExp(player) >= price) {
                    com.xiaye.xpfly.commands.flyon.changePlayerExp(player, (price * -1));
                } else {
                    String playername = player.getName();
                    player.sendMessage(out_of_exp);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fly " + playername + " disable");
                }
            }
        }
    }
}