package me.shanodekono.nostrip;

import me.shanodekono.nostrip.commands.NoStripCommand;
import me.shanodekono.nostrip.listeners.PlayerInteractListener;
import me.shanodekono.nostrip.utils.ConfigUtils;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class NoStrip extends JavaPlugin {

    @Override
    public void onEnable() {
        //noinspection unused
        Metrics metrics = new Metrics(this);

        ConfigUtils cfgUtils = new ConfigUtils(this);
        NoStripCommand nsCommand = new NoStripCommand(cfgUtils);

        cfgUtils.loadConfig();

        getServer().getPluginManager().registerEvents(new PlayerInteractListener(nsCommand), this);
        getCommand("nostrip").setExecutor(new NoStripCommand(cfgUtils));
    }

}
