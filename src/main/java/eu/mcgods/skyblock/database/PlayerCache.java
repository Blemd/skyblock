package eu.mcgods.skyblock.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import eu.mcgods.skyblock.utils.InventorySave;

public class PlayerCache {
	
	private InventoryAPI invAPI = new InventoryAPI();
	private static SkyCoinsAPI coinsAPI = new SkyCoinsAPI();
	private static CoopAPI coopAPI = new CoopAPI();
	
	//private static Map<String, Integer> islandbank = new HashMap<String, Integer>();
	private static Map<UUID, Integer> skyCoins = new HashMap<UUID, Integer>();
	private static Map<UUID, ItemStack[]> itemContents = new HashMap<UUID, ItemStack[]>();
	private static Map<UUID, ItemStack[]> armorContents = new HashMap<UUID, ItemStack[]>();
	//private static Map<UUID, List<String>> quests = new HashMap<UUID, List<String>>();
	
	private static Map<UUID, List<String>> coop = new HashMap<UUID, List<String>>();
	
	public PlayerCache(Player p) {
		
		UUID uuid = p.getUniqueId();
		
		Integer playerSkyCoins = coinsAPI.getSkyCoins(p.getUniqueId());
		String[] loadInventory = invAPI.getInv(p.getUniqueId()).split("\\;");
		ItemStack[] playerItems = InventorySave.itemStackArrayFromBase64(loadInventory[0]);
		ItemStack[] playerArmor = InventorySave.itemStackArrayFromBase64(loadInventory[1]);
		//playerBank
		//playerQuests
		
		try {
		String[] coopMember = coopAPI.getCoopPartners(p.getUniqueId(), p.getUniqueId().toString()).split("\\;");
		
		List<String> coopList = new ArrayList<String>();
		coopList.addAll(Arrays.asList(coopMember));
		
		coop.put(uuid, coopList);
		} catch (NullPointerException nullPointerException) {
		}
		
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
		
		if(coop.containsKey(uuid)) {
			coopAPI.setCoopPartners(uuid, getCoopPlayerCacheNames(uuid));
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
	
	public static void addCoopPlayerCache(UUID uuid, String targetUUID) {
		if(!(coop.size() >= 4)) {
			coop.get(uuid).add(targetUUID);			
		}
	}
	
	public static void removeCoopPlayerCache(UUID uuid, String targetUUID) {
		coop.get(uuid).remove(targetUUID);
	}
	
	public static Integer getCoopPlayerCacheSize(UUID uuid) {
		return coop.get(uuid).size();
	}
	
	public static List<String> getCoopPlayerCacheNames(UUID uuid) {
		
		List<Player> onlinePlayerList = new ArrayList<Player>();
		List<OfflinePlayer> offlinePlayerList = new ArrayList<OfflinePlayer>();
		
		List<String> userNames = new ArrayList<String>();
		
		for(int i = 0; i < coop.get(uuid).size(); i++) {
			UUID playerUUID = UUID.fromString(coop.get(uuid).get(i));
			
			Player player = Bukkit.getPlayer(playerUUID);
			if(player != null) {
				onlinePlayerList.add(player);
			} else {
				OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playerUUID);
				offlinePlayerList.add(offlinePlayer);
			}
		}
		if(onlinePlayerList != null) {
			for(int i = 0; i < onlinePlayerList.size(); i++) {
				userNames.add(onlinePlayerList.get(i).getName());
			}
		}
		if(offlinePlayerList != null) {
			for(int i = 0; i < offlinePlayerList.size(); i++) {
				userNames.add(offlinePlayerList.get(i).getName());
			}
		}
		return userNames;
	}
}