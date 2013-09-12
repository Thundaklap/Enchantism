package tk.thundaklap.enchantism;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;

public class EnchantismListener implements Listener {


    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            event.setCancelled(true);

            Player thePlayer = event.getPlayer();
            
            Enchantism.openInventories.add(new EnchantInventory(thePlayer, thePlayer.getTargetBlock(null, 500).getLocation(), Enchantism.getInstance().configuration.requireBookshelves));

        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClose(InventoryCloseEvent event) {
        EnchantInventory toRemove = null;

        Enchantism.getInstance().getLogger().info("An inventory was closed!");
        for (EnchantInventory inv : Enchantism.openInventories) {
            if (inv.player.equals(event.getPlayer())) {
                toRemove = inv;
                
                if(inv.updateTask != null){
                    inv.updateTask.cancel();
                }

                ItemStack itemToDrop = inv.getInventory().getItem(4);
                if (itemToDrop != null && itemToDrop.getType() != Material.AIR) {
                    event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), itemToDrop);
                }
                break;
            }
        }

        if (toRemove != null) {
            Enchantism.openInventories.remove(toRemove);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClick(InventoryClickEvent event) {
        for (EnchantInventory inv : Enchantism.openInventories) {
            if (inv.player.equals(event.getWhoClicked())) {
                inv.inventoryClicked(event);
                break;
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryDrag(InventoryDragEvent event) {
        for (EnchantInventory inv : Enchantism.openInventories) {
            if (inv.player.equals(event.getWhoClicked())) {
                inv.inventoryDragged(event);
                break;
            }
        }
    }
}
