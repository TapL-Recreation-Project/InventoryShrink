package me.swipez.inventoryshrink.item;

import me.swipez.inventoryshrink.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public class ItemManager {

    public static final ItemStack UNUSABLE_SPACE = ItemBuilder.of(Material.GRAY_STAINED_GLASS_PANE)
            .name(ChatColor.RED.toString()+ChatColor.BOLD+" ")
            .build();
}
