package eu.mcgods.skyblock.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import eu.mcgods.skyblock.database.PlayerCache;
import eu.mcgods.skyblock.main.SkyBlock;
import eu.mcgods.skyblock.utils.InventoryBuilder;

public class IslandUpgradeListener implements Listener {

	private SkyBlock m = SkyBlock.getInstance();
	private InventoryBuilder invBuilder = new InventoryBuilder();
	
	@EventHandler
	public void onClickUpgradeInv(InventoryClickEvent e) {
		try {
			
			Player p = (Player) e.getWhoClicked();
			
			if(e.getView().getTitle().equalsIgnoreCase("§2Insel-Upgrades")) {
				if(e.getClickedInventory() == e.getView().getTopInventory()) {
					e.setCancelled(true);
					if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Insel-Border")) {
						if(e.getCurrentItem().getType().equals(Material.END_CRYSTAL)) {
							if(Bukkit.getWorld(p.getUniqueId().toString()) != null) {
								invBuilder.loadSkyBlockMenu_IslandUpgrades_IslandBorder(p);
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Deine Insel muss dafür geladen sein.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					} else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cStufe-1")) {
						if(e.getCurrentItem().getType().equals(Material.LEATHER_HORSE_ARMOR)) {
							if(PlayerCache.getSkyCoinsCache(p.getUniqueId()) >= 2000) {
								p.closeInventory();
								PlayerCache.removeSkyCoinsCache(p.getUniqueId(), 2000);
								Bukkit.getWorld(p.getUniqueId().toString()).getWorldBorder().setSize(16*8D);
								p.sendMessage(m.getPrefix() + "Deine Insel wurde vergrößert.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast nicht genügend §6SkyCoins §7dafür.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cStufe-2")) {
						if(e.getCurrentItem().getType().equals(Material.IRON_HORSE_ARMOR)) {
							if(PlayerCache.getSkyCoinsCache(p.getUniqueId()) >= 5000) {
								p.closeInventory();
								PlayerCache.removeSkyCoinsCache(p.getUniqueId(), 5000);
								Bukkit.getWorld(p.getUniqueId().toString()).getWorldBorder().setSize(16*10D);
								p.sendMessage(m.getPrefix() + "Deine Insel wurde vergrößert.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast nicht genügend §6SkyCoins §7dafür.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cStufe-3")) {
						if(e.getCurrentItem().getType().equals(Material.GOLDEN_HORSE_ARMOR)) {
							if(PlayerCache.getSkyCoinsCache(p.getUniqueId()) >= 10000) {
								p.closeInventory();
								PlayerCache.removeSkyCoinsCache(p.getUniqueId(), 10000);
								Bukkit.getWorld(p.getUniqueId().toString()).getWorldBorder().setSize(16*12D);
								p.sendMessage(m.getPrefix() + "Deine Insel wurde vergrößert.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast nicht genügend §6SkyCoins §7dafür.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cStufe-4")) {
						if(e.getCurrentItem().getType().equals(Material.DIAMOND_HORSE_ARMOR)) {
							if(PlayerCache.getSkyCoinsCache(p.getUniqueId()) >= 20000) {
								p.closeInventory();
								PlayerCache.removeSkyCoinsCache(p.getUniqueId(), 20000);
								Bukkit.getWorld(p.getUniqueId().toString()).getWorldBorder().setSize(16*14D);
								p.sendMessage(m.getPrefix() + "Deine Insel wurde vergrößert.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast nicht genügend §6SkyCoins §7dafür.");
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