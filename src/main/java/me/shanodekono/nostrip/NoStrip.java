package me.shanodekono.nostrip;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import me.shanodekono.nostrip.commands.NoStripCommand;
import me.shanodekono.nostrip.listeners.PlayerInteractListener;
import me.shanodekono.nostrip.utils.ConfigUtils;

public class NoStrip extends JavaPlugin {

    @Override
    public void onEnable() {
        //noinspection unused
        Metrics m = new Metrics(this);

        ConfigUtils cfgUtils = new ConfigUtils(this);
        ConfigUtils.send("&eChecking permissions...");
        ConfigUtils.send("&aNoStrip has successfully loaded!");

        cfgUtils.loadConfig();

        getServer().getPluginManager().registerEvents(new PlayerInteractListener(cfgUtils), this);
        getCommand("nostrip").setExecutor(new NoStripCommand(cfgUtils));
    }

}
