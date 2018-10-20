package me.shanodekono.nostrip.commands;

import me.shanodekono.nostrip.utils.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;


public class NoStripCommand implements CommandExecutor {

    public List<UUID> toggle = new ArrayList<>();

    private ConfigUtils cfgUtils;

    public NoStripCommand(ConfigUtils configUtils) {
        cfgUtils = configUtils;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length != 1) {
            if (cfgUtils.allowHelpMenu) {
                sender.sendMessage(cfgUtils.color("&a------NoStrip------Commands------"));
                sender.sendMessage(cfgUtils.color("&6/nostrip toggle - &8Toggles Log Stripping/Debarking"));
                sender.sendMessage(cfgUtils.color("&6/nostrip aliases - &8Shows a list of command aliases"));

                if (sender.hasPermission("nostrip.reload")) {
                    sender.sendMessage(cfgUtils.color("&6/nostrip reload - &8Reloads The Config File"));
                }

                return true;
            }

            sender.sendMessage(cfgUtils.color(cfgUtils.prefix + " " + cfgUtils.unknownCommand
                    .replace("{command}", command.toString())));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("nostrip.reload")) {
                cfgUtils.reloadConfig(sender);
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("aliases")) {
            if (cfgUtils.allowAliasMenu) {
                sender.sendMessage(cfgUtils.color("&------NoStrip------Aliases------:"));
                sender.sendMessage(cfgUtils.color("&6/ns /nos /nostrip &8(toggle|aliases)"));
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("toggle")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(cfgUtils.color(cfgUtils.prefix + " " + cfgUtils.mustBePlayer));
                return true;
            }

            Player player = (Player) sender;

            if (!player.hasPermission("nostrip.toggle")) {
                player.sendMessage(cfgUtils.color(cfgUtils.prefix + " " + cfgUtils.noPermission));
                return true;
            }

            String status;

            if (!toggle.contains(player.getUniqueId())) {
                toggle.add(player.getUniqueId());
                status = "&coff";
                player.sendMessage(cfgUtils.color(cfgUtils.prefix + " " + cfgUtils.toggleMessage
                        .replace("{status}", status)));
                return true;
            }

            toggle.remove(player.getUniqueId());
            status = "&aon";
            player.sendMessage(cfgUtils.color(cfgUtils.prefix + " " + cfgUtils.toggleMessage
                    .replace("{status}", status)));
            return true;
        }

        sender.sendMessage(cfgUtils.color(cfgUtils.prefix + " " + cfgUtils.unknownCommand
                .replace("{command}", command.toString())));
        return true;
    }

}
