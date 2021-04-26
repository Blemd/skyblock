package eu.mcgods.skyblock.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import eu.mcgods.skyblock.main.SkyBlock;

public class LocationManager {

	private static File file = new File(SkyBlock.getInstance().getDataFolder(), "locs.yml");
	private static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

	public static void saveCfg() {
		try {
			cfg.save(file);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static void setLocation(String name, Location loc) {
		cfg.createSection(name);
		cfg.getConfigurationSection(name).set("World", loc.getWorld().getName());
		cfg.getConfigurationSection(name).set("X", loc.getX());
		cfg.getConfigurationSection(name).set("Y", loc.getY());
		cfg.getConfigurationSection(name).set("Z", loc.getZ());
		cfg.getConfigurationSection(name).set("Yaw", loc.getYaw());
		cfg.getConfigurationSection(name).set("Pitch", loc.getPitch());
		cfg.getConfigurationSection(name).set("Direction", loc.getDirection());

		saveCfg();

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}

	public static Location getLocation(String name) {
		World world = Bukkit.getWorld(cfg.getConfigurationSection(name).getString("World"));
		double x = cfg.getConfigurationSection(name).getDouble("X");
		double y = cfg.getConfigurationSection(name).getDouble("Y");
		double z = cfg.getConfigurationSection(name).getDouble("Z");
		
		Location loc = new Location(world, x, y, z);
		loc.setYaw(cfg.getConfigurationSection(name).getInt("Yaw"));
		loc.setPitch(cfg.getConfigurationSection(name).getInt("Pitch"));
		loc.setDirection(cfg.getConfigurationSection(name).getVector("Direction"));
		return loc;
	}
}