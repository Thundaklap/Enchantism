package tk.thundaklap.enchantism;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;

public class EnchantismListener implements Listener {

    private static List<EnchantInventory> inventories = new ArrayList<EnchantInventory>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (event.getInventory() instanceof EnchantingInventory) {
            event.setCancelled(true);

            inventories.add(new EnchantInventory((Player) event.getPlayer()));
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClose(InventoryCloseEvent event) {
        EnchantInventory toRemove = null;

        for (EnchantInventory inv : inventories) {
            if (inv.player.equals(event.getPlayer())) {
                ItemStack itemToDrop = inv.getInventory().getItem(4);

                if (itemToDrop != null && itemToDrop.getType() != Material.AIR) {
                    event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), inv.getInventory().getItem(4));
                }

                toRemove = inv;
                break;
            }
        }

        if (toRemove != null) {
            inventories.remove(toRemove);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClick(InventoryClickEvent event) {
        for (EnchantInventory inv : inventories) {
            if (inv.player.equals(event.getWhoClicked())) {
                inv.inventoryClicked(event);
                break;
            }
        }
    }
}
