package tk.thundaklap.enchantism;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DelayUpdateInventory extends BukkitRunnable {
    private final Player player;

    public DelayUpdateInventory(Player player) {
        this.player = player;
    }

    @SuppressWarnings("deprecation")
    public void run() {
        if (player.isValid()) {
            player.updateInventory();
        }
    }
}
