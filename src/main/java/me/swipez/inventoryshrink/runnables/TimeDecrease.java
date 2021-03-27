package me.swipez.inventoryshrink.runnables;

import me.swipez.inventoryshrink.InventoryShrink;
import me.swipez.inventoryshrink.item.ItemManager;
import me.swipez.inventoryshrink.utils.SendTitleBarMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class TimeDecrease extends BukkitRunnable {

    InventoryShrink plugin;

    public TimeDecrease(InventoryShrink plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (plugin.gamestarted){
            if (plugin.inventoryshrinktimer > 0){
                plugin.inventoryshrinktimer--;
                for (Player others : Bukkit.getOnlinePlayers()) {
                    UUID uuid = others.getUniqueId();
                    if (!plugin.hasFullInventory.containsKey(uuid)) {
                        SendTitleBarMessage.sendMessage(others, ChatColor.LIGHT_PURPLE + "An inventory slot will be removed in", plugin.inventoryshrinktimer);
                    }
                    if (plugin.InventorySpace.containsKey(uuid)) {
                        for (int i = 0; i < plugin.InventorySpace.get(uuid).size(); i++) {
                            others.getInventory().setItem(plugin.InventorySpace.get(uuid).get(i), ItemManager.UNUSABLE_SPACE);
                        }
                    }
                }
            }
        }
    }
}
