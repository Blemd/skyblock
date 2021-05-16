package eu.mcgods.skyblock.listener;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import eu.mcgods.skyblock.database.PlayerCache;
import eu.mcgods.skyblock.main.SkyBlock;
import eu.mcgods.skyblock.utils.InventoryBuilder;
import eu.mcgods.skyblock.utils.IslandGenerator;

public class IslandNpcListener implements Listener {

	private SkyBlock m = SkyBlock.getInstance();
	private InventoryBuilder invBuilder = new InventoryBuilder();
	
	@EventHandler
	public void onInteract(PlayerInteractAtEntityEvent e) {
		try {
			
			Player p = e.getPlayer();
			
			if(e.getRightClicked().getName().equalsIgnoreCase("§aInsel Meister") && e.getRightClicked().getType().equals(EntityType.PLAYER)) {
				invBuilder.loadIslandNpcMenu(p);
			}
		} catch (Exception exception) {
		}
	}
	
	@EventHandler
	public void clickInMenu(InventoryClickEvent e) {
		try {
			
			Player p = (Player) e.getWhoClicked();
			UUID uuid = p.getUniqueId();
			
			File world = null;
			
			try {
				world = new File(Bukkit.getServer().getWorldContainer(), uuid.toString());
			} catch(NullPointerException nullPointerException) {
			}
			
			if(e.getView().getTitle().equalsIgnoreCase("§aInsel Meister")) {
				if(e.getClickedInventory() == e.getView().getTopInventory()) {
					e.setCancelled(true);
					if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eInsel-Erstellen")) {
						if(e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
							if(!world.exists()) {
								if(PlayerCache.getSkyCoinsCache(uuid) >= 1000) {
									p.closeInventory();
									p.sendMessage(m.getPrefix() + "Deine Insel wird generiert...");
									PlayerCache.removeSkyCoinsCache(uuid, 1000);
									IslandGenerator.generateIsland(uuid.toString(), Difficulty.EASY, 16*40D);
									PlayerCache.setIslandSizeCache(uuid, 1);
									p.teleport(Bukkit.getWorld(uuid.toString()).getSpawnLocation());
									p.sendMessage(m.getPrefix() + "Deine Insel wurde generiert.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								} else {
									p.closeInventory();
									p.sendMessage(m.getPrefix() + "Du hast nicht genügend SkyCoins.");
									p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
								}
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast bereits eine Insel.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					}
				}
			}
		} catch (Exception exception) {
		}
	}
}