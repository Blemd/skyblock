package eu.mcgods.skyblock.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import eu.mcgods.skyblock.database.PlayerCache;

public class InventoryBuilder {

	private Inventory skyBlockMenu = Bukkit.createInventory(null, 6 * 9, "§a§lSkyblock-Menü");
	
	private Inventory skyBlockMenu_islandManage = Bukkit.createInventory(null, 9, "§2Insel-Verwalten");
	private Inventory skyBlockMenu_islandManage_CoopList = Bukkit.createInventory(null, 9, "§2Insel-Verwalten");
	private Inventory skyBlockMenu_islandManage_CoopRemove = Bukkit.createInventory(null, 9, "§2Insel-Verwalten");
	
	private Inventory skyBlockMenu_IslandUpgrades = Bukkit.createInventory(null, 9, "§2Insel-Upgrades");
	private Inventory skyBlockMenu_IslandUpgrades_IslandBorder = Bukkit.createInventory(null, 9, "§2Insel-Upgrades");
	
	private Inventory skyBlockMenu_Quests = Bukkit.createInventory(null, 9, "§4Quests");
	private Inventory skyBlockMenu_Quests_Easy = Bukkit.createInventory(null, 3 * 9, "§aEinfache Quests");
//	private Inventory skyBlockMenu_Quests_Mid = Bukkit.createInventory(null, 3 * 9, "§eMittlere Quests");
//	private Inventory skyBlockMenu_Quests_Hard = Bukkit.createInventory(null, 3 * 9, "§cSchwere Quests");
	
	private Inventory skyBlockMenu_FastTravel = Bukkit.createInventory(null, 9, "§3Schnellreise");
	
	private Inventory islandNpcMenu = Bukkit.createInventory(null, 9, "§aInsel Meister");

	public void loadSkyBlockMenu(Player p) {

		UUID uuid = p.getUniqueId();

		for (int i = 0; i <= 53; i++) {
			if (i != 4 && i != 10 && i != 16 && i != 20 && i != 22 && i != 24 && i != 30 && i != 31 && i != 32
					&& i != 40) {
				this.skyBlockMenu.setItem(i, itemBuilder.createItemWithOutLore(Material.BLACK_STAINED_GLASS_PANE, 1, " "));
			}
		}
		this.skyBlockMenu.setItem(4, itemBuilder.createSkullWithLore(1, p.getName(), "§e" + p.getName(), "§6SkyCoins: " + PlayerCache.getSkyCoinsCache(uuid)));
		this.skyBlockMenu.setItem(10, itemBuilder.createItemWithLore(Material.NETHER_STAR, 1, "§eVote", "§7➥ Vote für das Netzwerk und erhalte eine Belohnung."));
		this.skyBlockMenu.setItem(16, itemBuilder.createItemWithLore(Material.REPEATER, 1, "§eSpieler-Einstellungen", "§7➥ §cKommt demnächst..."));
		this.skyBlockMenu.setItem(20, itemBuilder.createItemWithLore(Material.PAINTING, 1, "§9Sammlungen", "§7➥ §cKommt noch nicht."));
		this.skyBlockMenu.setItem(22, itemBuilder.createItemWithLore(Material.BOOK, 1, "§4Quests", "§7➥ Sieh dir deine aktuellen Quests an."));
		this.skyBlockMenu.setItem(24, itemBuilder.BuildCustomSkullWithLore("http://textures.minecraft.net/texture/e4d49bae95c790c3b1ff5b2f01052a714d6185481d5b1c85930b3f99d2321674", "§2Insel-Verwalten", "§7➥ Verwalte deine Insel."));
		this.skyBlockMenu.setItem(31, itemBuilder.BuildCustomSkullWithLore("http://textures.minecraft.net/texture/dfeb39d71ef8e6a42646593393a5753ce26a1bee27a0ca8a32cb637b1ffae", "§2Insel-Upgrades", "§7➥ Verbessere deine Insel."));
		this.skyBlockMenu.setItem(40, itemBuilder.BuildCustomSkullWithOutLore("http://textures.minecraft.net/texture/11b3188fd44902f72602bd7c2141f5a70673a411adb3d81862c69e536166b", "§3Schnellreise"));

		if (p.hasPermission("skyblock.vip") || p.hasPermission("skyblock.vorteilpack")) {
			this.skyBlockMenu.setItem(30, itemBuilder.createItemWithLore(Material.CRAFTING_TABLE, 1, "§fWerkbank", "§7➥ Öffne eine Mobile Werkbank."));
			this.skyBlockMenu.setItem(32, itemBuilder.createItemWithLore(Material.ENDER_CHEST, 1, "§5Enderchest", "§7➥ Öffne deine Enderchest."));
		} else {
			this.skyBlockMenu.setItem(30, itemBuilder.createItemWithLore(Material.CRAFTING_TABLE, 1, "§8Werkbank", "§7➥ §cFür diese Funktion benötigst du VIP oder ein Vorteilspaket."));
			this.skyBlockMenu.setItem(32, itemBuilder.createItemWithLore(Material.ENDER_CHEST, 1, "§8Enderchest", "§7➥ §cFür diese Funktion benötigst du VIP oder ein Vorteilspaket."));
		}
		p.openInventory(this.skyBlockMenu);
	}

