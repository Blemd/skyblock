package eu.mcgods.skyblock.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import eu.mcgods.skyblock.database.MySQL;
import eu.mcgods.skyblock.listener.JoinListener;
import eu.mcgods.skyblock.listener.QuitListener;
import eu.mcgods.skyblock.listener.SkyBlockMenuItemListener;
import eu.mcgods.skyblock.listener.SkyBlockMenuListener;

public class SkyBlock extends JavaPlugin {

	private static SkyBlock instance;
	
	private final String prefix = "§a§lSkyblock §8➢ §7";
	
	public MySQL mySQL = new MySQL();
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		this.loadConfig();
		this.initializeMySQLConfig();
		this.mySQL.connect();
		this.mySQL.createTable();
		
		this.loadListener();
		this.loadCommands();
	}
	
	@Override
	public void onDisable() {
		this.mySQL.disconnect();
	}
	
	private void loadListener() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new QuitListener(), this);
		pm.registerEvents(new SkyBlockMenuItemListener(), this);
		pm.registerEvents(new SkyBlockMenuListener(), this);
	}
	
	private void loadCommands() {
	}
	
	private void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	private void initializeMySQLConfig() {
		mySQL.setHost(getConfig().getConfigurationSection("MySQL").getString("Host"));
		mySQL.setPort(getConfig().getConfigurationSection("MySQL").getString("Port"));
		mySQL.setDataBase(getConfig().getConfigurationSection("MySQL").getString("Database"));
		mySQL.setTable(getConfig().getConfigurationSection("MySQL").getString("Table"));
		mySQL.setUserName(getConfig().getConfigurationSection("MySQL").getString("Username"));
		mySQL.setPassWord(getConfig().getConfigurationSection("MySQL").getString("Password"));
	}
	
	// Returns the instance
	public static SkyBlock getInstance() {
		return instance;
	}
	
	// Returns the prefix
	public String getPrefix() {
		return this.prefix;
	}
}