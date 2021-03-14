package eu.mcgods.skyblock.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import eu.mcgods.skyblock.main.SkyBlock;

public class SkyCoinsAPI {

	private SkyBlock m = SkyBlock.getInstance();
	private MySQL mysql = m.mySQL;

	public void setSkyCoins(UUID uuid, int amount) {
		if (this.mysql.checkIfUserHasAlReadySkyCoinsData(uuid)) {
			try {
				PreparedStatement pst = this.mysql.getConnection().prepareStatement("UPDATE " + this.mysql.getTable() + "_skycoins SET amount = ? WHERE UUID = ?");
				pst.setInt(1, amount);
				pst.setString(2, uuid.toString());
				pst.executeUpdate();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		} else {
			try {
				PreparedStatement pst = this.mysql.getConnection().prepareStatement("INSERT INTO " + this.mysql.getTable() + "_skycoins (uuid,amount) VALUES (?,?)");
				pst.setString(1, uuid.toString());
				pst.setInt(2, amount);
				pst.executeUpdate();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}

	public Integer getSkyCoins(UUID uuid) {
		try {
			PreparedStatement pst = this.mysql.getConnection().prepareStatement("SELECT amount FROM " + this.mysql.getTable() + "_skycoins WHERE UUID = ?");
			pst.setString(1, uuid.toString());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt("amount");
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return 0;
	}

	public void addSkyCoins(UUID uuid, int amount) {
		setSkyCoins(uuid, getSkyCoins(uuid) + amount);
	}

	public void removeSkyCoins(UUID uuid, int amount) {
		setSkyCoins(uuid, getSkyCoins(uuid) - amount);
	}
}