package eu.mcgods.skyblock.database;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import eu.mcgods.skyblock.utils.InventorySave;

public class PlayerCache {
	
	private InventoryAPI invAPI = new InventoryAPI();
	private static SkyCoinsAPI coinsAPI = new SkyCoinsAPI();
	
	//private static Map<String, Integer> islandbank = new HashMap<String, Integer>();
	private static Map<UUID, Integer> skyCoins = new HashMap<UUID, Integer>();
	private static Map<UUID, ItemStack[]> itemContents = new HashMap<UUID, ItemStack[]>();
	private static Map<UUID, ItemStack[]> armorContents = new HashMap<UUID, ItemStack[]>();
	//private static Map<UUID, List<String>> quests = new HashMap<UUID, List<String>>();
	//private static Map<String, List<UUID>> coop = new HashMap<String, List<UUID>>();
	
	public PlayerCache(Player p) {
		
		UUID uuid = p.getUniqueId();
		
		Integer playerSkyCoins = coinsAPI.getSkyCoins(p.getUniqueId());
		String[] loadInventory = invAPI.getInv(p.getUniqueId()).split("\\;");
		ItemStack[] playerItems = InventorySave.itemStackArrayFromBase64(loadInventory[0]);
		ItemStack[] playerArmor = InventorySave.itemStackArrayFromBase64(loadInventory[1]);
		//playerBank
		//playerQuests
		//coop
		
		skyCoins.put(uuid, playerSkyCoins);
		itemContents.put(uuid, playerItems);
		armorContents.put(uuid, playerArmor);
		
		p.getInventory().setContents(playerItems);
		p.getInventory().setArmorContents(playerArmor);;
	}
	
	public static void deleteUserCacheData(UUID uuid) {
		if(skyCoins.containsKey(uuid)) {
			coinsAPI.setSkyCoins(uuid, getSkyCoinsCache(uuid));
		}
		
		skyCoins.remove(uuid);
		itemContents.remove(uuid);
		armorContents.remove(uuid);
	}
	
	public static Integer getSkyCoinsCache(UUID uuid) {
		return skyCoins.get(uuid);
	}

	public static void setSkyCoinsCache(UUID uuid, Integer amount) {
		skyCoins.put(uuid, amount);
	}

	public static void addSkyCoinsCache(UUID uuid, Integer amount) {
		skyCoins.put(uuid, getSkyCoinsCache(uuid) + amount);
	}

	public static void removeSkyCoinsCache(UUID uuid, Integer amount) {
		skyCoins.put(uuid, getSkyCoinsCache(uuid) - amount);
	}

	// Setter & Getter for Inventory

	public static Map<UUID, ItemStack[]> getItemContentsMap() {
		return itemContents;
	}
	
	public static ItemStack[] getItemContents(UUID uuid) {
		return itemContents.get(uuid);
	}

	public static void setItemContents(UUID uuid, ItemStack[] itemStack) {
		itemContents.put(uuid, itemStack);
	}
	
	public static Map<UUID, ItemStack[]> getArmorContentsMap() {
		return armorContents;
	}

	public static ItemStack[] getArmorContents(UUID uuid) {
		return armorContents.get(uuid);
	}

	public static void setArmorContents(UUID uuid, ItemStack[] itemStack) {
		armorContents.put(uuid, itemStack);
	}
}