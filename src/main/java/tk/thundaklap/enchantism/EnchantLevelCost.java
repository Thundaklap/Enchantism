package tk.thundaklap.enchantism;

import org.bukkit.enchantments.Enchantment;

public class EnchantLevelCost {
    public Enchantment enchant;
    public int level;
    public int cost;

    public EnchantLevelCost(Enchantment enchant, int level) {
        this.enchant = enchant;
        this.level = level;
        this.cost = Enchantism.getInstance().configuration.getCost(enchant, level);
    }
}
