package me.swipez.inventoryshrink.commands;

import me.swipez.inventoryshrink.InventoryShrink;
import me.swipez.inventoryshrink.item.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class InventoryCommand implements CommandExecutor {

    InventoryShrink plugin;

    public InventoryCommand(InventoryShrink plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player p = (Player) sender;
            if (sender.hasPermission("inventoryshrink.trigger")){
                if (args.length == 1){
                    if (args[0].equals("start")){
                        plugin.gamestarted = true;
                        Bukkit.broadcastMessage(ChatColor.GREEN+"Inventory shrink challenge has started!");
                    }
                    else if (args[0].equals("stop")){
                        plugin.gamestarted = false;
                        for (Player player : Bukkit.getOnlinePlayers()){
                            UUID uuid = player.getUniqueId();
                            plugin.InventorySpace.remove(uuid);
                            if (player.getInventory().contains(ItemManager.UNUSABLE_SPACE)){
                                for (int i=0;i<player.getInventory().getSize();i++){
                                    if (player.getInventory().getItem(i) != null){
                                        if (player.getInventory().getItem(i).equals(ItemManager.UNUSABLE_SPACE)){
                                            player.getInventory().clear(i);
                                        }
                                    }
                                }
                            }
                        }
                        Bukkit.broadcastMessage(ChatColor.GREEN+"Inventory shrink challenge has ended!");
                    }
                    else {
                        p.sendMessage(ChatColor.RED+"/inventoryshrink <start/stop>");
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED+"/inventoryshrink <start/stop>");
                }
            }
            else {
                p.sendMessage(ChatColor.RED+"You do not have the permission to run this command!");
            }
        }
        else {
            sender.sendMessage(ChatColor.RED+"This command is for players only!");
        }
        return true;
    }
}
