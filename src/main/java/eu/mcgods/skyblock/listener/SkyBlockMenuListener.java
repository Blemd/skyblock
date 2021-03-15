package eu.mcgods.skyblock.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import eu.mcgods.skyblock.utils.InventoryBuilder;

public class SkyBlockMenuListener implements Listener {

	private InventoryBuilder invBuilder = new InventoryBuilder();
	
	@EventHandler
	public void onClickInMenu(InventoryClickEvent e) {
		try {

			Player p = (Player) e.getWhoClicked();
			
			if (e.getView().getTitle().equalsIgnoreCase("§a§lSkyblock-Menü")) {
				if (e.getClickedInventory() == e.getView().getTopInventory()) {
					e.setCancelled(true);
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Insel-Verwalten")) {
						if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
							invBuilder.loadSkyBlockMenu_IslandManage(p);
						}
					}
				}
			}
		} catch (NullPointerException nullPointerException) {
		}
	}
}