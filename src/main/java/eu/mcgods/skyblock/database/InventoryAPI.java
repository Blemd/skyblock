package eu.mcgods.skyblock.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import eu.mcgods.skyblock.main.SkyBlock;
import eu.mcgods.skyblock.utils.InventorySave;

public class InventoryAPI {

	private SkyBlock m = SkyBlock.getInstance();
	private MySQL mysql = m.mySQL;

	public void setInv(UUID uuid) {
		if (this.mysql.checkIfUserHasAlReadyInventoryData(uuid)) {
			try {
				Player p = Bukkit.getPlayer(uuid);
				String saveData = String.valueOf(InventorySave.toBase64((Inventory) p.getInventory())) + ";" + InventorySave.itemStackArrayToBase64(p.getInventory().getArmorContents());
				PreparedStatement pst = this.mysql.getConnection().prepareStatement("UPDATE " + this.mysql.getTable() + "_inventar SET inventar = ? WHERE UUID = ?");
				pst.setString(1, saveData);
				pst.setString(2, uuid.toString());
				pst.executeUpdate();
				pst.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		} else {
			try {
				Player p = Bukkit.getPlayer(uuid);
				String saveData = String.valueOf(InventorySave.toBase64((Inventory) p.getInventory())) + ";" + InventorySave.itemStackArrayToBase64(p.getInventory().getArmorContents());
				PreparedStatement pst = this.mysql.getConnection().prepareStatement("INSERT INTO " + this.mysql.getTable() + "_inventar(UUID, inventar) VALUES ('" + uuid.toString() + "', '" + saveData + "');");
				pst.executeUpdate();
				pst.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}

	public void setInvFromUserCache(UUID uuid, ItemStack[] items, ItemStack[] armor) {
		if (this.mysql.checkIfUserHasAlReadyInventoryData(uuid)) {
			try {
				String saveData = String.valueOf(InventorySave.itemStackArrayToBase64(items) + ";" + InventorySave.itemStackArrayToBase64(armor));
				PreparedStatement pst = this.mysql.getConnection().prepareStatement("UPDATE " + this.mysql.getTable() + "_inventar SET inventar = ? WHERE UUID = ?");
				pst.setString(1, saveData);
				pst.setString(2, uuid.toString());
				pst.executeUpdate();
				pst.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		} else {
			try {
				String saveData = String.valueOf(InventorySave.itemStackArrayToBase64(items) + ";" + InventorySave.itemStackArrayToBase64(armor));
				PreparedStatement pst = this.mysql.getConnection().prepareStatement("INSERT INTO " + this.mysql.getTable() + "_inventar SET inventar = ? WHERE UUID = ?");
				pst.setString(1, saveData);
				pst.setString(2, uuid.toString());
				pst.executeUpdate();
				pst.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}

	public String getInv(UUID uuid) {
		try {
			PreparedStatement pst = this.mysql.getConnection().prepareStatement("SELECT inventar FROM " + this.mysql.getTable() + "_inventar WHERE UUID = ?");
			pst.setString(1, uuid.toString());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getString("inventar");
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return null;
	}
}