package eu.mcgods.skyblock.listener;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import eu.mcgods.skyblock.database.PlayerCache;
import eu.mcgods.skyblock.utils.itemBuilder;

public class DeathAndRespawnListener implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {

		Player p = e.getEntity();
		World world = p.getWorld();

		if (!world.getName().equals("Hub") && !world.getName().equals("Wald") && !world.getName().equals("End")
				&& !world.getName().equals("Mine") && !world.getName().equals("Nether")
				&& !world.getName().equals("Quelle")) {
			UUID ownerUUID = UUID.fromString(world.getName());

			e.setDeathMessage(null);

			if (e.getDrops() != null) {
				List<ItemStack> list = e.getDrops();
				Iterator<ItemStack> i = list.iterator();

				while (i.hasNext()) {
					ItemStack item = i.next();
					if (item.getType() == Material.PLAYER_HEAD
							&& item.getItemMeta().getDisplayName().equalsIgnoreCase("§7✦ §a§lSkyBlock §7✦")) {
						i.remove();
					}
				}
			}

			if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
				if (PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID) != null) {
					if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
						e.setKeepInventory(true);
						e.setKeepLevel(true);
					}
				} else {
					e.setKeepInventory(true);
					e.setKeepLevel(true);
				}
			}
		}
	}

	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		try {

			Player p = e.getPlayer();

			if (p.getInventory().getItem(8) == null) {
				p.getInventory().setItem(8, itemBuilder.createSkullWithOutLore(1, p.getName(), "§7✦ §a§lSkyBlock §7✦"));
			}
		} catch (NullPointerException nullPointerException) {
		}
	}
}