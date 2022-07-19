package com.xiaye.xpfly.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Plugin config = com.xiaye.xpfly.Xpfly.getPlugin(com.xiaye.xpfly.Xpfly.class);
        config.reloadConfig();
        config.saveConfig();
        com.xiaye.xpfly.Xpfly.getPlugin(com.xiaye.xpfly.Xpfly.class).getServer().getPluginManager().disablePlugin(config);
        com.xiaye.xpfly.Xpfly.getPlugin(com.xiaye.xpfly.Xpfly.class).getServer().getPluginManager().enablePlugin(config);
        sender.sendMessage(ChatColor.GREEN + "[Xpfly] 配置文件已重载");
        return false;
    }
}