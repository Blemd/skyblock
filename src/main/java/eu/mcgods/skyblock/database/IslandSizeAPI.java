package eu.mcgods.skyblock.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import eu.mcgods.skyblock.main.SkyBlock;

public class IslandSizeAPI {
	
	private SkyBlock m = SkyBlock.getInstance();
	private MySQL mySQL = m.mySQL;
	
	public void setIslandSize(UUID uuid, int size) {
		if(this.mySQL.checkIfUserHasAlReadyIslandSizeData(uuid)) {
			try {
				PreparedStatement pst = this.mySQL.getConnection().prepareStatement("UPDATE " + this.mySQL.getTable() + "_islandsize SET size = ? WHERE UUID = ?");
				pst.setInt(1, size);
				pst.setString(2, uuid.toString());
				pst.executeUpdate();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		} else {
			try {
				PreparedStatement pst = this.mySQL.getConnection().prepareStatement("INSERT INTO " + this.mySQL.getTable() + "_islandsize (uuid, size) VALUES (?,?)");
				pst.setString(1, uuid.toString());
				pst.setInt(2, size);
				pst.executeUpdate();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}
	
	public Integer getIslandSize(UUID uuid) {
		try {
			PreparedStatement pst = this.mySQL.getConnection().prepareStatement("SELECT size FROM " + this.mySQL.getTable() + "_islandsize WHERE UUID = ?");
			pst.setString(1, uuid.toString());
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return rs.getInt("size");
			}
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return 0;
	}
}