package eu.mcgods.skyblock.listener;

import org.bukkit.WorldCreator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

import eu.mcgods.skyblock.utils.CleanChunkGenerator;

public class WorldLoadListener implements Listener {
	
	@EventHandler
	public void onWorldLoad(WorldLoadEvent e) {
		WorldCreator worldCreator = (WorldCreator) e.getWorld();
		worldCreator.generator(new CleanChunkGenerator());
	}
}