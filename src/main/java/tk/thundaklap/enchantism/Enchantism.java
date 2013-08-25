package tk.thundaklap.enchantism;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.plugin.java.JavaPlugin;

public class Enchantism extends JavaPlugin {

    public EnchantismConfiguration configuration;
    private static Enchantism instance;
    static List<EnchantInventory> openInventories;

    public void onEnable() {
        
        openInventories = new ArrayList<EnchantInventory>();
        
        instance = this;
        saveDefaultConfig();
        configuration = new EnchantismConfiguration();
        getServer().getPluginManager().registerEvents(new EnchantismListener(), this);

    }

    public void onDisable() {
        for(EnchantInventory inv : openInventories){
            inv.player.closeInventory();
        }
        
        openInventories.clear();
    }

    public static Enchantism getInstance() {
        return instance;
    }
}
