package eu.mcgods.skyblock.commands;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.mcgods.skyblock.database.PlayerCache;
import eu.mcgods.skyblock.main.SkyBlock;

public class CoopCommand implements CommandExecutor {

	private SkyBlock m = SkyBlock.getInstance();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {

			Player p = (Player) sender;
			UUID uuid = p.getUniqueId();

			File world = null;

			try {
				world = new File(Bukkit.getServer().getWorldContainer(), uuid.toString());
			} catch (NullPointerException nullPointerException) {
			}

			if (sender instanceof Player) {
				if (args.length == 2) {
					if (args[0].equalsIgnoreCase("add")) {
						if (world.exists()) {
							Player target = Bukkit.getPlayer(args[1]);
							if (target != null) {
								if (target != sender) {
									if(PlayerCache.getCoopPlayerCacheUUIDs(uuid) != null) {
										if(PlayerCache.getCoopPlayerCacheSize(uuid) >= 4 && p.hasPermission("skyblock.vip")) {
											if(!PlayerCache.getCoopPlayerCacheUUIDs(uuid).contains(target.getUniqueId().toString())) {
												PlayerCache.addCoopPlayerCache(uuid, target.getUniqueId().toString());
												p.sendMessage(m.getPrefix() + "Du hast den Spieler §e" + target.getName() + " §7zu deiner Insel hinzugefügt.");
												p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
												target.sendMessage(m.getPrefix() + "Du wurdest zu der Insel von §e" + p.getName() + " §7hinzugefügt.");
											} else {
												p.sendMessage(m.getPrefix() + "Der Spieler §e" + target.getName() + " §7ist bereits zu deiner Insel hinzugefügt.");
												p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
											}
										} else if(!(PlayerCache.getCoopPlayerCacheSize(uuid) >= 4)) {
											if(!PlayerCache.getCoopPlayerCacheUUIDs(uuid).contains(target.getUniqueId().toString())) {
												PlayerCache.addCoopPlayerCache(uuid, target.getUniqueId().toString());
												p.sendMessage(m.getPrefix() + "Du hast den Spieler §e" + target.getName() + " §7zu deiner Insel hinzugefügt.");
												p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
												target.sendMessage(m.getPrefix() + "Du wurdest zu der Insel von §e" + p.getName() + " §7hinzugefügt.");	
											} else {
												p.sendMessage(m.getPrefix() + "Der Spieler §e" + target.getName() + " §7ist bereits zu deiner Insel hinzugefügt.");
												p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
											}
										}
									} else {
										PlayerCache.setCoopPlayerCache(uuid, target.getUniqueId().toString());
										p.sendMessage(m.getPrefix() + "Du hast den Spieler §e" + target.getName() + " §7zu deiner Insel hinzugefügt.");
										p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
										target.sendMessage(m.getPrefix() + "Du wurdest zu der Insel von §e" + p.getName() + " §7hinzugefügt.");
									}
								} else {
									p.sendMessage(m.getPrefix() + "Du kannst dich nicht selber als Coop-Spieler hinzufügen.");
									p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
								}
							} else {
								p.sendMessage(m.getPrefix() + "Der Spieler §e" + args[1] + " §7ist derzeit nicht Online.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						} else {
							p.sendMessage(m.getPrefix() + "Du hast noch keine Insel!");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					}
				} else {
					p.sendMessage(m.getPrefix() + "/coop add <Spieler>");
				}
			} else {
				sender.sendMessage(m.getPrefix() + "§cDieser Befehl kann nur von einem Spieler ausgeführt werden!");
			}
		} catch (Exception exception) {
		}
		return false;
	}
}