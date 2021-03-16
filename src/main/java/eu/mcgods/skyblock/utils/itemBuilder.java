package eu.mcgods.skyblock.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Base64;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class itemBuilder {

	public static ItemStack createItemWithOutLore(Material mat, Integer value, String name) {
		ItemStack item = new ItemStack(mat, value.intValue());
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName(name);
		itemmeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		item.setItemMeta(itemmeta);

		return item;
	}

	public static ItemStack createItemWithLore(Material mat, Integer value, String name, String lore) {
		ItemStack item = new ItemStack(mat, value.intValue());
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		ArrayList<String> itemlore = new ArrayList<String>();
		itemlore.add(lore);
		itemmeta.setLore(itemlore);
		itemmeta.setDisplayName(name);
		item.setItemMeta(itemmeta);

		return item;
	}
	
	public static ItemStack createGlowingItemWithLore(Material mat, Integer value, String name, String lore) {
		ItemStack item = new ItemStack(mat, value.intValue());
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS });
		itemmeta.addEnchant(Enchantment.DURABILITY, 1, false);
		ArrayList<String> itemlore = new ArrayList<String>();
		itemlore.add(lore);
		itemmeta.setLore(itemlore);
		itemmeta.setDisplayName(name);
		item.setItemMeta(itemmeta);

		return item;
	}

	public static ItemStack createPotionWithOutLore(Integer value, String name, Color color) {
		ItemStack item = new ItemStack(Material.POTION, value.intValue());
		PotionMeta itemmeta = (PotionMeta) item.getItemMeta();
		itemmeta.setColor(color);
		itemmeta.addEnchant(Enchantment.DURABILITY, 0, true);
		itemmeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		itemmeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
		itemmeta.setDisplayName(name);
		item.setItemMeta(itemmeta);

		return item;
	}

	public static ItemStack createPotionWithLore(Integer value, String name, Color color, String lore) {
		ItemStack item = new ItemStack(Material.POTION, value.intValue());
		PotionMeta itemmeta = (PotionMeta) item.getItemMeta();
		itemmeta.setColor(color);
		ArrayList<String> itemlore = new ArrayList<String>();
		itemlore.add(lore);
		itemmeta.setLore(itemlore);
		itemmeta.addEnchant(Enchantment.DURABILITY, 0, true);
		itemmeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		itemmeta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });
		itemmeta.setDisplayName(name);
		item.setItemMeta(itemmeta);

		return item;
	}

	@SuppressWarnings("deprecation")
	public static ItemStack createSkullWithOutLore(Integer value, String owner, String name) {
		
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, value, (short) 3);
		SkullMeta itemmeta = (SkullMeta) item.getItemMeta();
		itemmeta.setOwner(owner);
		itemmeta.setDisplayName(name);
		item.setItemMeta(itemmeta);
		
		return item;
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack createSkullWithLore(Integer value, String owner, String name, String lore) {
		
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, value, (short) 3);
		SkullMeta itemmeta = (SkullMeta) item.getItemMeta();
		ArrayList<String> itemlore = new ArrayList<String>();
		itemlore.add(lore);
		itemmeta.setLore(itemlore);
		itemmeta.setOwner(owner);
		itemmeta.setDisplayName(name);
		item.setItemMeta(itemmeta);
		
		return item;
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack BuildCustomSkullWithOutLore(String url, String displayname) {
		
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		
		byte[] data = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
		
		profile.getProperties().put("textures", new Property("textures", new String(data)));
		
		try {
			
			Field profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);

			meta.setDisplayName(displayname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		item.setItemMeta(meta);
		return item;
	}
	
	@SuppressWarnings("deprecation")
	public static ItemStack BuildCustomSkullWithLore(String url, String displayname, String lore) {
		
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);
		
		byte[] data = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
		
		profile.getProperties().put("textures", new Property("textures", new String(data)));
		
		try {
			
			Field profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
			
			meta.setLore(Lore);
			meta.setDisplayName(displayname);
		} catch (Exception e) {
			e.printStackTrace();
		}
		item.setItemMeta(meta);
		return item;
	}
}