package eu.mcgods.skyblock.listener;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import eu.mcgods.skyblock.database.InventoryAPI;
import eu.mcgods.skyblock.database.MySQL;
import eu.mcgods.skyblock.database.PlayerCache;
import eu.mcgods.skyblock.database.SkyCoinsAPI;
import eu.mcgods.skyblock.main.SkyBlock;
import eu.mcgods.skyblock.scoreboard.ScoreBoard;
import eu.mcgods.skyblock.utils.itemBuilder;

public class JoinListener implements Listener {

	private SkyBlock m = SkyBlock.getInstance();
	private MySQL mysql = m.mySQL;
	
	private SkyCoinsAPI coinsAPI = new SkyCoinsAPI();
	private InventoryAPI invAPI = new InventoryAPI();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		
		e.setJoinMessage(null);
		
		final Player p = e.getPlayer();
		final UUID uuid = p.getUniqueId();
		
		ScoreBoard.setScoreboard(uuid);
		
		Thread thread = new Thread(new Runnable() {

			public void run() {
				PlayerCache.setItemContents(uuid, p.getInventory().getContents());
				PlayerCache.setArmorContents(uuid, p.getInventory().getArmorContents());
				p.getInventory().clear();
				p.sendMessage(m.getPrefix() + "Dein Spielstand wird aus der Datenbank geladen...");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
				if(mysql.isConnected()) {
					try {
						if(mysql.checkIfUserHasAlReadyInventoryData(uuid) && mysql.checkIfUserHasAlReadySkyCoinsData(uuid)) {
							new PlayerCache(p);
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							p.sendMessage(m.getPrefix() + "Dein Spielstand wurde erfolgreich geladen.");
						} else {
							p.getInventory().setContents(PlayerCache.getItemContents(uuid));
							p.getInventory().setArmorContents(PlayerCache.getArmorContents(uuid));
							p.getInventory().setItem(8, itemBuilder.createSkullWithOutLore(1, p.getName(), "§7✦ §a§lSkyBlock §7✦"));
							coinsAPI.setSkyCoins(uuid, 1000);
							invAPI.setInv(uuid);
							PlayerCache.setSkyCoinsCache(uuid, coinsAPI.getSkyCoins(uuid));
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							p.sendMessage(m.getPrefix() + "Für dich wurde ein neuer Spielstand eingerichtet.");
						}
					} catch (Exception exception) {
						p.getInventory().setContents(PlayerCache.getItemContents(uuid));
						p.getInventory().setArmorContents(PlayerCache.getArmorContents(uuid));
						p.getInventory().setItem(8, itemBuilder.createSkullWithOutLore(1, p.getName(), "§7✦ §a§lSkyBlock §7✦"));
						coinsAPI.setSkyCoins(uuid, 1000);
						invAPI.setInv(uuid);
						PlayerCache.setSkyCoinsCache(uuid, coinsAPI.getSkyCoins(uuid));
						p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
						p.sendMessage(m.getPrefix() + "Für dich wurde ein neuer Spielstand eingerichtet.");
					}
				} else {
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
					p.getInventory().setContents(PlayerCache.getItemContents(uuid));
					p.getInventory().setArmorContents(PlayerCache.getArmorContents(uuid));
					PlayerCache.getItemContentsMap().remove(uuid);
					PlayerCache.getArmorContentsMap().remove(uuid);
					Bukkit.getScheduler().runTaskLater(SkyBlock.getInstance(), new Runnable() {
						
						public void run() {
							p.kickPlayer("§a§lSkyBlock" + "\n" + "§cUnsere Skyblock Datenbank ist derzeit leider nicht erreichbar, bitte versuche es später erneut.");
						}
					}, 2*20);
				}
			}
		});
		thread.start();
	}
}