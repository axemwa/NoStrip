package me.shanodekono.nostrip;

import me.shanodekono.nostrip.commands.NoStripCommand;
import me.shanodekono.nostrip.listeners.PlayerInteractListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoStrip extends JavaPlugin implements Listener {

private static List<UUID> enabledPlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        //noinspection unused
        Metrics metrics = new Metrics(this);

        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        this.getCommand("nostrip").setExecutor(new NoStripCommand());
    }
    public static List<UUID> getEnabledPlayers() {
        return enabledPlayers;
}
}
