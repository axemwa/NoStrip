package me.shanodekono.nostrip;

import me.shanodekono.nostrip.listeners.PlayerInteractListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class NoStrip extends JavaPlugin {
    @Override
    public void onEnable() {
        //noinspection unused
        Metrics metrics = new Metrics(this);

        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
    }
}
