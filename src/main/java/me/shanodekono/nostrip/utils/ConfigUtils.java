package me.shanodekono.nostrip.utils;

import me.shanodekono.nostrip.NoStrip;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConfigUtils {

    public List<UUID> toggle = new ArrayList<>();

    private NoStrip plugin;

    public ConfigUtils(NoStrip pl) {
        plugin = pl;
    }

    public boolean allowHelpMenu;

    public String prefix;
    public String unknownCommand;
    public String mustBePlayer;
    public String noPermission;
    public String noAxePermission;
    public String toggleMessage;

    private String configReloaded;

    public void loadConfig() {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

        allowHelpMenu = config.getBoolean("allow-help-menu", true);

        ConfigurationSection messages = config.getConfigurationSection("messages");

        prefix = messages.getString("prefix");
        unknownCommand = messages.getString("unknown-command");
        mustBePlayer = messages.getString("must-be-player");
        noPermission = messages.getString("no-permission");
        noAxePermission = messages.getString("no-axe-permission");
        toggleMessage = messages.getString("toggle-message");
        configReloaded = messages.getString("config-reloaded");

        plugin.saveConfig();
    }

    public void reloadConfig(CommandSender sender) {
        plugin.reloadConfig();
        plugin.getConfig();
        plugin.saveConfig();
        sender.sendMessage(color(prefix + " " + configReloaded));
    }

    public String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
