package eu.mcgods.skyblock.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import eu.mcgods.skyblock.database.PlayerCache;
import eu.mcgods.skyblock.main.SkyBlock;

public class QuestEasyListener implements Listener {

	private SkyBlock m = SkyBlock.getInstance();

	@EventHandler
	public void onClickInEasyQuestsInv(InventoryClickEvent e) {
		try {

		Player p = (Player) e.getWhoClicked();

		if (e.getView().getTitle().equalsIgnoreCase("§aEinfache Quests")) {
			if (e.getClickedInventory() == e.getView().getTopInventory()) {
				e.setCancelled(true);
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cBaue eine Werkbank")) {
					if (e.getCurrentItem().getType().equals(Material.CRAFTING_TABLE)) {
						if (p.getInventory().containsAtLeast(new ItemStack(Material.CRAFTING_TABLE), 1)) {
							p.getInventory().removeItem(new ItemStack(Material.CRAFTING_TABLE, 1));
							p.closeInventory();
							p.getInventory().addItem(new ItemStack(Material.OAK_SAPLING, 1));
							p.updateInventory();
							PlayerCache.addSkyCoinsCache(p.getUniqueId(), 5);
							if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
								PlayerCache.addPlayerQuestCache(p.getUniqueId(), "easy1");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eBaue eine Werkbank§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							} else {
								PlayerCache.setPlayerQuestCache(p.getUniqueId(), "easy1");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eBaue eine Werkbank§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							}
						} else {
							p.closeInventory();
							p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSammel 32x Erde")) {
					if (e.getCurrentItem().getType().equals(Material.DIRT)) {
						if (p.getInventory().containsAtLeast(new ItemStack(Material.DIRT), 32)) {
							p.getInventory().removeItem(new ItemStack(Material.DIRT, 32));
							p.closeInventory();
							p.getInventory().addItem(new ItemStack(Material.PODZOL, 4));
							p.updateInventory();
							PlayerCache.addSkyCoinsCache(p.getUniqueId(), 35);
							if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
								PlayerCache.addPlayerQuestCache(p.getUniqueId(), "easy2");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 32x Erde§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							} else {
								PlayerCache.setPlayerQuestCache(p.getUniqueId(), "easy2");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 32x Erde§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							}
						} else {
							p.closeInventory();
							p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase("§cSammel 30x Eichenholz")) {
					if (e.getCurrentItem().getType().equals(Material.OAK_LOG)) {
						if (p.getInventory().containsAtLeast(new ItemStack(Material.OAK_LOG), 30)) {
							p.getInventory().removeItem(new ItemStack(Material.OAK_LOG, 30));
							p.closeInventory();
							p.getInventory().addItem(new ItemStack(Material.STONE_AXE, 1));
							p.updateInventory();
							PlayerCache.addSkyCoinsCache(p.getUniqueId(), 10);
							if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
								PlayerCache.addPlayerQuestCache(p.getUniqueId(), "easy3");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 30x Eichenholz§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							} else {
								PlayerCache.setPlayerQuestCache(p.getUniqueId(), "easy3");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 30x Eichenholz§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							}
						} else {
							p.closeInventory();
							p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSammel 128x Bruchstein")) {
					if (e.getCurrentItem().getType().equals(Material.COBBLESTONE)) {
						if (p.getInventory().containsAtLeast(new ItemStack(Material.COBBLESTONE), 128)) {
							p.getInventory().removeItem(new ItemStack(Material.COBBLESTONE, 128));
							p.closeInventory();
							p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 6));
							p.updateInventory();
							PlayerCache.addSkyCoinsCache(p.getUniqueId(), 20);
							if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
								PlayerCache.addPlayerQuestCache(p.getUniqueId(), "easy4");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 128x Bruchstein§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							} else {
								PlayerCache.setPlayerQuestCache(p.getUniqueId(), "easy4");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 128x Bruchstein§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							}
						} else {
							p.closeInventory();
							p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cBaue einen Ofen")) {
					if (e.getCurrentItem().getType().equals(Material.FURNACE)) {
						if (p.getInventory().containsAtLeast(new ItemStack(Material.FURNACE), 1)) {
							p.getInventory().removeItem(new ItemStack(Material.FURNACE, 1));
							p.closeInventory();
							p.getInventory().addItem(new ItemStack(Material.COAL, 10));
							p.updateInventory();
							PlayerCache.addSkyCoinsCache(p.getUniqueId(), 15);
							if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
								PlayerCache.addPlayerQuestCache(p.getUniqueId(), "easy5");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eBaue einen Ofen§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							} else {
								PlayerCache.setPlayerQuestCache(p.getUniqueId(), "easy5");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eBaue einen Ofen§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							}
						} else {
							p.closeInventory();
							p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSammel 16x Kohle")) {
					if (e.getCurrentItem().getType().equals(Material.COAL)) {
						if (p.getInventory().containsAtLeast(new ItemStack(Material.COAL), 16)) {
							p.getInventory().removeItem(new ItemStack(Material.COAL, 16));
							p.closeInventory();
							p.getInventory().addItem(new ItemStack(Material.LAVA_BUCKET, 1));
							p.updateInventory();
							PlayerCache.addSkyCoinsCache(p.getUniqueId(), 25);
							if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
								PlayerCache.addPlayerQuestCache(p.getUniqueId(), "easy6");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 16x Kohle§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							} else {
								PlayerCache.setPlayerQuestCache(p.getUniqueId(), "easy6");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 16x Kohle§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							}
						} else {
							p.closeInventory();
							p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSammel 32x Eisen")) {
					if (e.getCurrentItem().getType().equals(Material.IRON_INGOT)) {
						if (p.getInventory().containsAtLeast(new ItemStack(Material.IRON_INGOT), 32)) {
							p.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 32));
							p.closeInventory();
							p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
							p.updateInventory();
							PlayerCache.addSkyCoinsCache(p.getUniqueId(), 30);
							if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
								PlayerCache.addPlayerQuestCache(p.getUniqueId(), "easy7");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 32x Eisen§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							} else {
								PlayerCache.setPlayerQuestCache(p.getUniqueId(), "easy7");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 32x Eisen§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							}
						} else {
							p.closeInventory();
							p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase("§cSammel 256x Melonenscheiben")) {
					if (e.getCurrentItem().getType().equals(Material.MELON)) {
						if (p.getInventory().containsAtLeast(new ItemStack(Material.MELON_SLICE), 256)) {
							p.getInventory().removeItem(new ItemStack(Material.MELON_SLICE, 256));
							p.closeInventory();
							p.getInventory().addItem(new ItemStack(Material.CARROT, 5));
							p.updateInventory();
							PlayerCache.addSkyCoinsCache(p.getUniqueId(), 100);
							if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
								PlayerCache.addPlayerQuestCache(p.getUniqueId(), "easy8");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 256x Melonenscheiben§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							} else {
								PlayerCache.setPlayerQuestCache(p.getUniqueId(), "easy8");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 256x Melonenscheiben§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							}
						} else {
							p.closeInventory();
							p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSammel 48x Äpfel")) {
					if (e.getCurrentItem().getType().equals(Material.APPLE)) {
						if (p.getInventory().containsAtLeast(new ItemStack(Material.APPLE), 48)) {
							p.getInventory().removeItem(new ItemStack(Material.APPLE, 48));
							p.closeInventory();
							p.getInventory().addItem(new ItemStack(Material.SPRUCE_SAPLING, 1));
							p.updateInventory();
							PlayerCache.addSkyCoinsCache(p.getUniqueId(), 50);
							if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
								PlayerCache.addPlayerQuestCache(p.getUniqueId(), "easy9");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 48x Äpfel§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							} else {
								PlayerCache.setPlayerQuestCache(p.getUniqueId(), "easy9");
								p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 48x Äpfel§8] §7abgeschlossen.");
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							}
						} else {
							p.closeInventory();
							p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					}
				} else if (e.getCurrentItem().getItemMeta().getDisplayName()
						.equalsIgnoreCase("§cSchließe alle einfachen Quests ab")) {
					if (e.getCurrentItem().getType().equals(Material.BEACON)) {
						if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
							if (PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy1")
									&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy2")
									&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy3")
									&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy4")
									&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy5")
									&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy6")
									&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy7")
									&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy8")
									&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy9")) {
								p.closeInventory();
								p.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
								p.updateInventory();
								PlayerCache.addSkyCoinsCache(p.getUniqueId(), 200);
								if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
									PlayerCache.addPlayerQuestCache(p.getUniqueId(), "easy10");
									p.sendMessage(m.getPrefix() + "Glückwunsch, du hast alle einfachen Quests abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								} else {
									PlayerCache.setPlayerQuestCache(p.getUniqueId(), "easy10");
									p.sendMessage(m.getPrefix() + "Glückwunsch, du hast alle einfachen Quests abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								}
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du musst erst alle anderen einfachen Quests abschließen.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						} else {
							p.closeInventory();
							p.sendMessage(m.getPrefix() + "Du musst erst alle anderen einfachen Quests abschließen.");
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