package eu.mcgods.skyblock.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import eu.mcgods.skyblock.utils.InventoryBuilder;

public class SkyBlockMenuItemListener implements Listener {

	private InventoryBuilder invBuilder = new InventoryBuilder();
	
	@EventHandler
	public void onTryToDrop(PlayerDropItemEvent e) {
		try {
			ItemStack item = new ItemStack(e.getItemDrop().getItemStack());

			if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§7✦ §a§lSkyBlock §7✦")) {
				if (item.getType().equals(Material.PLAYER_HEAD)) {
					if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
						e.setCancelled(true);
					}
				}
			}
		} catch (NullPointerException nullPointerException) {
		}
	}

	@EventHandler
	public void onTryToMoveInInv(InventoryClickEvent e) {
		try {

			Player p = (Player) e.getWhoClicked();

			if (e.getCurrentItem() != null) {
				if (e.getHotbarButton() == 8) {
					if (p.getGameMode() != GameMode.CREATIVE) {
						e.setCancelled(true);
					}
				}
			}

			if (e.getCurrentItem().getType() != null && e.getCurrentItem().getType() != Material.AIR) {
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7✦ §a§lSkyBlock §7✦")) {
					if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
						if (p.getGameMode() != GameMode.CREATIVE) {
							e.setCancelled(true);
						}
					}
				}
			}
		} catch (NullPointerException nullPointerException) {
		}
	}

	@EventHandler
	public void onTryToSwap(PlayerSwapHandItemsEvent e) {
		try {
			if (e.getMainHandItem().getType().equals(Material.PLAYER_HEAD)) {
				if (e.getMainHandItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7✦ §a§lSkyBlock §7✦")) {
					e.setCancelled(true);
				}
			}

			if (e.getOffHandItem().getType().equals(Material.PLAYER_HEAD)) {
				if (e.getOffHandItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7✦ §a§lSkyBlock §7✦")) {
					e.setCancelled(true);
				}
			}
		} catch (NullPointerException nullPointerException) {
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		try {
			if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7✦ §a§lSkyBlock §7✦")) {
					if (e.getItem().getType().equals(Material.PLAYER_HEAD)) {
						if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
							invBuilder.loadSkyBlockMenu(e.getPlayer());
						}
					}
				}
			}
		} catch (NullPointerException nullPointerException) {
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onTryToPlace(BlockPlaceEvent e) {
		try {

			Player p = e.getPlayer();

			if (p.getGameMode() != GameMode.CREATIVE) {
				if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7✦ §a§lSkyBlock §7✦")) {
					if (p.getItemInHand().getType().equals(Material.PLAYER_HEAD)) {
						e.setCancelled(true);
					}
				}
			}
		} catch (NullPointerException nullPointerException) {
		}
	}
}