package eu.mcgods.skyblock.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.mcgods.skyblock.main.SkyBlock;
import eu.mcgods.skyblock.utils.LocationManager;

public class SetLocationCommand implements CommandExecutor {

	private SkyBlock m = SkyBlock.getInstance();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = (Player) sender;
		
		if(sender instanceof Player) {
			if(p.hasPermission("skyblock.setLoc")) {
				if(args.length == 1) {
					LocationManager.setLocation(args[0], p.getLocation());
					p.sendMessage(m.getPrefix() + "Die Location wurde gesetzt.");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
				} else {
					p.sendMessage(m.getPrefix() + "/setLoc <Name>");
				}
			} else {
				p.sendMessage(m.getPrefix() + "Du hast nicht die benötigten Rechte für diesen Befehl!");
			}
		} else {
			sender.sendMessage(m.getPrefix() + "§cDieser Befehl kann nur von einem Spieler ausgeführt werden!");
		}
		return false;
	}
}