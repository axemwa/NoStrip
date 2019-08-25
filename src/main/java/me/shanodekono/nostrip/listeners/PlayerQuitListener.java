package me.shanodekono.nostrip.listeners;

import me.shanodekono.nostrip.utils.ConfigUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private ConfigUtils cfgUtils;

    public  PlayerQuitListener(ConfigUtils configUtils) {

        cfgUtils = configUtils;
    }

    @EventHandler

    private void onQuit(PlayerQuitEvent event) {

        if (!cfgUtils.toggleDefaultOn && !cfgUtils.toggle.contains(event.getPlayer().getUniqueId())) {
            cfgUtils.toggle.add(event.getPlayer().getUniqueId());
        }

        if (cfgUtils.toggleDefaultOn && cfgUtils.toggle.contains(event.getPlayer().getUniqueId())) {
            cfgUtils.toggle.remove(event.getPlayer().getUniqueId());
        }
    }
}
