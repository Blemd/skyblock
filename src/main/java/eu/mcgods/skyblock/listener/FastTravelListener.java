package eu.mcgods.skyblock.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import eu.mcgods.skyblock.main.SkyBlock;
import eu.mcgods.skyblock.utils.LocationManager;

public class FastTravelListener implements Listener {

	private SkyBlock m = SkyBlock.getInstance();
	
	@EventHandler
	public void onClickInInv(InventoryClickEvent e) {
		try {

			Player p = (Player) e.getWhoClicked();

			if (e.getView().getTitle().equalsIgnoreCase("§3Schnellreise")) {
				if (e.getClickedInventory() == e.getView().getTopInventory()) {
					e.setCancelled(true);
					if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
						if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSpawn")) {
							p.teleport(LocationManager.getLocation("Hub"));
							p.sendMessage(m.getPrefix() + "Du wurdest zum §aSpawn §7teleportiert.");
						} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5End")) {
							p.teleport(LocationManager.getLocation("End"));
							p.sendMessage(m.getPrefix() + "Du wurdest zum §5End §7teleportiert.");
						} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Nether")) {
							p.teleport(LocationManager.getLocation("Nether"));
							p.sendMessage(m.getPrefix() + "Du wurdest zum §4Nether §7teleportiert.");
						} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Wald")) {
							p.teleport(LocationManager.getLocation("Wald"));
							p.sendMessage(m.getPrefix() + "Du wurdest zum §2Wald §7teleportiert.");
						} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Mine")) {
							p.teleport(LocationManager.getLocation("Mine"));
							p.sendMessage(m.getPrefix() + "Du wurdest zur §6Mine §7teleportiert.");
						} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Quelle")) {
							p.teleport(LocationManager.getLocation("Quelle"));
							p.sendMessage(m.getPrefix() + "Du wurdest zur §9Quelle §7teleportiert.");
						}
					}
				}
			}
		} catch (NullPointerException nullPointerException) {
		}
	}
}