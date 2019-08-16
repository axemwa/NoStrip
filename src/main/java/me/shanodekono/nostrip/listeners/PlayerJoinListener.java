package me.shanodekono.nostrip.listeners;

import me.shanodekono.nostrip.utils.ConfigUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private ConfigUtils cfgUtils;

    public  PlayerJoinListener(ConfigUtils configUtils) {

        cfgUtils = configUtils;
    }

    @EventHandler

    private void onJoin(PlayerJoinEvent event) {

        if (!cfgUtils.toggleDefaultOn) {
            cfgUtils.toggle.add(event.getPlayer().getUniqueId());
        }
    }
}
