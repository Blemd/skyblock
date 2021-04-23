package eu.mcgods.skyblock.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.mcgods.skyblock.database.PlayerCache;
import eu.mcgods.skyblock.database.SkyCoinsAPI;
import eu.mcgods.skyblock.main.SkyBlock;

public class EcoCommand implements CommandExecutor {
	
	private SkyBlock m = SkyBlock.getInstance();
	private SkyCoinsAPI coinsAPI = new SkyCoinsAPI();
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		try {

			Player p = (Player) sender;

			if (sender instanceof Player) {
				if (p.hasPermission("skyblock.eco")) {
					if (args.length == 3) {
						if (StringUtils.isNumeric(args[2])) {
							Player target = Bukkit.getPlayer(args[1]);
							Integer amount = Integer.valueOf(args[2]);
							if (args[0].equalsIgnoreCase("set")) {
								if (target != null) {
									PlayerCache.setSkyCoinsCache(target.getUniqueId(), amount);
									p.sendMessage(m.getPrefix() + "Die §6SkyCoins §7von §e" + args[1] + " §7wurden auf §6" + amount + " §7gesetzt.");
								} else {
									try {
										coinsAPI.setSkyCoins(Bukkit.getOfflinePlayer(args[1]).getUniqueId(), amount);
										p.sendMessage(m.getPrefix() + "Die §6SkyCoins §7von §e" + args[1] + " §7wurden auf §6" + amount + " §7gesetzt.");
									} catch (Exception exception) {
										p.sendMessage(m.getPrefix() + "Der Spieler §e" + args[1] + " §7ist nicht Online und existiert nicht in der Datenbank.");
									}
								}
							} else if (args[0].equalsIgnoreCase("add")) {
								if (target != null) {
									PlayerCache.addSkyCoinsCache(target.getUniqueId(), amount);
									p.sendMessage(m.getPrefix() + "Dem Spieler §e" + args[1] + " §7wurden §6" + amount + " SkyCoins §7hinzugefügt.");
								} else {
									try {
										coinsAPI.addSkyCoins(Bukkit.getOfflinePlayer(args[1]).getUniqueId(), amount);
										p.sendMessage(m.getPrefix() + "Dem Spieler §e" + args[1] + " §7wurden §6" + amount + " SkyCoins §7hinzugefügt.");
									} catch (Exception exception) {
										p.sendMessage(m.getPrefix() + "Der Spieler §e" + args[1] + " §7ist nicht Online und existiert nicht in der Datenbank.");
									}
								}
							} else if (args[0].equalsIgnoreCase("remove")) {
								if (target != null) {
									PlayerCache.removeSkyCoinsCache(target.getUniqueId(), amount);
									p.sendMessage(m.getPrefix() + "Dem Spieler §e" + args[1] + " §7wurden §6" + amount + " SkyCoins §7entfernt.");
								} else {
									try {
										coinsAPI.removeSkyCoins(Bukkit.getOfflinePlayer(args[1]).getUniqueId(), amount);
										p.sendMessage(m.getPrefix() + "Dem Spieler §e" + args[1] + " §7wurden §6" + amount + " SkyCoins §7entfernt.");
									} catch (Exception exception) {
										p.sendMessage(m.getPrefix() + "Der Spieler §e" + args[1] + " §7ist nicht Online und existiert nicht in der Datenbank.");
									}
								}
							}
						} else {
							p.sendMessage(m.getPrefix() + "Der Betrag §e" + args[2] + " §7ist ungültig.");
						}
					} else {
						p.sendMessage(m.getPrefix() + "/eco <set,add,remove> <Spieler> <Betrag>");
					}
				} else {
					p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Rechte für diesen Befehl!");
				}
			} else {
				sender.sendMessage(m.getPrefix() + "§cDieser Befehl kann nur von einem Spieler ausgeführt werden!");
			}
		} catch (Exception exception) {
		}
		return false;
	}
}