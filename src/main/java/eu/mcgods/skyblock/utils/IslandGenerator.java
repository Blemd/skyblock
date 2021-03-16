package eu.mcgods.skyblock.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;

import eu.mcgods.skyblock.main.SkyBlock;

public class IslandGenerator {

	private static SkyBlock m = SkyBlock.getInstance();
	
	@SuppressWarnings("deprecation")
	public static World generateIsland(String worldName, Difficulty difficulty, Double islandSize) throws IOException, WorldEditException{

		WorldCreator worldCreator = WorldCreator.name(worldName);
		worldCreator.generator(new CleanChunkGenerator());
		worldCreator.generateStructures(false);
		
		World playerWorld = Bukkit.createWorld(worldCreator);
		
		playerWorld.setSpawnLocation(0, 100, 0);
		playerWorld.setPVP(false);
		playerWorld.setDifficulty(difficulty);
		playerWorld.getWorldBorder().setCenter(playerWorld.getSpawnLocation());
		playerWorld.getWorldBorder().setSize(islandSize);
		playerWorld.setGameRule(GameRule.KEEP_INVENTORY, true);
		
		int locX = playerWorld.getSpawnLocation().getBlockX();
		int locY = playerWorld.getSpawnLocation().getBlockY();
		int locZ = playerWorld.getSpawnLocation().getBlockZ();
		
		File schematic = new File("plugins/WorldEdit/schematics/island.schematic");
		
		Clipboard clipboard = null;
		ClipboardFormat format = ClipboardFormats.findByFile(schematic);
		
		try (ClipboardReader reader = format.getReader(new FileInputStream(schematic))) {
			clipboard = reader.read();
		}
		
		try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(playerWorld), -1)) {
			Operation operation = new ClipboardHolder(clipboard).createPaste(editSession).to(BlockVector3.at(locX, locY, locZ)).build();
			Operations.complete(operation);
		}
		return playerWorld;
	}
	
	@SuppressWarnings("deprecation")
	public static void setIslandWarpPoint(World world, UUID uuid, Integer locX, Integer locY, Integer locZ) {
		try {

			Player p = Bukkit.getPlayer(uuid);

			if (world.getName().equalsIgnoreCase(uuid.toString())) {
				if (!p.isFlying() && !p.isSneaking() && p.isOnGround()) {
					world.setSpawnLocation(locX, locY, locZ);
					p.sendMessage(m.getPrefix() + "Dein Insel Warp-Punkt, wurde auf X:§e" + locX + "§7Y:§e" + locY + "§7Z:§e" + locZ + " §7gesetzt.");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
				} else {
					p.sendMessage(m.getPrefix() + "Du darfst dabei nicht in der Luft sein.");
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
				}
			} else {
				p.sendMessage(m.getPrefix() + "Dafür musst du auf deiner eigenen Insel sein.");
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
			}
		} catch (Exception exception) {
		}
	}
}