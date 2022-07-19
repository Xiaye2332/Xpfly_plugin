package com.xiaye.xpfly.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;


public class flyon implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playername = player.getName();
            String world = player.getWorld().getName();
            int exp = getPlayerExp(player);
            Plugin config = com.xiaye.xpfly.Xpfly.getPlugin(com.xiaye.xpfly.Xpfly.class);
            String fail_to_enable_fly = config.getConfig().getString("fail_to_enable_fly");
            int price = config.getConfig().getInt("price");
            if (world.equals("world") && exp >= price) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "fly " + playername + " enable");
                changePlayerExp(player, (price * -1));
            } else {
                player.sendMessage(fail_to_enable_fly);
            }
        } else {
            System.out.println("[Xpfly] 此命令只能由玩家执行");
        }
        return false;
    }

    // 获得升级所需经验
    public static int getExpToLevelUp(int level) {
        if (level <= 15) {
            return 2 * level + 7;
        } else if (level <= 30) {
            return 5 * level - 38;
        } else {
            return 9 * level - 158;
        }
    }

    // 计算玩家当前等级
    public static int getExpAtLevel(int level) {
        if (level <= 16) {
            return (int) (Math.pow(level, 2) + 6 * level);
        } else if (level <= 31) {
            return (int) (2.5 * Math.pow(level, 2) - 40.5 * level + 360.0);
        } else {
            return (int) (4.5 * Math.pow(level, 2) - 162.5 * level + 2220.0);
        }
    }

    // 计算玩家当前经验值
    public static int getPlayerExp(Player player) {
        int exp = 0;
        int level = player.getLevel();
        exp += getExpAtLevel(level);
        exp += Math.round(getExpToLevelUp(level) * player.getExp());
        return exp;
    }

    // 更改玩家经验
    public static int changePlayerExp(Player player, int exp) {
        int currentExp = getPlayerExp(player);
        player.setExp(0);
        player.setLevel(0);
        int newExp = currentExp + exp;
        player.giveExp(newExp);
        return newExp;
    }
}