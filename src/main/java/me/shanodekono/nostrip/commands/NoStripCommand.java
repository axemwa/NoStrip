package me.shanodekono.nostrip.commands;

import me.shanodekono.nostrip.utils.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class NoStripCommand implements CommandExecutor {

    private ConfigUtils cfgUtils;

    public NoStripCommand(ConfigUtils configUtils) {
        cfgUtils = configUtils;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length != 1) {
            if (cfgUtils.allowHelpMenu) {
                sender.sendMessage(cfgUtils.color("&5------------------\\"));
                sender.sendMessage(cfgUtils.color("&a*~&6{X&c{x&8&l[&5&lNo&a&lStrip&8&l]&r&cx}&6X}&a~*&5|>"));
                sender.sendMessage(cfgUtils.color("&5\\-----------------/"));
                sender.sendMessage(cfgUtils.color("&8/     &8&l[&e&lCommands&8&l]   &8\\"));
                sender.sendMessage(cfgUtils.color("&8\\-----------------/"));
                sender.sendMessage(cfgUtils.color("&c&l/nostrip toggle   &8|>"));
                sender.sendMessage(cfgUtils.color("&8-------------------|>"));
                sender.sendMessage(cfgUtils.color("&8Turns Log Stripping/Debarking &aOn&8|&cOff&8 |>"));
                sender.sendMessage(cfgUtils.color("&8Must be toggled &aon/enabled &8to strip with permitted axes |>"));
                sender.sendMessage(cfgUtils.color("&8Toggle &coff/disabled&8 = log stripping &cOff &8for all axes    |>"));
                sender.sendMessage(cfgUtils.color("&8-----------------------------------------------"));

                if (sender.hasPermission("nostrip.reload")) {
                    sender.sendMessage(cfgUtils.color("&c&l/nostrip reload &r&8|>"));
                    sender.sendMessage(cfgUtils.color("&8Reloads The Config File &r&8|>"));
                }
                return true;
            }

            sender.sendMessage(cfgUtils.color(cfgUtils.prefix + " " + cfgUtils.unknownCommand));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("nostrip.reload")) {
                cfgUtils.reloadConfig(sender);
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
            String astatus;

            if (!cfgUtils.toggle.contains(player.getUniqueId())) {
                cfgUtils.toggle.add(player.getUniqueId());
                status = "&cdisabled";
                astatus = "&coff";
                player.sendMessage(cfgUtils.color(cfgUtils.prefix + " " + cfgUtils.toggleMessage
                        .replace("{status}", status)
                        .replace("{astatus}", astatus)));
                return true;
            }

            cfgUtils.toggle.remove(player.getUniqueId());
            status = "&aenabled";
            astatus = "&aon";
            player.sendMessage(cfgUtils.color(cfgUtils.prefix + " " + cfgUtils.toggleMessage
                    .replace("{status}", status)
                    .replace("{astatus}", astatus)));
            return true;
        }
        sender.sendMessage(cfgUtils.color(cfgUtils.prefix + " " + cfgUtils.unknownCommand));
        return true;
    }

}