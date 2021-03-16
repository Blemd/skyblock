package eu.mcgods.skyblock.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import eu.mcgods.skyblock.main.SkyBlock;

public class CoopAPI {

	private SkyBlock m = SkyBlock.getInstance();
	private MySQL mysql = m.mySQL;

	public void addCoopPartner(UUID worldOwner, String islandName, UUID coopPartner) {
		if (this.mysql.checkIfUserHasAlReadyCoopData(worldOwner)) {
			try {
				String[] oldCoopData = this.getCoopPartners(worldOwner, islandName).split("\\;");

				ArrayList<String> oldArray = new ArrayList<String>();
				oldArray.addAll(Arrays.asList(oldCoopData));

				if(!(oldArray.size() >= 4)) {
					
				String newCoopData = "";
				for (int i = 0; i < oldArray.size(); i++) {
					newCoopData += oldArray.get(i) + ";";
				}
				newCoopData = newCoopData + coopPartner.toString() + ";";
				
					PreparedStatement pst = this.mysql.getConnection().prepareStatement("UPDATE " + this.mysql.getTable() + "_coop SET memberlist = ? WHERE world = ?");
					pst.setString(1, newCoopData);
					pst.setString(2, islandName.toString());
					pst.executeUpdate();
					pst.close();
				}
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		} else {
			try {
				String coopData = String.valueOf(coopPartner + ";");
				PreparedStatement pst = this.mysql.getConnection().prepareStatement("INSERT INTO " + this.mysql.getTable() + "_coop(world, memberlist) VALUES ('" + islandName.toString() + "','" + coopData + "');");
				pst.executeUpdate();
				pst.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}

	public String getCoopPartners(UUID ownerUUID, String islandName) {
		if (this.mysql.checkIfUserHasAlReadyCoopData(ownerUUID)) {
			try {
				PreparedStatement pst = this.mysql.getConnection().prepareStatement("SELECT memberlist FROM " + this.mysql.getTable() + "_coop WHERE world = ?");
				pst.setString(1, islandName);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					return rs.getString("memberlist");
				}
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
		return null;
	}

	public boolean checkCoopPartner(UUID ownerUUID, String islandName, UUID checkUser) {
		if (this.mysql.checkIfUserHasAlReadyCoopData(ownerUUID)) {
			try {
				PreparedStatement pst = this.mysql.getConnection().prepareStatement("SELECT memberlist FROM " + this.mysql.getTable() + "_coop WHERE world = ?");
				pst.setString(1, islandName);
				ResultSet rs = pst.executeQuery();
				
				if(rs.next()) {
					String[] memberlist = rs.getString("memberlist").split("\\;");
					ArrayList<String> memberArray = new ArrayList<String>();
					memberArray.addAll(Arrays.asList(memberlist));
					
					for(int i = 0; i < memberArray.size(); i++) {
						if(memberlist[i].equalsIgnoreCase(checkUser.toString())) {
							return true;
						}
					}					
				}
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
		return false;
	}
	
	public void setCoopPartners(UUID uuid, List<String> playerList) {
		if(this.mysql.checkIfUserHasAlReadyCoopData(uuid)) {
			try {
				
				String list = "";
				
				ArrayList<String> playerArray = new ArrayList<String>();
				playerArray.addAll(playerList);
				
				for(int i = 0; i < playerArray.size(); i++) {
					list += playerArray.get(i) + ";";
				}
				
				PreparedStatement pst = this.mysql.getConnection().prepareStatement("UPDATE " + this.mysql.getTable() + "_coop SET memberlist = ? WHERE world = ?");
				pst.setString(1, list);
				pst.setString(2, uuid.toString());
				pst.executeUpdate();
				pst.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		} else {
			try {
				
				String list = "";
				
				ArrayList<String> playerArray = new ArrayList<String>();
				playerArray.addAll(playerList);
				
				for(int i = 0; i < playerArray.size(); i++) {
					list += playerArray.get(i) + ";";
				}
				
				PreparedStatement pst = this.mysql.getConnection().prepareStatement("INSERT INTO " + this.mysql.getTable() + "_coop(world, memberlist) VALUES ('" + uuid.toString() + "', '" + list + "')");
				pst.executeUpdate();
				pst.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}
}