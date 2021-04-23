package eu.mcgods.skyblock.listener;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.luckperms.api.LuckPermsProvider;

public class ChatDesign implements Listener {

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		try {

			Player p = e.getPlayer();
			UUID uuid = p.getUniqueId();
			
			String message = e.getMessage();

			if (LuckPermsProvider.get().getUserManager().getUser(uuid).getPrimaryGroup().equalsIgnoreCase("Admin")) {
				e.setFormat(String.format("§4§lAdmin §8|§c %s§8: §7%s", p.getName(), message));
			} else if (LuckPermsProvider.get().getUserManager().getUser(uuid).getPrimaryGroup().equalsIgnoreCase("Developer")) {
				e.setFormat(String.format("§3§lDev §8|§b %s§8: §7%s", p.getName(), message));
			} else if (LuckPermsProvider.get().getUserManager().getUser(uuid).getPrimaryGroup().equalsIgnoreCase("Supporter")) {
				e.setFormat(String.format("§1§lSupporter §8|§9 %s§8: §7%s", p.getName(), message));
			} else if (LuckPermsProvider.get().getUserManager().getUser(uuid).getPrimaryGroup().equalsIgnoreCase("Builder")) {
				e.setFormat(String.format("§2§lBuilder §8|§a %s§8: §7%s", p.getName(), message));
			} else if (LuckPermsProvider.get().getUserManager().getUser(uuid).getPrimaryGroup().equalsIgnoreCase("VIP")) {
				e.setFormat(String.format("§6§lVIP §8|§e %s§8: §7%s", p.getName(), message));
			} else {
				e.setFormat(String.format("§7§lSpieler §8|§7 %s§8: §7%s", p.getName(), message));
			}
		} catch (NullPointerException nullPointerException) {
		}
	}
}