package me.swipez.inventoryshrink;

import me.swipez.inventoryshrink.commands.CommandComplete;
import me.swipez.inventoryshrink.commands.InventoryCommand;
import me.swipez.inventoryshrink.listeners.UnusableDeny;
import me.swipez.inventoryshrink.runnables.RemoveNearbyUnusablesRunnable;
import me.swipez.inventoryshrink.runnables.TimeDecrease;
import me.swipez.inventoryshrink.runnables.TimeRunOut;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class InventoryShrink extends JavaPlugin {

    public HashMap<UUID, Boolean> hasFullInventory = new HashMap<>();
    public HashMap<UUID, List<Integer>> InventorySpace = new HashMap<>();
    public int initialtime = getConfig().getInt("inventory-shrink-time");
    public boolean gamestarted = false;
    public int inventoryshrinktimer = initialtime;

    @Override
    public void onEnable() {
        //Runnables
        BukkitTask TimeDecrease = new TimeDecrease(this).runTaskTimer(this, 20, 20);
        BukkitTask TimeRunOut = new TimeRunOut(this).runTaskTimer(this, 20 , 20);
        BukkitTask RemoveUnusedItemsDropped = new RemoveNearbyUnusablesRunnable(this).runTaskTimer(this, 1, 1);

        //Listener
        getServer().getPluginManager().registerEvents(new UnusableDeny(), this);

        //Commands
        getCommand("inventoryshrink").setExecutor(new InventoryCommand(this));
        getCommand("inventoryshrink").setTabCompleter(new CommandComplete());

        //Config
        saveDefaultConfig();
        getConfig().options().copyDefaults();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
