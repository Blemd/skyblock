package eu.mcgods.skyblock.listener;

import java.util.Iterator;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import eu.mcgods.skyblock.utils.itemBuilder;

public class DeathAndRespawnListener implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		try {
			
			if(e.getDrops() != null) {
				List<ItemStack> list = e.getDrops();
				Iterator<ItemStack> i = list.iterator();
				
				while(i.hasNext()) {
					ItemStack item = i.next();
					if(item.getType() == Material.PLAYER_HEAD && item.getItemMeta().getDisplayName().equalsIgnoreCase("§7✦ §a§lSkyBlock §7✦")) {
						i.remove();
					}
				}
			}	
		} catch (NullPointerException nullPointerException) {
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		try {
			
			Player p = e.getPlayer();
			
			if(p.getInventory().getItem(8) == null) {
				p.getInventory().setItem(8, itemBuilder.createSkullWithOutLore(1, p.getName(), "§7✦ §a§lSkyBlock §7✦"));				
			}
			
		} catch (NullPointerException nullPointerException) {
		}
	}
}