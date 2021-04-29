package eu.mcgods.skyblock.listener;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;

import eu.mcgods.skyblock.database.PlayerCache;

public class IslandBuildListener implements Listener {

	@EventHandler
	public void PlaceBlockOnIsland(BlockPlaceEvent e) {

		Player p = e.getPlayer();
		World world = p.getWorld();
		Block checkBlock = e.getBlockAgainst();
		UUID ownerUUID = UUID.fromString(world.getName());

		Location loc = world.getBlockAt(0, 100, 0).getLocation();

		ArrayList<Location> locs = new ArrayList<Location>();

		if (world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
			if (PlayerCache.getIslandSizeCache(p.getUniqueId()) == 1) {
				for (int x = (loc.getBlockX() - 50); x <= (loc.getBlockX() + 50); x++) {
					for (int z = (loc.getBlockZ() - 50); z <= (loc.getBlockZ() + 50); z++) {
						for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
							Location l = new Location(loc.getWorld(), x, y, z);
							locs.add(l);
						}
					}
				}
			} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 2) {
				for (int x = (loc.getBlockX() - 60); x <= (loc.getBlockX() + 60); x++) {
					for (int z = (loc.getBlockZ() - 60); z <= (loc.getBlockZ() + 60); z++) {
						for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
							Location l = new Location(loc.getWorld(), x, y, z);
							locs.add(l);
						}
					}
				}
			} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 3) {
				for (int x = (loc.getBlockX() - 70); x <= (loc.getBlockX() + 70); x++) {
					for (int z = (loc.getBlockZ() - 70); z <= (loc.getBlockZ() + 70); z++) {
						for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
							Location l = new Location(loc.getWorld(), x, y, z);
							locs.add(l);
						}
					}
				}
			} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 4) {
				for (int x = (loc.getBlockX() - 80); x <= (loc.getBlockX() + 80); x++) {
					for (int z = (loc.getBlockZ() - 80); z <= (loc.getBlockZ() + 80); z++) {
						for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
							Location l = new Location(loc.getWorld(), x, y, z);
							locs.add(l);
						}
					}
				}
			} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 5) {
				for (int x = (loc.getBlockX() - 90); x <= (loc.getBlockX() + 90); x++) {
					for (int z = (loc.getBlockZ() - 90); z <= (loc.getBlockZ() + 90); z++) {
						for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
							Location l = new Location(loc.getWorld(), x, y, z);
							locs.add(l);
						}
					}
				}
			}
		} else {
			if (PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
				if (PlayerCache.getIslandSizeCache(ownerUUID) == 1) {
					for (int x = (loc.getBlockX() - 50); x <= (loc.getBlockX() + 50); x++) {
						for (int z = (loc.getBlockZ() - 50); z <= (loc.getBlockZ() + 50); z++) {
							for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
								Location l = new Location(loc.getWorld(), x, y, z);
								locs.add(l);
							}
						}
					}
				} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 2) {
					for (int x = (loc.getBlockX() - 60); x <= (loc.getBlockX() + 60); x++) {
						for (int z = (loc.getBlockZ() - 60); z <= (loc.getBlockZ() + 60); z++) {
							for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
								Location l = new Location(loc.getWorld(), x, y, z);
								locs.add(l);
							}
						}
					}
				} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 3) {
					for (int x = (loc.getBlockX() - 70); x <= (loc.getBlockX() + 70); x++) {
						for (int z = (loc.getBlockZ() - 70); z <= (loc.getBlockZ() + 70); z++) {
							for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
								Location l = new Location(loc.getWorld(), x, y, z);
								locs.add(l);
							}
						}
					}
				} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 4) {
					for (int x = (loc.getBlockX() - 80); x <= (loc.getBlockX() + 80); x++) {
						for (int z = (loc.getBlockZ() - 80); z <= (loc.getBlockZ() + 80); z++) {
							for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
								Location l = new Location(loc.getWorld(), x, y, z);
								locs.add(l);
							}
						}
					}
				} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 5) {
					for (int x = (loc.getBlockX() - 90); x <= (loc.getBlockX() + 90); x++) {
						for (int z = (loc.getBlockZ() - 90); z <= (loc.getBlockZ() + 90); z++) {
							for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
								Location l = new Location(loc.getWorld(), x, y, z);
								locs.add(l);
							}
						}
					}
				}
			} else {
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
			}
		}
		if (!locs.contains(e.getBlock().getLocation())) {
			e.setCancelled(true);
			p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_HIT, 1.0F, 1.0F);
			p.spawnParticle(Particle.SMOKE_NORMAL, checkBlock.getLocation(), 1);
		}
	}

	@EventHandler
	public void BreakBlockOnIsland(BlockBreakEvent e) {

		Player p = e.getPlayer();
		Block breakedBlock = e.getBlock();
		World world = breakedBlock.getWorld();
		UUID ownerUUID = UUID.fromString(world.getName());

		Location loc = world.getBlockAt(0, 100, 0).getLocation();

		ArrayList<Block> blocks = new ArrayList<Block>();

		if (world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
			if (PlayerCache.getIslandSizeCache(p.getUniqueId()) == 1) {
				for (int x = (loc.getBlockX() - 50); x <= (loc.getBlockX() + 50); x++) {
					for (int z = (loc.getBlockZ() - 50); z <= (loc.getBlockZ() + 50); z++) {
						for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
							Location l = new Location(loc.getWorld(), x, y, z);
							blocks.add(l.getBlock());
						}
					}
				}
			} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 2) {
				for (int x = (loc.getBlockX() - 60); x <= (loc.getBlockX() + 60); x++) {
					for (int z = (loc.getBlockZ() - 60); z <= (loc.getBlockZ() + 60); z++) {
						for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
							Location l = new Location(loc.getWorld(), x, y, z);
							blocks.add(l.getBlock());
						}
					}
				}
			} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 3) {
				for (int x = (loc.getBlockX() - 70); x <= (loc.getBlockX() + 70); x++) {
					for (int z = (loc.getBlockZ() - 70); z <= (loc.getBlockZ() + 70); z++) {
						for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
							Location l = new Location(loc.getWorld(), x, y, z);
							blocks.add(l.getBlock());
						}
					}
				}
			} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 4) {
				for (int x = (loc.getBlockX() - 80); x <= (loc.getBlockX() + 80); x++) {
					for (int z = (loc.getBlockZ() - 80); z <= (loc.getBlockZ() + 80); z++) {
						for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
							Location l = new Location(loc.getWorld(), x, y, z);
							blocks.add(l.getBlock());
						}
					}
				}
			} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 5) {
				for (int x = (loc.getBlockX() - 90); x <= (loc.getBlockX() + 90); x++) {
					for (int z = (loc.getBlockZ() - 90); z <= (loc.getBlockZ() + 90); z++) {
						for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
							Location l = new Location(loc.getWorld(), x, y, z);
							blocks.add(l.getBlock());
						}
					}
				}
			}
		} else {
			if (PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
				if (PlayerCache.getIslandSizeCache(ownerUUID) == 1) {
					for (int x = (loc.getBlockX() - 50); x <= (loc.getBlockX() + 50); x++) {
						for (int z = (loc.getBlockZ() - 50); z <= (loc.getBlockZ() + 50); z++) {
							for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
								Location l = new Location(loc.getWorld(), x, y, z);
								blocks.add(l.getBlock());
							}
						}
					}
				} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 2) {
					for (int x = (loc.getBlockX() - 60); x <= (loc.getBlockX() + 60); x++) {
						for (int z = (loc.getBlockZ() - 60); z <= (loc.getBlockZ() + 60); z++) {
							for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
								Location l = new Location(loc.getWorld(), x, y, z);
								blocks.add(l.getBlock());
							}
						}
					}
				} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 3) {
					for (int x = (loc.getBlockX() - 70); x <= (loc.getBlockX() + 70); x++) {
						for (int z = (loc.getBlockZ() - 70); z <= (loc.getBlockZ() + 70); z++) {
							for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
								Location l = new Location(loc.getWorld(), x, y, z);
								blocks.add(l.getBlock());
							}
						}
					}
				} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 4) {
					for (int x = (loc.getBlockX() - 80); x <= (loc.getBlockX() + 80); x++) {
						for (int z = (loc.getBlockZ() - 80); z <= (loc.getBlockZ() + 80); z++) {
							for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
								Location l = new Location(loc.getWorld(), x, y, z);
								blocks.add(l.getBlock());
							}
						}
					}
				} else if (PlayerCache.getIslandSizeCache(ownerUUID) == 5) {
					for (int x = (loc.getBlockX() - 90); x <= (loc.getBlockX() + 90); x++) {
						for (int z = (loc.getBlockZ() - 90); z <= (loc.getBlockZ() + 90); z++) {
							for (int y = (loc.getBlockY() - 100); y <= (loc.getBlockY() + 100); y++) {
								Location l = new Location(loc.getWorld(), x, y, z);
								blocks.add(l.getBlock());
							}
						}
					}
				}
			} else {
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
			}
		}

		if (!blocks.contains(breakedBlock)) {
			e.setCancelled(true);
			p.playSound(p.getLocation(), Sound.BLOCK_CHAIN_HIT, 1.0F, 1.0F);
			p.spawnParticle(Particle.SMOKE_NORMAL, breakedBlock.getLocation(), 1);
		}
	}

	@EventHandler
	public void placeVehicleOnIsland(VehicleCreateEvent e) {
		try {

			Player p = (Player) e.getVehicle();
			World world = p.getWorld();

			UUID ownerUUID = UUID.fromString(world.getName());

			if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
				try {
					if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
						e.setCancelled(true);
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
					}
				} catch (NullPointerException nullPointerException) {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
				}
			}
		} catch (Exception exception) {
		}
	}

	@EventHandler
	public void breakVehicleOnIsland(VehicleDestroyEvent e) {
		try {

			Player p = (Player) e.getAttacker();
			World world = p.getWorld();

			UUID ownerUUID = UUID.fromString(world.getName());

			if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
				try {
					if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
						e.setCancelled(true);
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
					}
				} catch (NullPointerException nullPointerException) {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
				}
			}
		} catch (Exception exception) {
		}
	}

	@EventHandler
	public void enterVehicleOnIsland(VehicleEnterEvent e) {
		try {

			Player p = (Player) e.getEntered();
			World world = p.getWorld();

			UUID ownerUUID = UUID.fromString(world.getName());

			if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
				try {
					if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
						e.setCancelled(true);
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
					}
				} catch (NullPointerException nullPointerException) {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
				}
			}
		} catch (Exception exception) {
		}
	}

	@EventHandler
	public void frameRotateOnIsland(PlayerInteractEntityEvent e) {

		Player p = e.getPlayer();
		World world = p.getWorld();

		UUID ownerUUID = UUID.fromString(world.getName());

		if (e.getRightClicked().getType() == EntityType.ITEM_FRAME) {
			if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
				if (PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID) != null) {
					if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
						e.setCancelled(true);
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
					}
				} else {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
				}
			}
		}
	}

	@EventHandler
	public void frameDestroyOnIsland(HangingBreakByEntityEvent e) {

		if (e.getRemover() instanceof Player) {

			Player p = (Player) e.getRemover();
			World world = p.getWorld();

			UUID ownerUUID = UUID.fromString(world.getName());

			if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
				if (PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID) != null) {
					if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
						e.setCancelled(true);
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
					}
				} else {
					e.setCancelled(true);
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
				}
			}
		}
	}

	@EventHandler
	public void useSomeThingOnIsland(PlayerInteractEvent e) {
		try {

			Player p = e.getPlayer();
			World world = p.getWorld();

			UUID ownerUUID = UUID.fromString(world.getName());

			if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
				try {
					if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
						e.setCancelled(true);
						if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.LEFT_CLICK_AIR) {
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					}
				} catch (NullPointerException nullPointerException) {
					e.setCancelled(true);
					if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.LEFT_CLICK_AIR) {
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
					}
				}
			}
		} catch (Exception exception) {
		}
	}
}