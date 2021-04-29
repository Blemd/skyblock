package eu.mcgods.skyblock.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

public class MySQL {
	
	private Connection connection;

	private String host;
	private String port;
	private String dataBase;
	private String table;
	private String userName;
	private String passWord;

	// Connect to database
	public void connect() {
		
		final Properties prop = new Properties();
		prop.setProperty("user", this.userName);
		prop.setProperty("password", this.passWord);
		prop.setProperty("useSSL", "false");
		prop.setProperty("autoReconnect", "true");
		
		if (!isConnected()) {
			try {
				this.connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + dataBase, prop);
				System.out.println("Die MySQL Verbindung konnte erfolgreich hergestellt werden!");
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}

	// Disconnect from database
	public void disconnect() {
		if (isConnected()) {
			try {
				this.connection.close();
				System.out.println("Die MySQL Verbindung wurde erfolgreich beendet.");
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		}
	}

	// Automatic creation of tables if none are available
	public void createTable() {
		try {
			this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.table + "_islandbank (ID INT PRIMARY KEY AUTO_INCREMENT, world VARCHAR(100), amount INT(16))").executeUpdate();
			this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.table + "_skycoins (ID INT PRIMARY KEY AUTO_INCREMENT, uuid VARCHAR(100), amount INT(16))").executeUpdate();
			this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.table + "_inventar (ID INT PRIMARY KEY AUTO_INCREMENT, uuid VARCHAR(100), inventar TEXT(1431655765))").executeUpdate();
			this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.table + "_quest (ID INT PRIMARY KEY AUTO_INCREMENT, uuid VARCHAR(100), completedquests LONGTEXT)").executeUpdate();
			this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.table + "_coop (ID INT PRIMARY KEY AUTO_INCREMENT, world VARCHAR(100), memberlist TEXT(1431655765))").executeUpdate();
			this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.table + "_islandsize (ID INT PRIMARY KEY AUTO_INCREMENT, uuid VARCHAR(100), size INT(16))").executeUpdate();
//			this.connection.prepareStatement("CREATE TABLE IF NOT EXISTS " + this.table + "_userlog (ID INT PRIMARY KEY AUTO_INCREMENT, uuid VARCHAR(100), ip VARCHAR(100), lastlogin TIMESTAMP(), lastlogout TIMESTAMP(), messsages LONGTEXT").executeUpdate();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		}
	}
	
	// Check if user already has a entry in bank database
	public boolean checkIfUserHasAlReadyBankData(UUID uuid) {
		try {
			PreparedStatement pst = this.connection.prepareStatement("SELECT UUID FROM " + this.table + "_bank WHERE UUID = ?");
			pst.setString(1, uuid.toString());
			ResultSet rs = pst.executeQuery();
			
			return rs.next();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return false;
		}
	}
	
	// Check if user already has a entry in skycoins database
	public boolean checkIfUserHasAlReadySkyCoinsData(UUID uuid) {
		try {
			PreparedStatement pst = this.connection.prepareStatement("SELECT UUID FROM " + this.table + "_skycoins WHERE UUID = ?");
			pst.setString(1, uuid.toString());
			ResultSet rs = pst.executeQuery();
			
			return rs.next();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return false;
		}
	}
	
	public boolean checkIfUserHasAlReadyInventoryData(UUID uuid) {
		try {
			PreparedStatement pst = this.connection.prepareStatement("SELECT UUID FROM " + this.table + "_inventar WHERE UUID = ?");
			pst.setString(1, uuid.toString());
			ResultSet rs = pst.executeQuery();
			
			return rs.next();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return false;
		}
	}
	
	public boolean checkIfUserHasAlReadyCoopData(UUID uuid) {
		try {
			PreparedStatement pst = this.connection.prepareStatement("SELECT WORLD FROM " + this.table + "_coop WHERE WORLD = ?");
			pst.setString(1, uuid.toString());
			ResultSet rs = pst.executeQuery();
			
			return rs.next();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return false;
		}
	}
	
	public boolean checkIfUserHasAlReadyQuestData(UUID uuid) {
		try {
			PreparedStatement pst = this.connection.prepareStatement("SELECT COMPLETEDQUESTS FROM " + this.table + "_quest WHERE UUID = ?");
			pst.setString(1, uuid.toString());
			ResultSet rs = pst.executeQuery();
			
			return rs.next();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return false;
		}
	}
	
	public boolean checkIfUserHasAlReadyIslandSizeData(UUID uuid) {
		try {
			PreparedStatement pst = this.connection.prepareStatement("SELECT UUID FROM " + this.table + "_islandsize WHERE UUID = ?");
			pst.setString(1, uuid.toString());
			ResultSet rs = pst.executeQuery();
			
			return rs.next();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			return false;
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDataBase() {
		return dataBase;
	}

	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Connection getConnection() {
		return connection;
	}

	public boolean isConnected() {
		return this.connection != null;
	}
}