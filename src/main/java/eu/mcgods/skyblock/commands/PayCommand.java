package eu.mcgods.skyblock.commands;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.mcgods.skyblock.database.PlayerCache;
import eu.mcgods.skyblock.main.SkyBlock;

public class PayCommand implements CommandExecutor {

	private SkyBlock m = SkyBlock.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {

			Player p = (Player) sender;
			UUID uuid = p.getUniqueId();

			if (sender instanceof Player) {
				if (args.length == 2) {
					if (StringUtils.isNumeric(args[1])) {
						Player target = Bukkit.getPlayer(args[0]);
						Integer amount = Integer.valueOf(args[1]);
						if (target != null) {
							if (target != sender) {
								if (PlayerCache.getSkyCoinsCache(uuid) >= amount) {
									PlayerCache.removeSkyCoinsCache(uuid, amount);
									p.sendMessage(m.getPrefix() + "Du hast §6" + amount + " SkyCoins §7an §e" + target.getName() + " §7überwiesen.");
									PlayerCache.addSkyCoinsCache(target.getUniqueId(), amount);
									target.sendMessage(m.getPrefix() + "Dir wurden §6" + amount + " SkyCoins §7von §e" + p.getName() + " §7überwiesen.");
									p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
								} else {
									p.sendMessage(m.getPrefix() + "Du hast nicht genügend §6SkyCoins§7.");
									p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
								}
							} else {
								p.sendMessage(m.getPrefix() + "Du kannst dir selber keine §6SkyCoins §7überweisen.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						} else {
							p.sendMessage(m.getPrefix() + "Der Spieler §a" + args[0] + " §7ist nicht Online.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					} else {
						p.sendMessage(m.getPrefix() + "Der Betrag §e" + args[1] + " §7ist ungültig.");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
					}
				} else {
					p.sendMessage(m.getPrefix() + "/pay <Spieler> <Betrag>");
				}
			} else {
				sender.sendMessage(m.getPrefix() + "§cDieser Befehl kann nur von einem Spieler ausgeführt werden!");
			}
		} catch (Exception exception) {
		}
		return false;
	}
}