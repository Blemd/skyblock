package eu.mcgods.skyblock.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.mcgods.skyblock.main.SkyBlock;

public class isCommand implements CommandExecutor {

	private SkyBlock m = SkyBlock.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		Player p = (Player) sender;

		File world = null;

		if (sender instanceof Player) {
			try {
				world = new File(Bukkit.getServer().getWorldContainer(), p.getUniqueId().toString());
			} catch (NullPointerException nullPointerException) {
			}
			if (world.exists()) {
				p.teleport(Bukkit.getWorld(p.getUniqueId().toString()).getSpawnLocation());
				p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
			} else {
				p.sendMessage(m.getPrefix() + "Du hast derzeit noch keine Insel.");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
			}
		} else {
			sender.sendMessage(m.getPrefix() + "§cDieser Befehl kann nur von einem Spieler ausgeführt werden!");
		}
		return false;
	}
}