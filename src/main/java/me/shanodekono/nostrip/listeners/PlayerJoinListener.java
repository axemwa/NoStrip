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
                    String status;
                    String astatus;
                    if (cfgUtils.notifyOnJoin) {
                        status = "&cdisabled";
                        astatus = "&coff";
                        event.getPlayer().sendMessage(cfgUtils.color(cfgUtils.prefix + " " + cfgUtils.toggleMessage
                                .replace("{status}", status
                                        .replace("{astatus}", astatus))));
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
                    String status;
                    String astatus;
                    if (cfgUtils.notifyOnJoin) {
                        status = "&aenabled";
                        astatus = "&aon";
                        event.getPlayer().sendMessage(cfgUtils.color(cfgUtils.prefix + " " + cfgUtils.toggleMessage
                                .replace("{status}", status
                                        .replace("{astatus}", astatus))));
                    }
                }
            }, 20L);
        }
    }
}
