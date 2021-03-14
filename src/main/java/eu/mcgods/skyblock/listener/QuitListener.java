package eu.mcgods.skyblock.listener;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import eu.mcgods.skyblock.database.InventoryAPI;
import eu.mcgods.skyblock.database.PlayerCache;

public class QuitListener implements Listener {

	private InventoryAPI invAPI = new InventoryAPI();
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		
		final Player p = e.getPlayer();
		final UUID uuid = p.getUniqueId();
		
		invAPI.setInv(uuid);
		
		e.setQuitMessage(null);
		
		Thread thread = new Thread(new Runnable() {
			
			public void run() {
				PlayerCache.deleteUserCacheData(uuid);
			}
		});
		thread.start();
	}
}