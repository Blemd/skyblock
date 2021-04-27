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

public class QuestMidListener implements Listener {

	private SkyBlock m = SkyBlock.getInstance();

	@EventHandler
	public void onClickInMidQuestInv(InventoryClickEvent e) {
		try {

			Player p = (Player) e.getWhoClicked();

			if (e.getView().getTitle().equalsIgnoreCase("§eMittlere Quests")) {
				if (e.getClickedInventory() == e.getView().getTopInventory()) {
					e.setCancelled(true);
					if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSammel 64x Karotten")) {
						if (e.getCurrentItem().getType().equals(Material.CARROT)) {
							if (p.getInventory().containsAtLeast(new ItemStack(Material.CARROT), 64)) {
								p.getInventory().removeItem(new ItemStack(Material.CARROT, 64));
								p.closeInventory();
								p.getInventory().addItem(new ItemStack(Material.DIRT, 10));
								p.updateInventory();
								PlayerCache.addSkyCoinsCache(p.getUniqueId(), 65);
								if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
									PlayerCache.addPlayerQuestCache(p.getUniqueId(), "mid1");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 64x Karotten§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								} else {
									PlayerCache.setPlayerQuestCache(p.getUniqueId(), "mid1");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 64x Karotten§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								}
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSammel 64x Kartoffeln")) {
						if (e.getCurrentItem().getType().equals(Material.POTATO)) {
							if (p.getInventory().containsAtLeast(new ItemStack(Material.POTATO), 64)) {
								p.getInventory().removeItem(new ItemStack(Material.POTATO, 64));
								p.closeInventory();
								p.getInventory().addItem(new ItemStack(Material.DIRT, 10));
								p.updateInventory();
								PlayerCache.addSkyCoinsCache(p.getUniqueId(), 70);
								if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
									PlayerCache.addPlayerQuestCache(p.getUniqueId(), "mid2");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 64x Kartoffeln§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								} else {
									PlayerCache.setPlayerQuestCache(p.getUniqueId(), "mid2");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 64x Kartoffeln§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								}
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSammel 64x Rote Bete")) {
						if (e.getCurrentItem().getType().equals(Material.BEETROOT)) {
							if (p.getInventory().containsAtLeast(new ItemStack(Material.BEETROOT), 64)) {
								p.getInventory().removeItem(new ItemStack(Material.BEETROOT, 64));
								p.closeInventory();
								p.getInventory().addItem(new ItemStack(Material.DIRT, 10));
								p.updateInventory();
								PlayerCache.addSkyCoinsCache(p.getUniqueId(), 75);
								if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
									PlayerCache.addPlayerQuestCache(p.getUniqueId(), "mid3");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 64x Rote Bete§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								} else {
									PlayerCache.setPlayerQuestCache(p.getUniqueId(), "mid3");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 64x Rote Bete§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								}
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSammel 64x Gold")) {
						if (e.getCurrentItem().getType().equals(Material.GOLD_INGOT)) {
							if (p.getInventory().containsAtLeast(new ItemStack(Material.GOLD_INGOT), 64)) {
								p.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 64));
								p.closeInventory();
								p.getInventory().addItem(new ItemStack(Material.OBSIDIAN, 2));
								p.updateInventory();
								PlayerCache.addSkyCoinsCache(p.getUniqueId(), 85);
								if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
									PlayerCache.addPlayerQuestCache(p.getUniqueId(), "mid4");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 64x Gold§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								} else {
									PlayerCache.setPlayerQuestCache(p.getUniqueId(), "mid4");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 64x Gold§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								}
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSammel 128x Lapislazuli")) {
						if (e.getCurrentItem().getType().equals(Material.LAPIS_LAZULI)) {
							if (p.getInventory().containsAtLeast(new ItemStack(Material.LAPIS_LAZULI), 128)) {
								p.getInventory().removeItem(new ItemStack(Material.LAPIS_LAZULI, 128));
								p.closeInventory();
								p.getInventory().addItem(new ItemStack(Material.OBSIDIAN, 2));
								p.updateInventory();
								PlayerCache.addSkyCoinsCache(p.getUniqueId(), 90);
								if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
									PlayerCache.addPlayerQuestCache(p.getUniqueId(), "mid5");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 128x Lapislazuli§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								} else {
									PlayerCache.setPlayerQuestCache(p.getUniqueId(), "mid5");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 128x Lapislazuli§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								}
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cBaue einen Zaubertisch")) {
						if (e.getCurrentItem().getType().equals(Material.ENCHANTING_TABLE)) {
							if (p.getInventory().containsAtLeast(new ItemStack(Material.ENCHANTING_TABLE), 1)) {
								p.getInventory().removeItem(new ItemStack(Material.ENCHANTING_TABLE, 1));
								p.closeInventory();
								p.getInventory().addItem(new ItemStack(Material.OBSIDIAN, 4));
								p.updateInventory();
								PlayerCache.addSkyCoinsCache(p.getUniqueId(), 100);
								if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
									PlayerCache.addPlayerQuestCache(p.getUniqueId(), "mid6");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eBaue einen Zaubertisch§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								} else {
									PlayerCache.setPlayerQuestCache(p.getUniqueId(), "mid6");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eBaue einen Zaubertisch§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								}
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSammel 15x Diamanten")) {
						if (e.getCurrentItem().getType().equals(Material.DIAMOND)) {
							if (p.getInventory().containsAtLeast(new ItemStack(Material.DIAMOND), 15)) {
								p.getInventory().removeItem(new ItemStack(Material.DIAMOND, 15));
								p.closeInventory();
								p.getInventory().addItem(new ItemStack(Material.NETHERITE_INGOT, 1));
								p.updateInventory();
								PlayerCache.addSkyCoinsCache(p.getUniqueId(), 110);
								if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
									PlayerCache.addPlayerQuestCache(p.getUniqueId(), "mid7");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 15x Diamanten§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								} else {
									PlayerCache.setPlayerQuestCache(p.getUniqueId(), "mid7");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 15x Diamanten§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								}
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSammel 128x Zombiefleisch")) {
						if (e.getCurrentItem().getType().equals(Material.ROTTEN_FLESH)) {
							if (p.getInventory().containsAtLeast(new ItemStack(Material.ROTTEN_FLESH), 128)) {
								p.getInventory().removeItem(new ItemStack(Material.ROTTEN_FLESH, 128));
								p.closeInventory();
								p.getInventory().addItem(new ItemStack(Material.DIAMOND, 3));
								p.updateInventory();
								PlayerCache.addSkyCoinsCache(p.getUniqueId(), 115);
								if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
									PlayerCache.addPlayerQuestCache(p.getUniqueId(), "mid8");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 128x Zombiefleisch§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								} else {
									PlayerCache.setPlayerQuestCache(p.getUniqueId(), "mid8");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 128x Zombiefleisch§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								}
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSammel 128x Knochen")) {
						if (e.getCurrentItem().getType().equals(Material.BONE)) {
							if (p.getInventory().containsAtLeast(new ItemStack(Material.BONE), 128)) {
								p.getInventory().removeItem(new ItemStack(Material.BONE, 128));
								p.closeInventory();
								p.getInventory().addItem(new ItemStack(Material.DIAMOND, 3));
								p.updateInventory();
								PlayerCache.addSkyCoinsCache(p.getUniqueId(), 115);
								if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
									PlayerCache.addPlayerQuestCache(p.getUniqueId(), "mid9");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 128x Knochen§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								} else {
									PlayerCache.setPlayerQuestCache(p.getUniqueId(), "mid9");
									p.sendMessage(m.getPrefix() + "Du hast die Quest §8[§eSammel 128x Knochen§8] §7abgeschlossen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								}
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Items im Inventar.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					} else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cSchließe alle mittleren Quests ab")) {
						if(e.getCurrentItem().getType().equals(Material.BEACON)) {
							if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
								if (PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("mid1")
										&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("mid2")
										&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("mid3")
										&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("mid4")
										&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("mid5")
										&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("mid6")
										&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("mid7")
										&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("mid8")
										&& PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("mid9")) {
									p.closeInventory();
									p.getInventory().addItem(new ItemStack(Material.DIAMOND, 4));
									p.updateInventory();
									PlayerCache.addSkyCoinsCache(p.getUniqueId(), 400);
									if (PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
										PlayerCache.addPlayerQuestCache(p.getUniqueId(), "mid10");
										p.sendMessage(m.getPrefix() + "Glückwunsch, du hast alle mittleren Quests abgeschlossen.");
										p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
									} else {
										PlayerCache.setPlayerQuestCache(p.getUniqueId(), "mid10");
										p.sendMessage(m.getPrefix() + "Glückwunsch, du hast alle mittleren Quests abgeschlossen.");
										p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
									}
								} else {
									p.closeInventory();
									p.sendMessage(m.getPrefix() + "Du musst erst alle anderen mittleren Quests abschließen.");
									p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
								}
							} else {
								p.closeInventory();
								p.sendMessage(m.getPrefix() + "Du musst erst alle anderen mittleren Quests abschließen.");
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