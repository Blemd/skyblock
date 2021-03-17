package eu.mcgods.skyblock.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import eu.mcgods.skyblock.utils.InventoryBuilder;

public class QuestMenuListener implements Listener {

	private InventoryBuilder invBuilder = new InventoryBuilder();

	@EventHandler
	public void onClickinQuestMenu(InventoryClickEvent e) {
		try {

			Player p = (Player) e.getWhoClicked();

			if (e.getView().getTitle().equalsIgnoreCase("§4Quests")) {
				if (e.getClickedInventory() == e.getView().getTopInventory()) {
					e.setCancelled(true);
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aEinfache Quests")) {
						if (e.getCurrentItem().getType().equals(Material.LEATHER_HELMET)) {
							invBuilder.loadSkyBlockMenu_Quests_Easy(p);
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eMittlere Quests")) {
						if(e.getCurrentItem().getType().equals(Material.IRON_HELMET)) {
							
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSchwere Quests")) {
						if(e.getCurrentItem().getType().equals(Material.DIAMOND_HELMET)) {
							
						}
					}
				}
			}
		} catch (Exception exception) {
		}
	}
}