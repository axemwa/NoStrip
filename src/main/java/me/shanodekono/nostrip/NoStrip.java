package me.shanodekono.nostrip;

import me.shanodekono.nostrip.commands.NoStripCommand;
import me.shanodekono.nostrip.listeners.PlayerInteractListener;
import me.shanodekono.nostrip.utils.ConfigUtils;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class NoStrip extends JavaPlugin {

    @Override
    public void onEnable() {
        //noinspection unused
        Metrics metrics = new Metrics(this);

        ConfigUtils cfgUtils = new ConfigUtils(this);
        colorStr("&eChecking permissions...");
        colorStr("&aNoStrip has successfully loaded!");

        cfgUtils.loadConfig();

        getServer().getPluginManager().registerEvents(new PlayerInteractListener(cfgUtils), this);
        getCommand("nostrip").setExecutor(new NoStripCommand(cfgUtils));
    }
    private void colorStr(String string) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', string));
    }

}
