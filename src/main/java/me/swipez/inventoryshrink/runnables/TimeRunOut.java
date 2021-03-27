package me.swipez.inventoryshrink.runnables;

import me.swipez.inventoryshrink.InventoryShrink;
import me.swipez.inventoryshrink.item.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TimeRunOut extends BukkitRunnable {

    InventoryShrink plugin;

    public TimeRunOut(InventoryShrink plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (plugin.gamestarted){
            if (plugin.inventoryshrinktimer == 0){
                plugin.inventoryshrinktimer = plugin.initialtime;
                for (Player others : Bukkit.getOnlinePlayers()){
                    UUID uuid = others.getUniqueId();
                    int nextspace;
                    List<Integer> integerlist;
                    if (!plugin.InventorySpace.containsKey(uuid)){
                        nextspace = 35;
                    }
                    else {
                        nextspace = plugin.InventorySpace.get(uuid).get(plugin.InventorySpace.get(uuid).size()-1)-1;
                    }
                    if (plugin.InventorySpace.get(uuid) != null){
                        integerlist = plugin.InventorySpace.get(uuid);
                    }
                    else {
                        integerlist = new ArrayList<>();
                    }
                    if (nextspace >= 0){
                        others.sendMessage(ChatColor.GRAY+"[!] Your inventory has now shrunk to "+ChatColor.LIGHT_PURPLE+nextspace+ChatColor.GRAY+" slots!");
                        integerlist.add(nextspace);
                        plugin.InventorySpace.put(uuid, integerlist);
                        for (int i = 0; i < plugin.InventorySpace.get(uuid).size();i++){
                            others.getInventory().setItem(plugin.InventorySpace.get(uuid).get(i), ItemManager.UNUSABLE_SPACE);
                        }
                    }
                    else {
                        plugin.hasFullInventory.put(uuid, true);
                    }
                }
            }
        }
    }
}
