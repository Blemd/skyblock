package eu.mcgods.skyblock.utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import eu.mcgods.skyblock.database.SkyCoinsAPI;

public class InventoryBuilder {

	private SkyCoinsAPI coinsAPI = new SkyCoinsAPI();
	
	private Inventory skyBlockMenu = Bukkit.createInventory(null, 6 * 9, "§a§lSkyblock-Menü");
	
	public void loadSkyBlockMenu(Player p) {
		
		UUID uuid = p.getUniqueId();
		
		for (int i = 0; i <= 53; i++) {
			if(i != 4 && i != 10 && i != 16 && i != 20 && i != 22 && i != 24 && i != 30 && i != 31 && i != 32 && i != 40) {
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
		
		if(p.hasPermission("skyblock.vip") || p.hasPermission("skyblock.vorteilpack")) {
			this.skyBlockMenu.setItem(30, itemBuilder.createItemWithLore(Material.CRAFTING_TABLE, 1, "§fWerkbank", "§7➥ Öffne eine Mobile Werkbank."));
			this.skyBlockMenu.setItem(32, itemBuilder.createItemWithLore(Material.ENDER_CHEST, 1, "§5Enderchest", "§7➥ Öffne deine Enderchest."));			
		} else {
			this.skyBlockMenu.setItem(30, itemBuilder.createItemWithLore(Material.CRAFTING_TABLE, 1, "§8Werkbank", "§7➥ §cFür diese Funktion benötigst du VIP oder ein Vorteilspaket."));
			this.skyBlockMenu.setItem(32, itemBuilder.createItemWithLore(Material.ENDER_CHEST, 1, "§8Enderchest", "§7➥ §cFür diese Funktion benötigst du VIP oder ein Vorteilspaket."));	
		}
		p.openInventory(this.skyBlockMenu);
	}
}