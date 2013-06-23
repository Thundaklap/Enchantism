package tk.thundaklap.enchantism;

import org.bukkit.inventory.ItemStack;

import static tk.thundaklap.enchantism.Constants.*;

public class SlotChangeTask implements Runnable {
    private final EnchantInventory inv;
    private final ItemStack oldItem;

    public SlotChangeTask(EnchantInventory inventory, ItemStack old) {
        inv = inventory;
        oldItem = old;
    }

    public void run() {
        ItemStack newItem = inv.getInventory().getItem(SLOT_CURRENT_ITEM);
        if ((oldItem == null && newItem != null) || (oldItem != null && !oldItem.equals(newItem))) {
            inv.slotChange();
        }
    }
}
