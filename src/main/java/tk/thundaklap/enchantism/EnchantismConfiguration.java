package tk.thundaklap.enchantism;

import org.bukkit.enchantments.Enchantment;
import java.util.Map;
import java.util.HashMap;
import org.bukkit.configuration.Configuration;

public class EnchantismConfiguration {
    private Map<Enchantment, Map<Integer, Integer>> cachedCosts = new HashMap<Enchantment, Map<Integer, Integer>>();
    public final boolean enableUnenchantButton;

    public EnchantismConfiguration() {

        Configuration config = Enchantism.getInstance().getConfig();
        for (Enchantment enchant : Enchantment.values()) {
            Map<Integer, Integer> levelsForEnchant = new HashMap<Integer, Integer>();

            for (int i = 1; i <= 4; i++) {
                levelsForEnchant.put(i, config.getInt("enchantments." + enchant.getName().toLowerCase().replace('_', '-') + ".level" + i));
            }
            cachedCosts.put(enchant, levelsForEnchant);
        }
        enableUnenchantButton = config.getBoolean("enable-unenchant-button");
    }

    public int getCost(Enchantment enchant, int level) {
        return cachedCosts.get(enchant).get(level);
    }
}
