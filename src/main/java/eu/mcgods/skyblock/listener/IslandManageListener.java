package eu.mcgods.skyblock.listener;

import java.util.List;

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

	@EventHandler
	public void onClickInManageInv(InventoryClickEvent e) {
//		try {

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
						List<String> playernames = null;
						try {
							playernames = PlayerCache.getCoopPlayerCacheNames(p.getUniqueId());				
						} catch (NullPointerException nullPointerException) {
							p.closeInventory();
							p.sendMessage(m.getPrefix() + "Du hast derzeit keine Spieler zu deiner Insel hinzugefügt.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
						if(playernames != null) {							
							invBuilder.loadSkyBlockMenu_IslandManage_CoopList(p);
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
					
					if (playernames.contains(e.getCurrentItem().getItemMeta().getDisplayName())) {
						p.closeInventory();
						p.sendMessage(m.getPrefix() + "Du hast den Spieler entfernt.");
					}
				}
				
//				String[] coopMember = coopAPI.getCoopPartners(p.getUniqueId(), p.getUniqueId().toString()).split("\\;");
//
//				List<Player> playerList = new ArrayList<Player>();
//				List<OfflinePlayer> offlinePlayerList = new ArrayList<OfflinePlayer>();
//
//				List<String> playernames = new ArrayList<String>();
//
//				ArrayList<String> coopList = new ArrayList<String>();
//				coopList.addAll(Arrays.asList(coopMember));
//
//				// Put all coop players from database to playerlist & offlineplayerlist
//				for (int i = 0; i < coopList.size(); i++) {
//					UUID coopUUID = UUID.fromString(coopList.get(i));
//
//					Player player = Bukkit.getPlayer(coopUUID);
//					if (player != null) {
//						playerList.add(player);
//					} else {
//						OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(coopUUID);
//						offlinePlayerList.add(offlinePlayer);
//					}
//				}
//				
//				// Add online players to playernames list
//				if (playerList != null) {
//					for (int i = 0; i < playerList.size(); i++) {
//						playernames.add(playerList.get(i).getName());
//					}
//				}
//
//				// Add offline players to playernames list
//				if (offlinePlayerList != null) {
//					for (int i = 0; i < offlinePlayerList.size(); i++) {
//						playernames.add(offlinePlayerList.get(i).getName());
//					}
//				}
			}
		}
//		} catch (Exception exception) {
//		}
	}
}