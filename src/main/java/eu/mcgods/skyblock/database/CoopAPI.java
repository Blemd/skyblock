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
					String[] memberlist = rs.getString("memberlist").split("\\,");
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
				
				String list = new String();
				
				if(playerList != null) {
					ArrayList<String> playerArray = new ArrayList<String>();
					playerArray.addAll(playerList);					
					
					for(int i = 0; i < playerArray.size(); i++) {
						list += playerArray.get(i) + ";";
					}
					
					PreparedStatement pst = this.mysql.getConnection().prepareStatement("UPDATE " + this.mysql.getTable() + "_coop SET memberlist = ? WHERE world = ?");
					pst.setString(1, list);
					pst.setString(2, uuid.toString());
					pst.executeUpdate();
				} else {
					PreparedStatement pst = this.mysql.getConnection().prepareStatement("UPDATE " + this.mysql.getTable() + "_coop SET memberlist = ? WHERE world = ?");
					pst.setString(1, null);
					pst.setString(2, uuid.toString());
					pst.executeUpdate();
				}
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		} else {
			try {
				
				String list = new String();
				
				if(playerList != null) {
					ArrayList<String> playerArray = new ArrayList<String>();
					playerArray.addAll(playerList);					
					
					for(int i = 0; i < playerArray.size(); i++) {
						list += playerArray.get(i) + ";";
					}
					
					PreparedStatement pst = this.mysql.getConnection().prepareStatement("INSERT INTO " + this.mysql.getTable() + "_coop(world, memberlist) VALUES ('" + uuid.toString() + "','" + list + "')");
					pst.executeUpdate();
				}
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}
	
	
	public ArrayList<UUID> getAllCoopData() {
		try {
			PreparedStatement pst = mysql.getConnection().prepareStatement("SELECT * FROM " + mysql.getTable() + "_coop");
			ResultSet rs = pst.executeQuery();
			
			ArrayList<UUID> uuids = new ArrayList<UUID>();
			while(rs.next()) {
				uuids.add(UUID.fromString(rs.getString("world")));
			}
			return uuids;
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
		return null;
	}
}