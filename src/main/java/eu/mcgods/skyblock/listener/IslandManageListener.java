package eu.mcgods.skyblock.listener;

import java.util.List;

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
import eu.mcgods.skyblock.utils.IslandGenerator;

public class IslandManageListener implements Listener {

	private SkyBlock m = SkyBlock.getInstance();
	private InventoryBuilder invBuilder = new InventoryBuilder();
	private String clickedPlayer = "";

	@EventHandler
	public void onClickInManageInv(InventoryClickEvent e) {
		try {

		Player p = (Player) e.getWhoClicked();

		if (e.getView().getTitle().equalsIgnoreCase("§2Insel-Verwalten")) {
			if (e.getClickedInventory() == e.getView().getTopInventory()) {
				e.setCancelled(true);
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eSpawnpunkt")) {
					if (e.getCurrentItem().getType().equals(Material.RED_BED)) {
						p.closeInventory();
						IslandGenerator.setIslandWarpPoint(p.getWorld(), p.getUniqueId(), p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eCoop-Spielerliste")) {
					if (e.getCurrentItem().getType().equals(Material.PAPER)) {
						if(PlayerCache.getCoopPlayerCacheSize(p.getUniqueId()) != null) {
							invBuilder.loadSkyBlockMenu_IslandManage_CoopList(p);							
						} else {
							p.closeInventory();
							p.sendMessage(m.getPrefix() + "Du hast derzeit noch keine Spieler hinzugefügt.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§ePvP")) {
					if (e.getCurrentItem().getType().equals(Material.IRON_SWORD)) {
						if (p.getWorld().getName().equalsIgnoreCase(p.getUniqueId().toString())) {
							if (p.getWorld().getPVP() == false) {
								p.closeInventory();
								p.getWorld().setPVP(true);
								p.sendMessage(m.getPrefix() + "Du hast PvP auf deiner Insel aktiviert.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							} else {
								p.closeInventory();
								p.getWorld().setPVP(false);
								p.sendMessage(m.getPrefix() + "Du hast PvP auf deiner Insel deaktiviert.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							}
						} else {
							p.closeInventory();
							p.sendMessage(m.getPrefix() + "Dafür musst du auf deiner eigenen Insel sein.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					}
				} else if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
					
					List<String> playernames = PlayerCache.getCoopPlayerCacheNames(p.getUniqueId());
					
					clickedPlayer = e.getCurrentItem().getItemMeta().getDisplayName();
					
					if (playernames.contains(e.getCurrentItem().getItemMeta().getDisplayName())) {
						invBuilder.loadSkyBlockMenu_IslandManage_CoopRemove(p);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aBestätigen")) {
					if(e.getCurrentItem().getType().equals(Material.LIME_WOOL)) {
						p.closeInventory();
						
						Player target = Bukkit.getPlayer(clickedPlayer);
						System.out.println(target.getName());
						
						PlayerCache.removeCoopPlayerCache(p.getUniqueId(), target.getUniqueId().toString());
						p.sendMessage(m.getPrefix() + "Der Spieler §e" + target.getName() + " §7wurde von deiner Insel entfernt.");
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Abbrechen")) {
					if(e.getCurrentItem().getType().equals(Material.RED_WOOL)) {
						p.closeInventory();
						
						Player target = Bukkit.getPlayer(clickedPlayer);
						
						p.sendMessage(m.getPrefix() + "Der Spieler §e" + target.getName() + " §7wurde nicht von deiner Insel entfernt.");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
					}
				}
			}
		}
		} catch (Exception exception) {
		}
	}
}