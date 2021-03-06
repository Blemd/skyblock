package eu.mcgods.skyblock.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import eu.mcgods.skyblock.commands.CoopCommand;
import eu.mcgods.skyblock.commands.EcoCommand;
import eu.mcgods.skyblock.commands.PayCommand;
import eu.mcgods.skyblock.commands.SetLocationCommand;
import eu.mcgods.skyblock.commands.VisitCommand;
import eu.mcgods.skyblock.commands.isCommand;
import eu.mcgods.skyblock.commands.sbCommand;
import eu.mcgods.skyblock.database.CoopAPI;
import eu.mcgods.skyblock.database.MySQL;
import eu.mcgods.skyblock.database.PlayerCache;
import eu.mcgods.skyblock.listener.ChatDesignListener;
import eu.mcgods.skyblock.listener.DeathAndRespawnListener;
import eu.mcgods.skyblock.listener.FastTravelListener;
import eu.mcgods.skyblock.listener.IslandBuildListener;
import eu.mcgods.skyblock.listener.IslandDamageListener;
import eu.mcgods.skyblock.listener.IslandManageListener;
import eu.mcgods.skyblock.listener.IslandNpcListener;
import eu.mcgods.skyblock.listener.IslandUpgradeListener;
import eu.mcgods.skyblock.listener.JoinListener;
import eu.mcgods.skyblock.listener.LeafDecayListener;
import eu.mcgods.skyblock.listener.QuestEasyListener;
import eu.mcgods.skyblock.listener.QuestMenuListener;
import eu.mcgods.skyblock.listener.QuestMidListener;
import eu.mcgods.skyblock.listener.QuitListener;
import eu.mcgods.skyblock.listener.SkyBlockMenuItemListener;
import eu.mcgods.skyblock.listener.SkyBlockMenuListener;
import eu.mcgods.skyblock.scoreboard.ScoreBoard;

public class SkyBlock extends JavaPlugin {

	private static SkyBlock instance;
	
	private final String prefix = "§a§lSkyBlock §8➢ §7";
	
	public MySQL mySQL = new MySQL();
	
	private CoopAPI coopAPI;
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		this.loadConfig();
		this.initializeMySQLConfig();
		this.mySQL.connect();
		this.mySQL.createTable();
		
		coopAPI = new CoopAPI();
		
		this.loadListener();
		this.loadCommands();
		
		for(Player allOnline : Bukkit.getOnlinePlayers()) {
			new PlayerCache(allOnline);
			ScoreBoard.updateScoreboard(allOnline.getUniqueId());
			ScoreBoard.updateTablist(allOnline.getUniqueId());
		}
		
		for(int i = 0; i < coopAPI.getAllCoopData().size(); i++) {
			UUID userUUID = coopAPI.getAllCoopData().get(i);
			String[] coopMember = coopAPI.getCoopPartners(userUUID, userUUID.toString()).split("\\;");
			List<String> coopMemberList = new ArrayList<String>();
			coopMemberList.addAll(Arrays.asList(coopMember));
			PlayerCache.getCoopCacheMap().put(userUUID, coopMemberList);
		}
		
		System.out.println(this.prefix + "§cCreated §e" + Bukkit.getOnlinePlayers().size() + " §cnew PlayerCache's!");
		
		new BukkitRunnable() {
			
			public void run() {
				for(Player all : Bukkit.getOnlinePlayers()) {
					ScoreBoard.updateScoreboard(all.getUniqueId());
				}
			}
		}.runTaskTimer(getInstance(), 20, 20);
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
		pm.registerEvents(new IslandManageListener(), this);
		pm.registerEvents(new IslandUpgradeListener(), this);
		pm.registerEvents(new QuestMenuListener(), this);
		pm.registerEvents(new QuestEasyListener(), this);
		pm.registerEvents(new QuestMidListener(), this);
		pm.registerEvents(new FastTravelListener(), this);
		pm.registerEvents(new ChatDesignListener(), this);
		pm.registerEvents(new DeathAndRespawnListener(), this);
		pm.registerEvents(new LeafDecayListener(), this);
		pm.registerEvents(new IslandBuildListener(), this);
		pm.registerEvents(new IslandDamageListener(), this);
	}
	
	private void loadCommands() {
		getCommand("coop").setExecutor(new CoopCommand());
		getCommand("visit").setExecutor(new VisitCommand());
		getCommand("pay").setExecutor(new PayCommand());
		getCommand("eco").setExecutor(new EcoCommand());
		getCommand("sb").setExecutor(new sbCommand());
		getCommand("setLoc").setExecutor(new SetLocationCommand());
		getCommand("is").setExecutor(new isCommand());
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