	public void loadSkyBlockMenu_IslandManage(Player p) {

		for (int i = 0; i <= 8; i++) {
			if (i != 3 && i != 4 && i != 5) {
				this.skyBlockMenu_islandManage.setItem(i, itemBuilder.createItemWithOutLore(Material.BLACK_STAINED_GLASS_PANE, 1, " "));
			}
		}
		this.skyBlockMenu_islandManage.setItem(3, itemBuilder.createItemWithLore(Material.RED_BED, 1, "§eSpawnpunkt", "§7➥ Setze den Spawnpunkt deiner Insel."));
		this.skyBlockMenu_islandManage.setItem(4, itemBuilder.createItemWithLore(Material.PAPER, 1, "§eCoop-Spielerliste", "§7➥ Verwalte die Coop-Spieler deiner Insel."));
		this.skyBlockMenu_islandManage.setItem(5, itemBuilder.createItemWithLore(Material.IRON_SWORD, 1, "§ePvP", "§7➥ Schalte PvP auf deiner Insel an oder aus."));

		p.openInventory(this.skyBlockMenu_islandManage);
	}

	public void loadSkyBlockMenu_IslandManage_CoopList(Player p) {

		this.skyBlockMenu_islandManage_CoopList.clear();
		
		for (int i = 0; i <= 8; i++) {
			if (i != 2 && i != 3 && i != 4 && i != 5 && i != 6) {
				this.skyBlockMenu_islandManage_CoopList.setItem(i, itemBuilder.createItemWithOutLore(Material.BLACK_STAINED_GLASS_PANE, 1, " "));
			}
		}
		
		List<String> coopMember = PlayerCache.getCoopPlayerCacheUUIDs(p.getUniqueId());

		ArrayList<String> coopList = new ArrayList<String>();
		coopList.addAll(coopMember);

		if (!p.hasPermission("skyblock.vip")) {
			
			if(coopList != null) {
				for (int i = 0; i < coopList.size(); i++) {
					UUID uuid = UUID.fromString(coopList.get(i));
					String skullOwner = "";
					try {
						skullOwner = Bukkit.getPlayer(uuid).getName();
					} catch (NullPointerException nullPointerException) {
						OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
						skullOwner = offlinePlayer.getName();
					}
					this.skyBlockMenu_islandManage_CoopList.addItem(itemBuilder.createSkullWithLore(1, skullOwner, skullOwner, "§7➥ §cKlicke zum entfernen von deiner Insel."));
				}	
			}
			
			if(coopList.size() == 0) {
				this.skyBlockMenu_islandManage_CoopList.setItem(2, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-1"));
				this.skyBlockMenu_islandManage_CoopList.setItem(3, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-2"));
				this.skyBlockMenu_islandManage_CoopList.setItem(4, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-3"));
				this.skyBlockMenu_islandManage_CoopList.setItem(5, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-4"));
			} else if(coopList.size() == 1) {
				this.skyBlockMenu_islandManage_CoopList.setItem(3, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-2"));
				this.skyBlockMenu_islandManage_CoopList.setItem(4, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-3"));
				this.skyBlockMenu_islandManage_CoopList.setItem(5, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-4"));
			} else if(coopList.size() == 2) {
				this.skyBlockMenu_islandManage_CoopList.setItem(4, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-3"));
				this.skyBlockMenu_islandManage_CoopList.setItem(5, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-4"));
			} else if(coopList.size() == 3) {
				this.skyBlockMenu_islandManage_CoopList.setItem(5, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-4"));
			}			
			
			this.skyBlockMenu_islandManage_CoopList.setItem(6, itemBuilder.createItemWithLore(Material.STONE_BUTTON, 1, "§8Slot-5", "§7➥ §cFür diesen Coop-Slot benötigst du VIP."));
			
		
		} else {
			
			this.skyBlockMenu_islandManage_CoopList.clear();
			
			for (int i = 0; i <= 8; i++) {
				if (i != 2 && i != 3 && i != 4 && i != 5 && i != 6) {
					this.skyBlockMenu_islandManage_CoopList.setItem(i, itemBuilder.createItemWithOutLore(Material.BLACK_STAINED_GLASS_PANE, 1, " "));
				}
			}
			
			if(coopList != null) {
			for (int i = 0; i < coopList.size(); i++) {
				for (int s = 2; s < 6; s++) {
					Player skullOwner = Bukkit.getPlayer(UUID.fromString(coopList.get(i)));
					this.skyBlockMenu_islandManage_CoopList.addItem(itemBuilder.createSkullWithLore(1, skullOwner.getName(), skullOwner.getName(), "§7➥ §cKlicke zum entfernen von deiner Insel."));
					}
				}
			}
			if(coopList.size() == 0) {
				this.skyBlockMenu_islandManage_CoopList.setItem(2, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-1"));
				this.skyBlockMenu_islandManage_CoopList.setItem(3, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-2"));
				this.skyBlockMenu_islandManage_CoopList.setItem(4, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-3"));
				this.skyBlockMenu_islandManage_CoopList.setItem(5, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-4"));
			} else if(coopList.size() == 1) {
				this.skyBlockMenu_islandManage_CoopList.setItem(3, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-2"));
				this.skyBlockMenu_islandManage_CoopList.setItem(4, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-3"));
				this.skyBlockMenu_islandManage_CoopList.setItem(5, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-4"));
			} else if(coopList.size() == 2) {
				this.skyBlockMenu_islandManage_CoopList.setItem(4, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-3"));
				this.skyBlockMenu_islandManage_CoopList.setItem(5, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-4"));
			} else if(coopList.size() == 3) {
				this.skyBlockMenu_islandManage_CoopList.setItem(5, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-4"));
			}
			
			if(coopList.size() != 4) {				
				this.skyBlockMenu_islandManage_CoopList.setItem(5, itemBuilder.createItemWithOutLore(Material.STONE_BUTTON, 1, "§7Slot-5"));
			}
		}
		p.openInventory(this.skyBlockMenu_islandManage_CoopList);
	}
	
	public void loadSkyBlockMenu_IslandManage_CoopRemove(Player p) {
		
		for(int i = 0; i <= 8; i++) {
			if(i != 3 && i != 5) {
				this.skyBlockMenu_islandManage_CoopRemove.setItem(i, itemBuilder.createItemWithOutLore(Material.BLACK_STAINED_GLASS_PANE, 1, " "));
			}
		}
		
		this.skyBlockMenu_islandManage_CoopRemove.setItem(3, itemBuilder.createItemWithLore(Material.LIME_WOOL, 1, "§aBestätigen", "§7➥ §cDer User wird von deiner Insel entfernt!"));
		this.skyBlockMenu_islandManage_CoopRemove.setItem(5, itemBuilder.createItemWithLore(Material.RED_WOOL, 1, "§4Abbrechen", "§7➥ §aDer User wird nicht von deiner Insel entfernt!"));
		
		p.openInventory(this.skyBlockMenu_islandManage_CoopRemove);
	}
	
	public void loadSkyBlockMenu_IslandUpgrades(Player p) {
		
		for(int i = 0; i <= 8; i++) {
			if(i != 3 && i != 4 && i != 5) {
				this.skyBlockMenu_IslandUpgrades.setItem(i, itemBuilder.createItemWithOutLore(Material.BLACK_STAINED_GLASS_PANE, 1, " "));
			}
		}
		
		this.skyBlockMenu_IslandUpgrades.setItem(3, itemBuilder.createSkullWithLore(1, "MHF_Question", "§4?", "§c§kKommt bald"));
		this.skyBlockMenu_IslandUpgrades.setItem(4, itemBuilder.createItemWithLore(Material.END_CRYSTAL, 1, "§5Insel-Border", "§7➥ Vergrößere deine Insel."));
		this.skyBlockMenu_IslandUpgrades.setItem(5, itemBuilder.createSkullWithLore(1, "MHF_Question", "§4?", "§c§kKommt bald"));
		
		p.openInventory(this.skyBlockMenu_IslandUpgrades);
	}

	public void loadSkyBlockMenu_IslandUpgrades_IslandBorder(Player p) {
		
		for(int i = 0; i <= 8; i++) {
			if(i != 2 && i != 3 && i != 4 && i != 5) {
				this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(i, itemBuilder.createItemWithOutLore(Material.BLACK_STAINED_GLASS_PANE, 1, " "));
			}
		}
		
		if(Bukkit.getWorld(p.getUniqueId().toString()).getWorldBorder().getSize() == 16*6D) {
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(2, itemBuilder.createItemWithLore(Material.LEATHER_HORSE_ARMOR, 1, "§cStufe-1", "§7Kostet: §62000 SkyCoins"));
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(3, itemBuilder.createItemWithLore(Material.IRON_HORSE_ARMOR, 1, "§cStufe-2", "§7Kostet: §65000 SkyCoins"));
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(4, itemBuilder.createItemWithLore(Material.GOLDEN_HORSE_ARMOR, 1, "§cStufe-3", "§7Kostet: §610000 SkyCoins"));
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(5, itemBuilder.createItemWithLore(Material.DIAMOND_HORSE_ARMOR, 1, "§cStufe-4", "§7Kostet: §620000 SkyCoins"));
		} else if (Bukkit.getWorld(p.getUniqueId().toString()).getWorldBorder().getSize() == 16*8D) {
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(2, itemBuilder.createGlowingItemWithLore(Material.LEATHER_HORSE_ARMOR, 1, "§aStufe-1", "§7Gekauft"));
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(3, itemBuilder.createItemWithLore(Material.IRON_HORSE_ARMOR, 1, "§cStufe-2", "§7Kostet: §65000 SkyCoins"));
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(4, itemBuilder.createItemWithLore(Material.GOLDEN_HORSE_ARMOR, 1, "§cStufe-3", "§7Kostet: §610000 SkyCoins"));
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(5, itemBuilder.createItemWithLore(Material.DIAMOND_HORSE_ARMOR, 1, "§cStufe-4", "§7Kostet: §620000 SkyCoins"));
		} else if (Bukkit.getWorld(p.getUniqueId().toString()).getWorldBorder().getSize() == 16*10D) {
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(2, itemBuilder.createGlowingItemWithLore(Material.LEATHER_HORSE_ARMOR, 1, "§aStufe-1", "§7Gekauft"));
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(3, itemBuilder.createGlowingItemWithLore(Material.IRON_HORSE_ARMOR, 1, "§aStufe-2", "§7Gekauft"));
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(4, itemBuilder.createItemWithLore(Material.GOLDEN_HORSE_ARMOR, 1, "§cStufe-3", "§7Kostet: §610000 SkyCoins"));
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(5, itemBuilder.createItemWithLore(Material.DIAMOND_HORSE_ARMOR, 1, "§cStufe-4", "§7Kostet: §620000 SkyCoins"));
		} else if (Bukkit.getWorld(p.getUniqueId().toString()).getWorldBorder().getSize() == 16*12D) {
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(2, itemBuilder.createGlowingItemWithLore(Material.LEATHER_HORSE_ARMOR, 1, "§aStufe-1", "§7Gekauft"));
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(3, itemBuilder.createGlowingItemWithLore(Material.IRON_HORSE_ARMOR, 1, "§aStufe-2", "§7Gekauft"));
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(4, itemBuilder.createGlowingItemWithLore(Material.GOLDEN_HORSE_ARMOR, 1, "§aStufe-3", "§7Gekauft"));
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(5, itemBuilder.createItemWithLore(Material.DIAMOND_HORSE_ARMOR, 1, "§cStufe-4", "§7Kostet: §620000 SkyCoins"));
		} else if (Bukkit.getWorld(p.getUniqueId().toString()).getWorldBorder().getSize() == 16*14D) {
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(2, itemBuilder.createGlowingItemWithLore(Material.LEATHER_HORSE_ARMOR, 1, "§aStufe-1", "§7Gekauft"));
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(3, itemBuilder.createGlowingItemWithLore(Material.IRON_HORSE_ARMOR, 1, "§aStufe-2", "§7Gekauft"));
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(4, itemBuilder.createGlowingItemWithLore(Material.GOLDEN_HORSE_ARMOR, 1, "§aStufe-3", "§7Gekauft"));
			this.skyBlockMenu_IslandUpgrades_IslandBorder.setItem(5, itemBuilder.createGlowingItemWithLore(Material.DIAMOND_HORSE_ARMOR, 1, "§aStufe-4", "§7Gekauft"));
		}
		
		p.openInventory(this.skyBlockMenu_IslandUpgrades_IslandBorder);
	}
	
	public void loadSkyBlockMenu_Quests(Player p) {
		
		for(int i = 0; i <= 8; i++) {
			if(i != 2 && i != 4 && i != 6) {
				this.skyBlockMenu_Quests.setItem(i, itemBuilder.createItemWithOutLore(Material.BLACK_STAINED_GLASS_PANE, 1, " "));
			}
		}
		
		this.skyBlockMenu_Quests.setItem(2, itemBuilder.createItemWithOutLore(Material.LEATHER_HELMET, 1, "§aEinfache Quests"));
		
		if(PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
			if(PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy10")) {
				this.skyBlockMenu_Quests.setItem(4, itemBuilder.createItemWithOutLore(Material.IRON_HELMET, 1, "§eMittlere Quests"));								
			} else {
				this.skyBlockMenu_Quests.setItem(4, itemBuilder.createSkullWithOutLore(1, "MHF_Question", "§c???"));
			}
		} else {
			this.skyBlockMenu_Quests.setItem(4, itemBuilder.createSkullWithOutLore(1, "MHF_Question", "§c???"));
		}
		
		if(PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
			if(PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("mid10")) {				
				this.skyBlockMenu_Quests.setItem(6, itemBuilder.createItemWithOutLore(Material.DIAMOND_HELMET, 1, "§cSchwere Quests"));
			} else {
				this.skyBlockMenu_Quests.setItem(6, itemBuilder.createSkullWithOutLore(1, "MHF_Question", "§c???"));
			}
		} else {
			this.skyBlockMenu_Quests.setItem(6, itemBuilder.createSkullWithOutLore(1, "MHF_Question", "§c???"));
		}
		
		p.openInventory(this.skyBlockMenu_Quests);
	}
	
	public void loadSkyBlockMenu_Quests_Easy(Player p) {
		for(int i = 0; i <= 26; i++) {
			if(i != 0 && i != 2 && i != 4 && i != 6 && i != 8 && i != 10 && i != 16 && i != 20 && i != 22 && i != 24) {
				this.skyBlockMenu_Quests_Easy.setItem(i, itemBuilder.createItemWithOutLore(Material.BLACK_STAINED_GLASS_PANE, 1, " "));
			}
		}
		
		if(PlayerCache.getPlayerQuestCache(p.getUniqueId()) != null) {
		if(PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy1")) {
			this.skyBlockMenu_Quests_Easy.setItem(0, itemBuilder.createGlowingItemWithLore(Material.CRAFTING_TABLE, 1, "§aBaue eine Werkbank", "§8● §aAbgeschlossen"));
		} else {
			this.skyBlockMenu_Quests_Easy.setItem(0, itemBuilder.createItemWith3Lore(Material.CRAFTING_TABLE, 1, "§cBaue eine Werkbank", "§7Erhalte:", "§8● §a1x Eichensetzling", "§8● §65 SkyCoins"));			
		}
		if(PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy2")) {
			this.skyBlockMenu_Quests_Easy.setItem(2, itemBuilder.createGlowingItemWithLore(Material.DIRT, 1, "§aSammel 32x Erde", "§8● §aAbgeschlossen"));
		} else {
			this.skyBlockMenu_Quests_Easy.setItem(2, itemBuilder.createItemWith3Lore(Material.DIRT, 1, "§cSammel 32x Erde", "§7Erhalte:", "§8● §a4x Podsol", "§8● §635 SkyCoins"));			
		}
		if(PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy3")) {
			this.skyBlockMenu_Quests_Easy.setItem(4, itemBuilder.createGlowingItemWithLore(Material.OAK_LOG, 1, "§aSammel 30x Eichenholz", "§8● §aAbgeschlossen"));
		} else {
			this.skyBlockMenu_Quests_Easy.setItem(4, itemBuilder.createItemWith3Lore(Material.OAK_LOG, 1, "§cSammel 30x Eichenholz", "§7Erhalte:", "§8● §a1x Steinaxt", "§8● §610 SkyCoins"));			
		}
		if(PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy4")) {
			this.skyBlockMenu_Quests_Easy.setItem(6, itemBuilder.createGlowingItemWithLore(Material.COBBLESTONE, 1, "§aSammel 128x Bruchstein", "§8● §aAbgeschlossen"));
		} else {
			this.skyBlockMenu_Quests_Easy.setItem(6, itemBuilder.createItemWith3Lore(Material.COBBLESTONE, 1, "§cSammel 128x Bruchstein", "§7Erhalte:", "§8● §a6x Eisenbarren", "§8● §620 SkyCoins"));			
		}
		if(PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy5")) {
			this.skyBlockMenu_Quests_Easy.setItem(8, itemBuilder.createGlowingItemWithLore(Material.FURNACE, 1, "§aBaue einen Ofen", "§8● §aAbgeschlossen"));
		} else {
			this.skyBlockMenu_Quests_Easy.setItem(8, itemBuilder.createItemWith3Lore(Material.FURNACE, 1, "§cBaue einen Ofen", "§7Erhalte:", "§8● §a1x 10 Kohle", "§8● §615 SkyCoins"));			
		}
		if(PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy6")) {
			this.skyBlockMenu_Quests_Easy.setItem(10, itemBuilder.createGlowingItemWithLore(Material.COAL, 1, "§aSammel 16x Kohle", "§8● §aAbgeschlossen"));
		} else {
			this.skyBlockMenu_Quests_Easy.setItem(10, itemBuilder.createItemWith3Lore(Material.COAL, 1, "§cSammel 16x Kohle", "§7Erhalte:", "§8● §a1x Lavaeimer", "§8● §625 SkyCoins"));			
		}
		if(PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy7")) {
			this.skyBlockMenu_Quests_Easy.setItem(16, itemBuilder.createGlowingItemWithLore(Material.IRON_INGOT, 1, "§aSammel 32x Eisen", "§8● §aAbgeschlossen"));
		} else {
			this.skyBlockMenu_Quests_Easy.setItem(16, itemBuilder.createItemWith3Lore(Material.IRON_INGOT, 1, "§cSammel 32x Eisen", "§7Erhalte:", "§8● §a1x Diamant", "§8● §630 SkyCoins"));			
		}
		if(PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy8")) {
			this.skyBlockMenu_Quests_Easy.setItem(20, itemBuilder.createGlowingItemWithLore(Material.MELON, 1, "§aSammel 256x Melonenscheiben", "§8● §aAbgeschlossen"));
		} else {
			this.skyBlockMenu_Quests_Easy.setItem(20, itemBuilder.createItemWith3Lore(Material.MELON, 1, "§cSammel 256x Melonenscheiben", "§7Erhalte:", "§8● §a5x Karotten", "§8● §6100 SkyCoins"));			
		}
		if(PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy10")) {
			this.skyBlockMenu_Quests_Easy.setItem(22, itemBuilder.createGlowingItemWithLore(Material.BEACON, 1, "§aSchließe alle einfachen Quests ab", "§8● §aAbgeschlossen"));
		} else {
			this.skyBlockMenu_Quests_Easy.setItem(22, itemBuilder.createItemWith3Lore(Material.BEACON, 1, "§cSchließe alle einfachen Quests ab", "§7Erhalte:", "§8● §a2x Diamant", "§8● §6200 SkyCoins"));			
		}
		if(PlayerCache.getPlayerQuestCache(p.getUniqueId()).contains("easy9")) {
			this.skyBlockMenu_Quests_Easy.setItem(24, itemBuilder.createGlowingItemWithLore(Material.APPLE, 1, "§aSammel 48x Äpfel", "§8● §aAbgeschlossen"));
		} else {
			this.skyBlockMenu_Quests_Easy.setItem(24, itemBuilder.createItemWith3Lore(Material.APPLE, 1, "§cSammel 48x Äpfel", "§7Erhalte:", "§8● §a1x Fichtensetzling", "§8● §650 SkyCoins"));			
			}
	} else {
		this.skyBlockMenu_Quests_Easy.setItem(0, itemBuilder.createItemWith3Lore(Material.CRAFTING_TABLE, 1, "§cBaue eine Werkbank", "§7Erhalte:", "§8● §a1x Eichensetzling", "§8● §65 SkyCoins"));			
		this.skyBlockMenu_Quests_Easy.setItem(2, itemBuilder.createItemWith3Lore(Material.DIRT, 1, "§cSammel 32x Erde", "§7Erhalte:", "§8● §a4x Podsol", "§8● §635 SkyCoins"));			
		this.skyBlockMenu_Quests_Easy.setItem(4, itemBuilder.createItemWith3Lore(Material.OAK_LOG, 1, "§cSammel 30x Eichenholz", "§7Erhalte:", "§8● §a1x Steinaxt", "§8● §610 SkyCoins"));			
		this.skyBlockMenu_Quests_Easy.setItem(6, itemBuilder.createItemWith3Lore(Material.COBBLESTONE, 1, "§cSammel 128x Bruchstein", "§7Erhalte:", "§8● §a6x Eisenbarren", "§8● §620 SkyCoins"));			
		this.skyBlockMenu_Quests_Easy.setItem(8, itemBuilder.createItemWith3Lore(Material.FURNACE, 1, "§cBaue einen Ofen", "§7Erhalte:", "§8● §a1x 10 Kohle", "§8● §615 SkyCoins"));			
		this.skyBlockMenu_Quests_Easy.setItem(10, itemBuilder.createItemWith3Lore(Material.COAL, 1, "§cSammel 16x Kohle", "§7Erhalte:", "§8● §a1x Lavaeimer", "§8● §625 SkyCoins"));			
		this.skyBlockMenu_Quests_Easy.setItem(16, itemBuilder.createItemWith3Lore(Material.IRON_INGOT, 1, "§cSammel 32x Eisen", "§7Erhalte:", "§8● §a1x Diamant", "§8● §630 SkyCoins"));			
		this.skyBlockMenu_Quests_Easy.setItem(20, itemBuilder.createItemWith3Lore(Material.MELON, 1, "§cSammel 256x Melonenscheiben", "§7Erhalte:", "§8● §a5x Karotten", "§8● §6100 SkyCoins"));			
		this.skyBlockMenu_Quests_Easy.setItem(22, itemBuilder.createItemWith3Lore(Material.BEACON, 1, "§cSchließe alle einfachen Quests ab", "§7Erhalte:", "§8● §a1x Diamant", "§8● §6200 SkyCoins"));			
		this.skyBlockMenu_Quests_Easy.setItem(24, itemBuilder.createItemWith3Lore(Material.APPLE, 1, "§cSammel 48x Äpfel", "§7Erhalte:", "§8● §a1x Fichtensetzling", "§8● §650 SkyCoins"));		
	}
		p.openInventory(this.skyBlockMenu_Quests_Easy);
}
	
	public void loadIslandNpcMenu(Player p) {

		for (int i = 0; i <= 8; i++) {
			if (i != 4) {
				this.islandNpcMenu.setItem(i, itemBuilder.createItemWithOutLore(Material.BLACK_STAINED_GLASS_PANE, 1, " "));
			}
		}
		this.islandNpcMenu.setItem(4, itemBuilder.BuildCustomSkullWithLore("http://textures.minecraft.net/texture/4528ed45802400f465b5c4e3a6b7a9f2b6a5b3d478b6fd84925cc5d988391c7d", "§eInsel-Erstellen", "§7➥ Erstelle deine eigene Insel für §61000 SkyCoins§7."));
		p.openInventory(islandNpcMenu);
	}
	
	public void loadFastTravelMenu(Player p) {
		
		this.skyBlockMenu_FastTravel.setItem(0, itemBuilder.createItemWithOutLore(Material.BLACK_STAINED_GLASS_PANE, 1, " "));
		this.skyBlockMenu_FastTravel.setItem(1, itemBuilder.BuildCustomSkullWithLore("http://textures.minecraft.net/texture/cf40942f364f6cbceffcf1151796410286a48b1aeba77243e218026c09cd1", "§aSpawn", "§7➥ Teleportiere dich zum Spawn."));
		this.skyBlockMenu_FastTravel.setItem(2, itemBuilder.createItemWithOutLore(Material.BLACK_STAINED_GLASS_PANE, 1, " "));
		this.skyBlockMenu_FastTravel.setItem(3, itemBuilder.BuildCustomSkullWithLore("http://textures.minecraft.net/texture/c6cac59b2aae489aa0687b5d802b2555eb14a40bd62b21eb116fa569cdb756", "§5End", "§7➥ Teleportiere dich zum End."));
		this.skyBlockMenu_FastTravel.setItem(4, itemBuilder.BuildCustomSkullWithLore("http://textures.minecraft.net/texture/d83571ff589f1a59bb02b80800fc736116e27c3dcf9efebede8cf1fdde", "§4Nether", "§7➥ Teleportiere dich zum Nether."));
		this.skyBlockMenu_FastTravel.setItem(5, itemBuilder.BuildCustomSkullWithLore("http://textures.minecraft.net/texture/23afc6b82aac3036973380e8133170d06a7fbd28cd5c831d45376abd2af456a3", "§2Wald", "§7➥ Teleportiere dich zum Wald."));
		this.skyBlockMenu_FastTravel.setItem(6, itemBuilder.BuildCustomSkullWithLore("http://textures.minecraft.net/texture/73bc965d579c3c6039f0a17eb7c2e6faf538c7a5de8e60ec7a719360d0a857a9", "§6Mine", "§7➥ Teleportiere dich zur Mine."));
		this.skyBlockMenu_FastTravel.setItem(7, itemBuilder.BuildCustomSkullWithLore("http://textures.minecraft.net/texture/ba0c78de40fd5b588df142a09313c19580b332a69b8284f647007044b44d887a", "§9Quelle", "§7➥ Teleportiere dich zur Quelle."));
		this.skyBlockMenu_FastTravel.setItem(8, itemBuilder.createItemWithOutLore(Material.BLACK_STAINED_GLASS_PANE, 1, " "));
		
		p.openInventory(this.skyBlockMenu_FastTravel);
	}
}