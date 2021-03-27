package me.swipez.inventoryshrink.runnables;

import me.swipez.inventoryshrink.InventoryShrink;
import me.swipez.inventoryshrink.item.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class RemoveNearbyUnusablesRunnable extends BukkitRunnable {

    InventoryShrink plugin;

    public RemoveNearbyUnusablesRunnable(InventoryShrink plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (plugin.gamestarted){
            for (Player player : Bukkit.getOnlinePlayers()){
                List<Entity> nearby = player.getNearbyEntities(3,3,3);
                for (Entity entity : nearby){
                    if (entity.getType().equals(EntityType.DROPPED_ITEM)){
                        Item item = (Item) entity;
                        if (item.getItemStack().isSimilar(ItemManager.UNUSABLE_SPACE)){
                            item.remove();
                        }
                    }
                }
            }
        }
    }
}
