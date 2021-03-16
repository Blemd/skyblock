package eu.mcgods.skyblock.listener;

import java.util.UUID;

import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;

import eu.mcgods.skyblock.database.PlayerCache;
import eu.mcgods.skyblock.main.SkyBlock;

public class CoopProtectionListener implements Listener {

	private SkyBlock m = SkyBlock.getInstance();

	@EventHandler
	public void onTryToPlace(BlockPlaceEvent e) {
		try {

			Player p = e.getPlayer();
			World world = p.getWorld();

			UUID ownerUUID = UUID.fromString(world.getName());

			if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
				try {
					if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
						e.setCancelled(true);
						p.sendMessage(m.getPrefix() + "Du kannst hier nichts platzieren.");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
					}
				} catch (NullPointerException nullPointerException) {
					e.setCancelled(true);
					p.sendMessage(m.getPrefix() + "Du kannst hier nichts platzieren.");
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
				}
			}
		} catch (Exception exception) {
		}
	}

	@EventHandler
	public void onTryToBreak(BlockBreakEvent e) {
		try {

			Player p = e.getPlayer();
			World world = p.getWorld();

			UUID ownerUUID = UUID.fromString(world.getName());

			if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
				try {
					if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
						e.setCancelled(true);
						p.sendMessage(m.getPrefix() + "Du kannst hier nichts abbauen.");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
					}
				} catch (NullPointerException nullPointerException) {
					e.setCancelled(true);
					p.sendMessage(m.getPrefix() + "Du kannst hier nichts abbauen.");
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
				}
			}
		} catch (Exception exception) {
		}
	}

	@EventHandler
	public void onTryToPlaceVehicle(VehicleCreateEvent e) {
		try {

			Player p = (Player) e.getVehicle();
			World world = p.getWorld();

			UUID ownerUUID = UUID.fromString(world.getName());

			if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
				try {
					if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
						e.setCancelled(true);
						p.sendMessage(m.getPrefix() + "Du kannst hier nichts platzieren.");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
					}
				} catch (NullPointerException nullPointerException) {
					e.setCancelled(true);
					p.sendMessage(m.getPrefix() + "Du kannst hier nichts platzieren.");
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
				}
			}
		} catch (Exception exception) {
		}
	}

	@EventHandler
	public void onTryToBreakVehicle(VehicleDestroyEvent e) {
		try {

			Player p = (Player) e.getAttacker();
			World world = p.getWorld();

			UUID ownerUUID = UUID.fromString(world.getName());

			if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
				try {
					if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
						e.setCancelled(true);
						p.sendMessage(m.getPrefix() + "Du kannst hier nichts abbauen.");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
					}
				} catch (NullPointerException nullPointerException) {
					e.setCancelled(true);
					p.sendMessage(m.getPrefix() + "Du kannst hier nichts abbauen.");
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
				}
			}
		} catch (Exception exception) {
		}
	}

	@EventHandler
	public void onTryToEnterVehicle(VehicleEnterEvent e) {
		try {

			Player p = (Player) e.getEntered();
			World world = p.getWorld();

			UUID ownerUUID = UUID.fromString(world.getName());

			if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
				try {
					if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
						e.setCancelled(true);
						p.sendMessage(m.getPrefix() + "Du kannst hier nicht einsteigen");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
					}
				} catch (NullPointerException nullPointerException) {
					e.setCancelled(true);
					p.sendMessage(m.getPrefix() + "Du kannst hier nicht einsteigen.");
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
				}
			}
		} catch (Exception exception) {
		}
	}

	@EventHandler
	public void FrameRotate(PlayerInteractEntityEvent e) {
		try {
			Player p = e.getPlayer();
			World world = p.getWorld();

			UUID ownerUUID = UUID.fromString(world.getName());

			if (e.getRightClicked().getType().equals(EntityType.ITEM_FRAME)) {
				if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
					try {
						if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
							e.setCancelled(true);
							p.sendMessage(m.getPrefix() + "Du kannst das hier nicht.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					} catch (NullPointerException nullPointerException) {
						e.setCancelled(true);
						p.sendMessage(m.getPrefix() + "Du kannst das hier nicht.");
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
					}
				}
			}
		} catch (Exception exception) {
		}
	}

	@EventHandler
	public void FrameDestroy(EntityDamageByEntityEvent e) {
		try {
			Player p = (Player) e.getDamager();
			World world = p.getWorld();

			UUID ownerUUID = UUID.fromString(world.getName());

			if (e.getEntity() instanceof ItemFrame) {
				if (e.getDamager() instanceof Player) {
					if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
						try {
							if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
								e.setCancelled(true);
								p.sendMessage(m.getPrefix() + "Du kannst das hier nicht.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						} catch (NullPointerException nullPointerException) {
							e.setCancelled(true);
							p.sendMessage(m.getPrefix() + "Du kannst das hier nicht.");
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
						}
					}
				}
				if (e.getDamager() instanceof Projectile) {
					if (((Projectile) e.getDamager()).getShooter() instanceof Player) {
						if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
							try {
								if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID)
										.contains(p.getUniqueId().toString())) {
									e.setCancelled(true);
									p.sendMessage(m.getPrefix() + "Du kannst das hier nicht.");
									p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
								}
							} catch (NullPointerException nullPointerException) {
								e.setCancelled(true);
								p.sendMessage(m.getPrefix() + "Du kannst das hier nicht.");
								p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1.0F, 1.0F);
							}
						}
					}
				}
			}
		} catch (Exception exception) {
		}
	}

	@EventHandler
	public void onPlayerGetDamage(EntityDamageEvent e) {
		try {

			Player p = (Player) e.getEntity();
			World world = p.getWorld();

			UUID ownerUUID = UUID.fromString(world.getName());

			if (e.getEntity() instanceof Player) {
				if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
					if (!(p.getLocation().getBlockY() <= 0)) {
						try {
							if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
								e.setCancelled(true);
							}
						} catch (NullPointerException nullPointerException) {
							e.setCancelled(true);
						}
					}
				}
			}
		} catch (Exception exception) {
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		e.setDeathMessage(null);
	}
}