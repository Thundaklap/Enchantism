package tk.thundaklap.enchantism;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class DelayUpdateInventory extends BukkitRunnable {

    private final Player player;

    public DelayUpdateInventory(Player player) {
        this.player = player;

    }

    public void run() {
        player.updateInventory();
    }
}
