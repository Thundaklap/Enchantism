package tk.thundaklap.enchantism;

import org.bukkit.plugin.java.JavaPlugin;

public class Enchantism extends JavaPlugin {

    public EnchantismConfiguration configuration;
    private static Enchantism instance;

    public void onEnable() {

        instance = this;
        saveDefaultConfig();
        configuration = new EnchantismConfiguration();
        getServer().getPluginManager().registerEvents(new EnchantismListener(), this);

    }

    public void onDisable() {
        // TODO: Place any custom disable code here.
    }

    public static Enchantism getInstance() {
        return instance;
    }


}
