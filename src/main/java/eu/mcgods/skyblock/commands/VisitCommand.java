package eu.mcgods.skyblock.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.mcgods.skyblock.main.SkyBlock;

public class VisitCommand implements CommandExecutor {

	private SkyBlock m = SkyBlock.getInstance();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		try {

			Player p = (Player) sender;

			File world = null;

			if (sender instanceof Player) {
				if (args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					OfflinePlayer targetOffline = Bukkit.getOfflinePlayer(args[0]);
					if (target != null) {
						try {
							world = new File(Bukkit.getServer().getWorldContainer(), target.getUniqueId().toString());
						} catch (NullPointerException nullPointerException) {
						}
						if (world.exists()) {
							p.teleport(Bukkit.getWorld(target.getUniqueId().toString()).getSpawnLocation());
							p.sendMessage(m.getPrefix() + "Du wurdest zu der Insel von §e" + target.getName() + " §7teleportiert.");
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
							target.sendMessage(m.getPrefix() + "Der Spieler §e" + p.getName() + " §7besucht deine Insel.");
						} else {
							p.sendMessage(m.getPrefix() + "Der Spieler §e" + target.getName() + " §7hat noch keine Insel.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					} else {
						try {
							world = new File(Bukkit.getServer().getWorldContainer(), targetOffline.getUniqueId().toString());
						} catch (NullPointerException nullPointerException) {
						}
						if(world != null) {
							p.teleport(Bukkit.getWorld(targetOffline.getUniqueId().toString()).getSpawnLocation());
							p.sendMessage(m.getPrefix() + "Du wurdest zu der Insel von §e" + args[0] + " §7teleportiert.");
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
						} else {
							p.sendMessage(m.getPrefix() + "Der Spieler §e" + args[0] + " §7hat noch keine Insel oder war noch nie Online.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					}
				} else {
					p.sendMessage(m.getPrefix() + "/visit <Spieler>");
				}
			} else {
				sender.sendMessage(m.getPrefix() + "§cDieser Befehl kann nur von einem Spieler ausgeführt werden!");
			}
		} catch (Exception exception) {
		}
		return false;
	}
}