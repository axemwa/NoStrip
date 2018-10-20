package me.shanodekono.nostrip.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class NoStripCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("nostrip")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                ((Player) sender).getUniqueId();
                if (!player.hasPermission("nostrip.toggle")) {
                    player.sendMessage(ChatColor.RED + "Permission required");
                    return false;
                }else
                    if (args.length == 1)
                        if (args[0].equalsIgnoreCase("toggle"))
                            if (me.shanodekono.nostrip.NoStrip.getEnabledPlayers().contains(player.getUniqueId())) {
                                me.shanodekono.nostrip.NoStrip.getEnabledPlayers().remove(player.getUniqueId());
                                player.sendMessage(ChatColor.RED + "Log Stripping Off!");
                            }else{
                                me.shanodekono.nostrip.NoStrip.getEnabledPlayers().add(player.getUniqueId());
                                player.sendMessage(ChatColor.GREEN + "Log Stripping On!");
                            }
            }else
                if (args.length == 1)
                    if (args[0].equalsIgnoreCase("toggle")) {
                        sender.sendMessage(ChatColor.RED + "Sorry only players can use this command");
                    }
            }if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.hasPermission("nostrip.help")) {
                    return false;
                }else
                    if (args.length == 1)
                        if (args[0].equalsIgnoreCase("help")) {
                            player.sendMessage(ChatColor.GREEN + "--NoStrip--Commands--:");
                            player.sendMessage(ChatColor.GREEN + "---------------------");
                            player.sendMessage(ChatColor.RED + "/nostrip toggle -" + ChatColor.GOLD +
                                    " Toggles Log Stripping/Debarking");
                            player.sendMessage(ChatColor.GOLD + "Permission -" + ChatColor.RED + " nostrip.toggle");
                            player.sendMessage(ChatColor.RED + "/nostrip aliases -" + ChatColor.GOLD +
                                    " Shows a list of command aliases");
                            player.sendMessage(ChatColor.GOLD + "Permission -" + ChatColor.RED + " nostrip.toggle");
                            player.sendMessage(ChatColor.RED + "/nostrip help -" + ChatColor.GOLD +
                                    " Brings up this menu");
                            player.sendMessage(ChatColor.GOLD + "Permission -" + ChatColor.RED + " nostrip.help");
                    }else
                        if (!player.hasPermission("nostrip.aliases")){
                        player.sendMessage(ChatColor.RED + "Permission required!!");
                        return false;
                    }
            }else
                if (args.length == 1)
                    if (args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage(ChatColor.GREEN + "--NoStrip--Commands--:");
                    sender.sendMessage(ChatColor.GREEN + "---------------------");
                    sender.sendMessage(ChatColor.RED + "/nostrip toggle -" + ChatColor.GOLD +
                                " Toggles Log Stripping/Debarking");
                    sender.sendMessage(ChatColor.GOLD + "Permission -" + ChatColor.RED + " nostrip.toggle");
                    sender.sendMessage(ChatColor.RED + "/nostrip aliases -" + ChatColor.GOLD +
                                " Shows a list of command aliases");
                    sender.sendMessage(ChatColor.GOLD + "Permission -" + ChatColor.RED + " nostrip.aliases");
                    sender.sendMessage(ChatColor.RED + "/nostrip help -" + ChatColor.GOLD +
                                " Brings up this menu");
                    sender.sendMessage(ChatColor.GOLD + "Permission -" + ChatColor.RED + " nostrip.help");
            }if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!player.hasPermission("nostrip.aliases")) {
                    player.sendMessage(ChatColor.RED + "Permission required");
                }else
                    if (args.length == 1)
                        if (args[0].equalsIgnoreCase("aliases")) {
                            player.sendMessage(ChatColor.GREEN + "--NoStrip--Aliases--:");
                            player.sendMessage(ChatColor.GREEN + "---------------------");
                            player.sendMessage(ChatColor.RED + "/nostrip toggle -" + ChatColor.GOLD +
                                    " /ns toggle, /nos toggle");
                            player.sendMessage(ChatColor.RED + "/nostrip aliases -" + ChatColor.GOLD +
                                    " /ns aliases, /nos aliases");
                            player.sendMessage(ChatColor.RED + "/nostrip help -" + ChatColor.GOLD +
                                    " /ns help, /nos help");
                        }
                    }else
                        if (args.length == 1)
                            if (args[0].equalsIgnoreCase("aliases")) {
                                sender.sendMessage(ChatColor.GREEN + "--NoStrip--Aliases--:");
                                sender.sendMessage(ChatColor.GREEN + "---------------------");
                                sender.sendMessage(ChatColor.RED + "/nostrip toggle -" + ChatColor.GOLD +
                                        " /ns toggle, /nos toggle");
                                sender.sendMessage(ChatColor.RED + "/nostrip aliases -" + ChatColor.GOLD +
                                        " /ns aliases, /nos aliases");
                                sender.sendMessage(ChatColor.RED + "/nostrip help -" + ChatColor.GOLD +
                                        " /ns help, /nos help");
                            }
        return false;
    }

}
