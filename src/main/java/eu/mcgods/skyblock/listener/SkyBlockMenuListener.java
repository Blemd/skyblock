package eu.mcgods.skyblock.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import eu.mcgods.skyblock.main.SkyBlock;
import eu.mcgods.skyblock.utils.InventoryBuilder;
import eu.mcgods.skyblock.utils.VoteTool;

public class SkyBlockMenuListener implements Listener {

	private InventoryBuilder invBuilder = new InventoryBuilder();
	VoteTool voteTool = new VoteTool();
	SkyBlock m = SkyBlock.getInstance();

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
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fWerkbank")) {
						if(e.getCurrentItem().getType().equals(Material.CRAFTING_TABLE)) {
							p.openWorkbench(p.getLocation(), true);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Enderchest")) {
						if(e.getCurrentItem().getType().equals(Material.ENDER_CHEST)) {
							p.openInventory(p.getEnderChest());
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Insel-Upgrades")) {
						if(e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
							invBuilder.loadSkyBlockMenu_IslandUpgrades(p);
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Quests")) {
						if(e.getCurrentItem().getType().equals(Material.BOOK)) {
							invBuilder.loadSkyBlockMenu_Quests(p);
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3Schnellreise")) {
						if(e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
							invBuilder.loadFastTravelMenu(p);
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eVote")) {
						if(e.getCurrentItem().getType().equals(Material.NETHER_STAR)) {
							int result = voteTool.makeAVote("mss603a4f9b2931c8x92016761603a4f9c293", p.getName());
							if(result == 0) {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast noch nicht gevoted!" 
								+ "\n" + "Vote hier: §e https://minecraft-server.eu/vote/index/21EBD");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							} else if (result == 1) {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast deinen Vote abgeholt!");
								p.getInventory().addItem(new ItemStack(Material.DIAMOND, 5));
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							} else if (result == 2) {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast deinen Vote bereits abgeholt!");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							} else if (result == 3) {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Es ist ein Fehler aufgetreten, versuche es später erneut.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					}
				}
			}
		} catch (NullPointerException nullPointerException) {
		}
	}
}