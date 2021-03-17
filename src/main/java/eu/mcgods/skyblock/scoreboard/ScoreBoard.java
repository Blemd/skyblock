package eu.mcgods.skyblock.scoreboard;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import eu.mcgods.skyblock.database.PlayerCache;

public class ScoreBoard {

	@SuppressWarnings("deprecation")
	public static void setScoreboard(UUID uuid) {
		Player p = Bukkit.getPlayer(uuid);
		
		ScoreboardManager sbm = Bukkit.getScoreboardManager();
		Scoreboard board = sbm.getNewScoreboard();
		
		Objective objective = board.registerNewObjective("McGods", "dummy");
		
		Team skyCoins = board.registerNewTeam("SkyCoins");
		skyCoins.addEntry("§0");
		if(PlayerCache.getSkyCoinsCache(uuid) != null) {
			skyCoins.setPrefix("§7➥ §6" + PlayerCache.getSkyCoinsCache(uuid));
		} else {
			skyCoins.setPrefix("§7➥ §cLade SkyCoins...");
		}
		
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName("§a§lSkyBlock");
		
		objective.getScore("§f").setScore(5);
		objective.getScore("§7§lSkyCoins").setScore(4);
		objective.getScore("§0").setScore(3);
		objective.getScore("§f§f").setScore(2);
		objective.getScore("§7Hilfe: §e/sb").setScore(1);
		
		p.setScoreboard(board);
	}
	
	public static void updateScoreboard(UUID uuid) {
		Player p = Bukkit.getPlayer(uuid);
		
		if(p.getScoreboard() == null) {
			setScoreboard(uuid);
		} else {
			
			Scoreboard board = p.getScoreboard();
			
			if(PlayerCache.getSkyCoinsCache(uuid) != null) {
				board.getTeam("SkyCoins").setPrefix("§7➥ §6" + PlayerCache.getSkyCoinsCache(uuid));
			} else {
				board.getTeam("SkyCoins").setPrefix("§7➥ §cLade SkyCoins...");
			}
		}
	}
}
