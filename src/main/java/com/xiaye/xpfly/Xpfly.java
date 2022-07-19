package com.xiaye.xpfly;

import com.xiaye.xpfly.commands.flyon;
import com.xiaye.xpfly.commands.reload;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;


public final class Xpfly extends JavaPlugin {
    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Plugin config = com.xiaye.xpfly.Xpfly.getPlugin(com.xiaye.xpfly.Xpfly.class);
        int timer = config.getConfig().getInt("timer");
        System.out.println("[Xpfly] 插件已成功加载");
        System.out.println("[Xpfly] Designed by Xiaye_");
        getCommand("xpflyon").setExecutor(new flyon());
        getCommand("xpreload").setExecutor(new reload());
        BukkitTask removeexp = new com.xiaye.xpfly.tasks.RemoveExpTask().runTaskTimer(this, 0, 20 * timer);
    }

    @Override
    public void onDisable() {
        System.out.println("[Xpfly] 插件已卸载");
    }
}
