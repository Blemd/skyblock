package eu.mcgods.skyblock.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import eu.mcgods.skyblock.main.SkyBlock;

public class QuestAPI {

	private SkyBlock m = SkyBlock.getInstance();
	private MySQL mysql = m.mySQL;

	public void setPlayerQuests(UUID uuid, List<String> questlist) {
		if (this.mysql.checkIfUserHasAlReadyQuestData(uuid)) {
			try {

				String list = "";

				ArrayList<String> questArray = new ArrayList<String>();
				questArray.addAll(questlist);

				if (questArray != null) {
					for (int i = 0; i < questArray.size(); i++) {
						list += questArray.get(i) + ";";
					}
				}

				PreparedStatement pst = this.mysql.getConnection().prepareStatement("UPDATE " + this.mysql.getTable() + "_quest SET completedquests = ? WHERE uuid = ?");
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

				ArrayList<String> questArray = new ArrayList<String>();
				questArray.addAll(questlist);

				if (questArray != null) {
					for (int i = 0; i < questArray.size(); i++) {
						list += questArray.get(i) + ";";
					}
				}

				PreparedStatement pst = this.mysql.getConnection().prepareStatement("INSERT INTO " + this.mysql.getTable() + "_quest(uuid, completedquests) VALUES ('" + uuid.toString() + "','" + list + "')");
				pst.executeUpdate();
				pst.close();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}

	public String getPlayerQuests(UUID uuid) {
		if (this.mysql.checkIfUserHasAlReadyQuestData(uuid)) {
			try {
				PreparedStatement pst = this.mysql.getConnection().prepareStatement("SELECT completedquests FROM " + this.mysql.getTable() + "_quest WHERE uuid = ?");
				pst.setString(1, uuid.toString());
				ResultSet rs = pst.executeQuery();

				if (rs.next()) {
					return rs.getString("completedquests");
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
