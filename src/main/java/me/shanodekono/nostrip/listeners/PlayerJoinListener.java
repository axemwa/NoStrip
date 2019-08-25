package me.shanodekono.nostrip.listeners;

import me.shanodekono.nostrip.NoStrip;
import me.shanodekono.nostrip.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitScheduler;

import static org.bukkit.Bukkit.getServer;

public class PlayerJoinListener implements Listener {

    private ConfigUtils cfgUtils;

    private static NoStrip plugin = (NoStrip)Bukkit.getPluginManager().getPlugin("NoStrip");

    public  PlayerJoinListener(ConfigUtils configUtils) {

        cfgUtils = configUtils;
    }

    @EventHandler

    private void onJoin(PlayerJoinEvent event) {

        if (!cfgUtils.toggleDefaultOn && !cfgUtils.toggle.contains(event.getPlayer().getUniqueId())) {
            cfgUtils.toggle.add(event.getPlayer().getUniqueId());
        }
        if (cfgUtils.toggle.contains(event.getPlayer().getUniqueId())) {
            BukkitScheduler scheduler = getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                    if (cfgUtils.notifyOnJoin) {
                        event.getPlayer().sendMessage(cfgUtils.color(cfgUtils.prefix + cfgUtils.toggleMessage
                                .replace("{toggle}", cfgUtils.disabled)));
                    }
                }
            }, 20L);
        }
        if (cfgUtils.toggleDefaultOn && cfgUtils.toggle.contains(event.getPlayer().getUniqueId())) {
            cfgUtils.toggle.remove(event.getPlayer().getUniqueId());
        }
        if (!cfgUtils.toggle.contains(event.getPlayer().getUniqueId())) {
            BukkitScheduler scheduler = getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                    if (cfgUtils.notifyOnJoin) {
                        event.getPlayer().sendMessage(cfgUtils.color(cfgUtils.prefix + cfgUtils.toggleMessage
                                .replace("{toggle}", cfgUtils.enabled)));
                    }
                }
            }, 20L);
        }
    }
}
