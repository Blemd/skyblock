package eu.mcgods.skyblock.commands;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import eu.mcgods.skyblock.main.SkyBlock;

public class sbCommand implements CommandExecutor {

	private SkyBlock m = SkyBlock.getInstance();
	
	private HashMap<UUID, Long> cooldown = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player p = (Player) sender;
		
		if(sender instanceof Player) {
			Thread thread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					int cooldownTime = 10;
					if(cooldown.containsKey(p.getUniqueId())) {
						long secondsLeft = ((Long) cooldown.get(p.getUniqueId())).longValue() / 1000L + cooldownTime - System.currentTimeMillis() / 1000L;
						if(secondsLeft > 0L) {
							p.sendMessage(m.getPrefix() + "Bitte warte noch §e" + secondsLeft + " §7Sekunden, bevor du den Befehl erneut ausführst.");
							return;
						}
					}
					cooldown.put(p.getUniqueId(), Long.valueOf(System.currentTimeMillis()));
					p.sendMessage("§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬§a§lSkyBlock§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
							+ "\n" + " "
							+ "\n" + "§e/is §7Teleportiere dich zu deiner Insel"
							+ "\n" + "§e/pay <Spieler> <Betrag> §7Überweise einem anderen Spieler Geld" 
							+ "\n" + "§e/visit <Spieler> §7Teleportiere dich zu der Insel eines Spielers" 
							+ "\n" + "§e/coop add <Spieler> §7Füge einen Spieler zu deiner Insel hinzu"
							+ "\n" + " "
							+ "\n" + "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
				}
			});
			thread.start();
		} else {
			sender.sendMessage(m.getPrefix() + "§cDieser Befehl kann nur von einem Spieler ausgeführt werden!");
		}
		return false;
	}
}