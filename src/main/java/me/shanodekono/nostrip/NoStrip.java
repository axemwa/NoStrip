package me.shanodekono.nostrip;

import me.shanodekono.nostrip.listeners.PlayerInteractListener;
import org.bukkit.plugin.java.JavaPlugin;

public class NoStrip extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
    }
}
