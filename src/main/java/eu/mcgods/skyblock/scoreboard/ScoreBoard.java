package eu.mcgods.skyblock.scoreboard;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import eu.mcgods.skyblock.database.PlayerCache;
import net.luckperms.api.LuckPermsProvider;

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
		}
		
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName("§a§lSkyBlock");
		
		objective.getScore("§f").setScore(5);
		objective.getScore("§7§lSkyCoins").setScore(4);
		objective.getScore("§0").setScore(3);
		objective.getScore("§f§f").setScore(2);
		objective.getScore("§7Hilfe: §e/sb").setScore(1);
		
		p.setScoreboard(board);
		
		p.setPlayerListHeaderFooter("\n" + "§9MC§6GODS §8x §a§lSkyBlock" + "\n", "\n" + "§9Discord §8>> §6Minecraft-gods.eu/dc" + "\n" + "§9Teamspeak §8>> §6Minecraft-gods.eu" + "\n");
		setTablist(uuid);
	}
	
	public static void updateScoreboard(UUID uuid) {
		Player p = Bukkit.getPlayer(uuid);
		
		if(p.getScoreboard() == null) {
			setScoreboard(uuid);
		} else {
			
			Scoreboard board = p.getScoreboard();
			
			if(PlayerCache.getSkyCoinsCache(uuid) != null) {
				board.getTeam("SkyCoins").setPrefix("§7➥ §6" + PlayerCache.getSkyCoinsCache(uuid));
			}
		}
	}
	
	public static void setTablist(UUID uuid) {
		Player p = Bukkit.getPlayer(uuid);

		if (p.getScoreboard() == null) {
			setScoreboard(uuid);
		} else {

			Scoreboard board = p.getScoreboard();

			Team admin = board.registerNewTeam("01Admin");
			admin.setPrefix("§4§lAdmin §8| ");
			admin.setColor(ChatColor.RED);
			Team developer = board.registerNewTeam("02Developer");
			developer.setPrefix("§3§lDev §8| ");
			developer.setColor(ChatColor.AQUA);
			Team supporter = board.registerNewTeam("03Supporter");
			supporter.setPrefix("§1§lSupporter §8| ");
			supporter.setColor(ChatColor.BLUE);
			Team builder = board.registerNewTeam("04Builder");
			builder.setPrefix("§2§lBuilder §8| ");
			builder.setColor(ChatColor.GREEN);
			Team vip = board.registerNewTeam("05VIP");
			vip.setPrefix("§6§lVIP §8| ");
			vip.setColor(ChatColor.YELLOW);
			Team player = board.registerNewTeam("06Player");
			player.setPrefix("§7§lSpieler §8| ");
			player.setColor(ChatColor.GRAY);

			for (Player all : Bukkit.getOnlinePlayers()) {
				String playerName = all.getName();
				if (LuckPermsProvider.get().getUserManager().getUser(playerName).getPrimaryGroup().equalsIgnoreCase("Admin")) {
					admin.addEntry(playerName);
					continue;
				}
				if (LuckPermsProvider.get().getUserManager().getUser(playerName).getPrimaryGroup().equalsIgnoreCase("Developer")) {
					developer.addEntry(playerName);
					continue;
				}
				if (LuckPermsProvider.get().getUserManager().getUser(playerName).getPrimaryGroup().equalsIgnoreCase("Supporter")) {
					supporter.addEntry(playerName);
					continue;
				}
				if (LuckPermsProvider.get().getUserManager().getUser(playerName).getPrimaryGroup().equalsIgnoreCase("Builder")) {
					builder.addEntry(playerName);
					continue;
				}
				if (LuckPermsProvider.get().getUserManager().getUser(playerName).getPrimaryGroup().equalsIgnoreCase("VIP")) {
					vip.addEntry(playerName);
					continue;
				}
				player.addEntry(playerName);
			}
		}
	}

	public static void updateTablist(UUID uuid) {
		Player p = Bukkit.getPlayer(uuid);

		if (p.getScoreboard() == null) {
			setTablist(uuid);
		} else {

			Scoreboard board = p.getScoreboard();

			Team admin = board.getTeam("01Admin");
			Team developer = board.getTeam("02Developer");
			Team supporter = board.getTeam("03Supporter");
			Team builder = board.getTeam("04Builder");
			Team vip = board.getTeam("05VIP");
			Team player = board.getTeam("06Player");

			if (admin == null || developer == null || supporter == null || builder == null || vip == null || player == null) {
				setTablist(uuid);
				return;
			}

				for (Player all : Bukkit.getOnlinePlayers()) {
					String playerName = all.getName();
					if (LuckPermsProvider.get().getUserManager().getUser(playerName).getPrimaryGroup().equalsIgnoreCase("Admin")) {
						admin.addEntry(playerName);
						continue;
					}
					if (LuckPermsProvider.get().getUserManager().getUser(playerName).getPrimaryGroup().equalsIgnoreCase("Developer")) {
						developer.addEntry(playerName);
						continue;
					}
					if (LuckPermsProvider.get().getUserManager().getUser(playerName).getPrimaryGroup().equalsIgnoreCase("Supporter")) {
						supporter.addEntry(playerName);
						continue;
					}
					if (LuckPermsProvider.get().getUserManager().getUser(playerName).getPrimaryGroup().equalsIgnoreCase("Builder")) {
						builder.addEntry(playerName);
						continue;
					}
					if (LuckPermsProvider.get().getUserManager().getUser(playerName).getPrimaryGroup().equalsIgnoreCase("VIP")) {
						vip.addEntry(playerName);
						continue;
					}
					player.addEntry(playerName);
				}
				return;
			}
			setTablist(uuid);
		}
	}