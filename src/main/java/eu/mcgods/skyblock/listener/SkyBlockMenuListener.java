package eu.mcgods.skyblock.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SkyBlockMenuListener implements Listener {

	@EventHandler
	public void onClickInMenu(InventoryClickEvent e) {
		try {

			if (e.getView().getTitle().equalsIgnoreCase("�a�lSkyblock-Men�")) {
				if (e.getClickedInventory() == e.getView().getTopInventory()) {
					e.setCancelled(true);
				}
			}
		} catch (NullPointerException nullPointerException) {
		}
	}
}