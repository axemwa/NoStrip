package me.shanodekono.nostrip.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.shanodekono.nostrip.utils.ConfigUtils;

public class NoStripCommand implements CommandExecutor {

    private ConfigUtils cfgUtils;

    public NoStripCommand(ConfigUtils configUtils) {
        cfgUtils = configUtils;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length != 1) {
            if (ConfigUtils.allowHelpMenu) {
            	ConfigUtils.send(sender, "&5=== [&a*~&6{X&c{x&f--&4&lNo&a&lStrip&f--&r&cx}&6X}&a~*&5] ===");
            	ConfigUtils.send(sender, "");
            	ConfigUtils.send(sender, "&c/nostrip toggle");
            	ConfigUtils.send(sender, "&e- &oTurns Log Stripping/Debarking &a&oOn&e&o|&c&oOff");
            	ConfigUtils.send(sender, "&e- &oMust be toggled &a&oon/enabled &e&oto strip with permitted axes");
            	ConfigUtils.send(sender, "&e- &oToggle &c&ooff/disabled&e&o = log stripping &c&oOff &e&ofor all axes");

                if (sender.hasPermission("nostrip.reload")) {
                	ConfigUtils.send(sender, "");
                	ConfigUtils.send(sender, "&c/nostrip reload");
                	ConfigUtils.send(sender, "&e- &oReloads The Config File");
                }
                return true;
            }

            ConfigUtils.send(sender, ConfigUtils.prefix + " " + ConfigUtils.unknownCommand);
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("nostrip.reload")) cfgUtils.reloadConfig(sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("toggle")) {
            if (!(sender instanceof Player)) {
            	ConfigUtils.send(sender, ConfigUtils.prefix + " " + ConfigUtils.mustBePlayer);
                return true;
            }

            Player player = (Player) sender;

            if (!player.hasPermission("nostrip.toggle")) {
            	ConfigUtils.send(player, ConfigUtils.prefix + " " + ConfigUtils.noPermission);
                return true;
            }

            String status;
            String astatus;

            if (!cfgUtils.toggle.contains(player.getUniqueId())) {
                cfgUtils.toggle.add(player.getUniqueId());
                status = "&cdisabled";
                astatus = "&coff";
                ConfigUtils.send(player,
                		ConfigUtils.prefix + " " + ConfigUtils.toggleMessage
                        .replace("{status}", status)
                        .replace("{astatus}", astatus));
                return true;
            }

            cfgUtils.toggle.remove(player.getUniqueId());
            status = "&aenabled";
            astatus = "&aon";
            ConfigUtils.send(player,
            		ConfigUtils.prefix + " " + ConfigUtils.toggleMessage
                    .replace("{status}", status)
                    .replace("{astatus}", astatus));
            return true;
        }
        ConfigUtils.send(sender, ConfigUtils.prefix + " " + ConfigUtils.unknownCommand);
        return true;
    }

}