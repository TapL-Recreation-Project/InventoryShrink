package me.swipez.inventoryshrink.listeners;

import me.swipez.inventoryshrink.item.ItemManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class UnusableDeny implements Listener {
    @EventHandler
    public void onPlayerClicksUnusable(InventoryClickEvent event){
        if (event.getCurrentItem()!= null){
            if (event.getCurrentItem().isSimilar(ItemManager.UNUSABLE_SPACE)){
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onPlayerDies(PlayerDeathEvent event){
        if (event.getEntity().getInventory().contains(ItemManager.UNUSABLE_SPACE)){
            Inventory inventory = event.getEntity().getInventory();
            for (int i = 0; i < inventory.getSize();i++){
                if (inventory.getItem(i) != null){
                    if (inventory.getItem(i).equals(ItemManager.UNUSABLE_SPACE)){
                        inventory.clear(i);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onPlayerScroll(PlayerItemHeldEvent event){
        if (event.getPlayer().getItemOnCursor().isSimilar(ItemManager.UNUSABLE_SPACE)){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event){
        if (event.getItemDrop().getItemStack().isSimilar(ItemManager.UNUSABLE_SPACE)){
            event.setCancelled(true);
        }
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event){
        if (event.getItem() != null){
            if (event.getItem().isSimilar(ItemManager.UNUSABLE_SPACE)){
                event.setCancelled(true);
            }
        }
    }
}
