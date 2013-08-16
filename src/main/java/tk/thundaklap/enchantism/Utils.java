package tk.thundaklap.enchantism;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public final class Utils {
    private static final List<Enchantment> empty = Collections.<Enchantment>emptyList();
    private static Map<Material, List<Enchantment>> cachedEnchantmentLists = new HashMap<Material, List<Enchantment>>();

    static {
        // cachedEnchantmentLists
        cachedEnchantmentLists.put(Material.BOOK, Arrays.asList(Enchantment.values()));

    }

    /**
     * Get all of the Enchantments that should be displayed for this item
     * (ignoring levels). Assume that the returned list is immutable.
     *
     * @param item
     * @return an immutable list of valid enchantments
     */
    public static List<Enchantment> getEnchantments(ItemStack item) {
        if (item == null) {
            return empty;
        }
        Material type = item.getType();
        if (type == Material.AIR) {
            return empty;
        }

        if (cachedEnchantmentLists.containsKey(type)) {
            return cachedEnchantmentLists.get(type);
        }

        // if (type == Material.BOOK) {
        //     return Arrays.asList(Enchantment.values());
        // }

        List<Enchantment> ret = new ArrayList<Enchantment>(7);
        for (Enchantment enc : Enchantment.values()) {
            if (enc.canEnchantItem(item)) {
                ret.add(enc);
            }
        }

        if (ret.size() == 0) {
            cachedEnchantmentLists.put(type, empty);
            return empty;
        }

        cachedEnchantmentLists.put(type, ret);
        return ret;
    }

    /**
     * Get the amount of bookshelves that an enchantment table would identify as bookshelves.
     *
     * @param loc The location from where the calculation should be made.
     * @return The number of bookshelves that would apply.
     */
    public static int getApplicableBookshelves(Location loc){

        int numBookshelves = 0;

        // A vanilla enchantment table will accept bookshelves from anywhere in a
        // 2-block square radius from it, and up to one block above.

        // TODO: Make bookshelves obstructed by air not count.

        for(int x = -2; x <= 2; x++){
            for(int z = -2; z <= 2; z++){
                if(loc.getWorld().getBlockAt(loc.getBlockX() + x, loc.getBlockY(), loc.getBlockZ() + z).getType().equals(Material.BOOKSHELF)){
                    numBookshelves++;
                }

                if(loc.getWorld().getBlockAt(loc.getBlockX() + x, loc.getBlockY() + 1, loc.getBlockZ() + z).getType().equals(Material.BOOKSHELF)){
                    numBookshelves++;
                }
            }
        }

        return numBookshelves;
    }
}
