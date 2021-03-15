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
import eu.mcgods.skyblock.database.SkyCoinsAPI;

public class InventoryBuilder {

	private SkyCoinsAPI coinsAPI = new SkyCoinsAPI();

	private Inventory skyBlockMenu = Bukkit.createInventory(null, 6 * 9, "§a§lSkyblock-Menü");
	private Inventory skyBlockMenu_islandManage = Bukkit.createInventory(null, 9, "§2Insel-Verwalten");
	private Inventory skyBlockMenu_islandManage_CoopList = Bukkit.createInventory(null, 9, "§2Insel-Verwalten");
	private Inventory islandNpcMenu = Bukkit.createInventory(null, 9, "§aInsel Meister");

	public void loadSkyBlockMenu(Player p) {

		UUID uuid = p.getUniqueId();

		for (int i = 0; i <= 53; i++) {
			if (i != 4 && i != 10 && i != 16 && i != 20 && i != 22 && i != 24 && i != 30 && i != 31 && i != 32
					&& i != 40) {
				this.skyBlockMenu.setItem(i, itemBuilder.createItemWithOutLore(Material.BLACK_STAINED_GLASS_PANE, 1, " "));
			}
		}
		this.skyBlockMenu.setItem(4, itemBuilder.createSkullWithLore(1, p.getName(), "§e" + p.getName(), "§6SkyCoins: " + coinsAPI.getSkyCoins(uuid)));
		this.skyBlockMenu.setItem(10, itemBuilder.createItemWithLore(Material.NETHER_STAR, 1, "§eVote", "§7➥ Vote für das Netzwerk und erhalte eine Belohnung."));
		this.skyBlockMenu.setItem(16, itemBuilder.createItemWithLore(Material.REPEATER, 1, "§eSpieler-Einstellungen", "§7➥ §cKommt demnächst..."));
		this.skyBlockMenu.setItem(20, itemBuilder.createItemWithLore(Material.PAINTING, 1, "§9Sammlungen", "§7➥ Sieh dir deine Sammlungen an."));
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
		
		List<String> coopMember = PlayerCache.getCoopPlayerCacheNames(p.getUniqueId());

		ArrayList<String> coopList = new ArrayList<String>();
		coopList.addAll(coopMember);

		if (!p.hasPermission("vippermission")) {
			
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
			for (int i = 0; i < coopList.size(); i++) {
				for (int s = 2; s < 6; s++) {
					Player skullOwner = Bukkit.getPlayer(UUID.fromString(coopList.get(i)));
					this.skyBlockMenu_islandManage_CoopList.addItem(itemBuilder.createSkullWithLore(1, skullOwner.getName(), skullOwner.getName(), "§7➥ §cKlicke zum entfernen von deiner Insel."));
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

	public void loadIslandNpcMenu(Player p) {

		for (int i = 0; i <= 8; i++) {
			if (i != 4) {
				this.islandNpcMenu.setItem(i,
						itemBuilder.createItemWithOutLore(Material.BLACK_STAINED_GLASS_PANE, 1, " "));
			}
		}
		this.islandNpcMenu.setItem(4, itemBuilder.BuildCustomSkullWithLore("", "§eInsel-Erstellen",
				"§7➥ Erstelle deine eigene Insel für §61000 SkyCoins§7."));
		p.openInventory(islandNpcMenu);
	}
}