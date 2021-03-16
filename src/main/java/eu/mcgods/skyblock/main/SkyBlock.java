package eu.mcgods.skyblock.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import eu.mcgods.skyblock.commands.CoopCommand;
import eu.mcgods.skyblock.commands.VisitCommand;
import eu.mcgods.skyblock.database.MySQL;
import eu.mcgods.skyblock.database.PlayerCache;
import eu.mcgods.skyblock.listener.CoopProtectionListener;
import eu.mcgods.skyblock.listener.IslandManageListener;
import eu.mcgods.skyblock.listener.IslandNpcListener;
import eu.mcgods.skyblock.listener.IslandUpgradeListener;
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
		
		for(Player allOnline : Bukkit.getOnlinePlayers()) {
			new PlayerCache(allOnline);
		}
		System.out.println(this.prefix + "§cCreated §e" + Bukkit.getOnlinePlayers().size() + " §cnew PlayerCache's!");
	}
	
	@Override
	public void onDisable() {
		
		for(Player allOnline : Bukkit.getOnlinePlayers()) {
			PlayerCache.deleteUserCacheData(allOnline.getUniqueId());
		}
		System.out.println(this.prefix + "§cDeleted §e" + Bukkit.getOnlinePlayers().size() + " §cexisting PlayerCache's!");
		
		this.mySQL.disconnect();
	}
	
	private void loadListener() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new JoinListener(), this);
		pm.registerEvents(new QuitListener(), this);
		pm.registerEvents(new SkyBlockMenuItemListener(), this);
		pm.registerEvents(new SkyBlockMenuListener(), this);
		pm.registerEvents(new IslandNpcListener(), this);
		pm.registerEvents(new CoopProtectionListener(), this);
		pm.registerEvents(new IslandManageListener(), this);
		pm.registerEvents(new IslandUpgradeListener(), this);
	}
	
	private void loadCommands() {
		getCommand("coop").setExecutor(new CoopCommand());
		getCommand("visit").setExecutor(new VisitCommand());
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