package me.shanodekono.nostrip.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.shanodekono.nostrip.NoStrip;

public class ConfigUtils { // For what use non-static if this class(not config) will be loaded only once?
    public static boolean allowHelpMenu,
    					  colorsConsole;

    public static String prefix,
    					 unknownCommand,
    					 mustBePlayer,
    					 noPermission,
    					 noAxePermission,
    					 toggleMessage;

    private static String configReloaded;

    public List<UUID> toggle = new ArrayList<>();

    private NoStrip plugin;

    public ConfigUtils(NoStrip pl) {
        plugin = pl;
    }


    public void loadConfig() {
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();

        allowHelpMenu = config.getBoolean("allow-help-menu", true);
        colorsConsole = config.getBoolean("send-colors-in-console", true);

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
        send(sender, prefix + " " + configReloaded);
    }

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void send(Object obj, String message) { // checks obj == ConsoleCommandSender || Player; | Auto-color.
		if (obj instanceof Player)
			((Player)obj).sendMessage(color(message));
		else
			if (obj instanceof ConsoleCommandSender)
				((ConsoleCommandSender)obj)
				.sendMessage(colorsConsole ? color(message) : ChatColor.stripColor(color(message)));
    }

    public static void send(String message) { // send message with auto-color to console.
		send(Bukkit.getConsoleSender(), message); // Use previous method to save code style :)
    }

}
