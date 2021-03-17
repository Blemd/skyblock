package eu.mcgods.skyblock.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import eu.mcgods.skyblock.main.SkyBlock;

public class QuestAPI {

	private SkyBlock m = SkyBlock.getInstance();
	private MySQL mysql = m.mySQL;

	public void setPlayerQuests(UUID uuid, List<String> quests) {
		if (this.mysql.checkIfUserHasAlReadyQuestData(uuid)) {
			try {

				String questlist = null;

				if(quests != null) {
					ArrayList<String> questArray = new ArrayList<String>();
					questArray.addAll(quests);

					if (questArray != null) {
						for (int i = 0; i < questArray.size(); i++) {
							questlist = questArray.get(i) + ";";
						}
					}

					PreparedStatement pst = this.mysql.getConnection().prepareStatement("UPDATE " + this.mysql.getTable() + "_quest SET completedquests = ? WHERE uuid = ?");
					pst.setString(1, questlist);
					pst.setString(2, uuid.toString());
					pst.executeUpdate();
					pst.close();	
				}
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		} else {
			try {

				String questlist = null;
				
				if(quests != null) {
					ArrayList<String> questArray = new ArrayList<String>();
					questArray.addAll(quests);

					if (questArray != null) {
						for (int i = 0; i < questArray.size(); i++) {
							questlist = questArray.get(i) + ";";
						}
					}

					PreparedStatement pst = this.mysql.getConnection().prepareStatement("INSERT INTO " + this.mysql.getTable() + "_quest(uuid, completedquests) VALUES ('" + uuid.toString() + "','" + questlist + "')");
					pst.executeUpdate();
					pst.close();	
				}
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}
	
	public List<String> getPlayerQuests(UUID uuid) {
		if(this.mysql.checkIfUserHasAlReadyQuestData(uuid)) {
			try {
				PreparedStatement pst = this.mysql.getConnection().prepareStatement("SELECT completedquests FROM " + this.mysql.getTable() + "_quest WHERE uuid = ?");
				pst.setString(1, uuid.toString());
				ResultSet rs = pst.executeQuery();
				
				if(rs.next()) {
					
					String[] questList = rs.getString("completedquests").split("\\;");
					
					ArrayList<String> questArray = new ArrayList<String>();
					questArray.addAll(Arrays.asList(questList));
					
					return questArray;
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
}
