package me.shanodekono.nostrip.listeners;

import me.shanodekono.nostrip.utils.ConfigUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerInteractListener implements Listener {

    private List<Material> logs;
    private List<Material> axes;
    private Map<Material, String> axePermissions;

    private ConfigUtils cfgUtils;

    public PlayerInteractListener(ConfigUtils configUtils) {

        logs = new ArrayList<>();
        axes = new ArrayList<>();
        axePermissions = new HashMap<>();

        logs.add(Material.ACACIA_LOG);
        logs.add(Material.BIRCH_LOG);
        logs.add(Material.DARK_OAK_LOG);
        logs.add(Material.JUNGLE_LOG);
        logs.add(Material.SPRUCE_LOG);
        logs.add(Material.OAK_LOG);
        logs.add(Material.ACACIA_WOOD);
        logs.add(Material.BIRCH_WOOD);
        logs.add(Material.DARK_OAK_WOOD);
        logs.add(Material.JUNGLE_WOOD);
        logs.add(Material.SPRUCE_WOOD);
        logs.add(Material.OAK_WOOD);

        axes.add(Material.WOODEN_AXE);
        axes.add(Material.STONE_AXE);
        axes.add(Material.IRON_AXE);
        axes.add(Material.GOLDEN_AXE);
        axes.add(Material.DIAMOND_AXE);

        axePermissions.put(Material.WOODEN_AXE, "nostrip.axe.wood");
        axePermissions.put(Material.STONE_AXE, "nostrip.axe.stone");
        axePermissions.put(Material.IRON_AXE, "nostrip.axe.iron");
        axePermissions.put(Material.GOLDEN_AXE, "nostrip.axe.gold");
        axePermissions.put(Material.DIAMOND_AXE, "nostrip.axe.diamond");

        cfgUtils = configUtils;
    }

    @EventHandler
    private void onInteract(PlayerInteractEvent event) {


        // If Action is NOT A Right Click, Stop
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        // If The Block Is NOT A Log, Stop
        if (!logs.contains(event.getClickedBlock().getType())) {
            return;
        }

        if (!axes.contains(event.getPlayer().getInventory().getItemInMainHand().getType())) {
            return;
        }

        /*
        NOTE: Toggle Contains The Players Who Want To Prevent Stripping, So We Want To Check If They Are Not In Toggle
        Because That Means They WANT To Strip Logs. If It's Determined That They Want To Strip Logs We Verify They Have
        The Correct Permissions To Strip Logs With The Axe They Are Using
         */
        if (!cfgUtils.toggle.contains(event.getPlayer().getUniqueId())) {

            // If They Have An Individual Axe Permission For The Axe They Are Using, Let Them Strip The Log
            if (event.getPlayer().hasPermission(axePermissions.get(event.getItem().getType()))) {
                return;
            }

            if (!event.getPlayer().hasPermission((axePermissions.get(event.getItem().getType())))) {
                event.getPlayer().sendMessage(cfgUtils.color(cfgUtils.prefix + " " + cfgUtils.noAxePermission));
            }

            // Otherwise, Move On And Prevent Stripping The Log
        }
        event.setCancelled(true);
    }
}
