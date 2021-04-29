package eu.mcgods.skyblock.listener;

import java.util.UUID;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityTargetEvent;

import eu.mcgods.skyblock.database.PlayerCache;

public class IslandDamageListener implements Listener {

	@EventHandler
	public void onPlayerGetDamageFromPlayer(EntityDamageByEntityEvent e) {

		if (e.getEntity() instanceof Player) {

			try {

				Player p = (Player) e.getEntity();
				Player damager = (Player) e.getDamager();

				World world = p.getWorld();

				if (world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
					if (PlayerCache.getCoopPlayerCacheUUIDs(p.getUniqueId()) != null) {
						if (!PlayerCache.getCoopPlayerCacheUUIDs(p.getUniqueId())
								.contains(damager.getUniqueId().toString())) {
							if (!e.getCause().equals(DamageCause.VOID)) {
								e.setCancelled(true);
							}
						}
					} else {
						if (!e.getCause().equals(DamageCause.VOID)) {
							e.setCancelled(true);
						}
					}
				} else if (world.getName().equalsIgnoreCase(damager.getUniqueId().toString())) {
					if (PlayerCache.getCoopPlayerCacheUUIDs(damager.getUniqueId()) != null) {
						if (!PlayerCache.getCoopPlayerCacheUUIDs(damager.getUniqueId())
								.contains(p.getUniqueId().toString())) {
							if (!e.getCause().equals(DamageCause.VOID)) {
								e.setCancelled(true);
							}
						}
					} else {
						if (!e.getCause().equals(DamageCause.VOID)) {
							e.setCancelled(true);
						}
					}
				}
			} catch (ClassCastException classCastException) {

				Player p = (Player) e.getEntity();
				World world = p.getWorld();

				if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
					if (!e.getCause().equals(DamageCause.VOID)) {
						e.setCancelled(true);
					}
				}
			}
		} else {

			if (e.getDamager() instanceof Player) {
				Player damager = (Player) e.getDamager();
				World world = e.getEntity().getWorld();

				if (!world.getName().equals("Hub") && !world.getName().equals("Wald") && !world.getName().equals("End")
						&& !world.getName().equals("Mine") && !world.getName().equals("Nether")
						&& !world.getName().equals("Quelle")) {
					UUID ownerUUID = UUID.fromString(world.getName());

					if (!world.getName().equalsIgnoreCase(damager.getUniqueId().toString())) {
						if (PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID) != null) {
							if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID)
									.contains(damager.getUniqueId().toString())) {
								e.setCancelled(true);
							}
						} else {
							e.setCancelled(true);
						}
					}
				}
			}
		}

		if (e.getDamager() instanceof Projectile) {
			if (((Projectile) e.getDamager()).getShooter() instanceof Player) {

				Player damager = (Player) (((Projectile) e.getDamager()).getShooter());
				World world = e.getEntity().getWorld();

				if (!world.getName().equals("Hub") && !world.getName().equals("Wald") && !world.getName().equals("End")
						&& !world.getName().equals("Mine") && !world.getName().equals("Nether")
						&& !world.getName().equals("Quelle")) {
					UUID ownerUUID = UUID.fromString(world.getName());

					if (!world.getName().equalsIgnoreCase(damager.getUniqueId().toString())) {
						if (PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID) != null) {
							if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID)
									.contains(damager.getUniqueId().toString())) {
								e.setCancelled(true);
							}
						} else {
							e.setCancelled(true);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onPlayerGetDamage(EntityDamageEvent e) {

		if (e.getEntity() instanceof Player) {

			Player p = (Player) e.getEntity();
			World world = p.getWorld();

			if (!world.getName().equals("Hub") && !world.getName().equals("Wald") && !world.getName().equals("End")
					&& !world.getName().equals("Mine") && !world.getName().equals("Nether")
					&& !world.getName().equals("Quelle")) {
				UUID ownerUUID = UUID.fromString(world.getName());

				if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
					if (PlayerCache.getCoopPlayerCacheNames(ownerUUID) != null) {
						if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
							if (!e.getCause().equals(DamageCause.VOID)) {
								e.setCancelled(true);
							}
						}
					} else {
						if (!e.getCause().equals(DamageCause.VOID)) {
							e.setCancelled(true);
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onTriggerMob(EntityTargetEvent e) {

		if (e.getTarget() instanceof Player) {
			Player p = (Player) e.getTarget();
			World world = p.getWorld();
			if (!world.getName().equals("Hub") && !world.getName().equals("Wald") && !world.getName().equals("End")
					&& !world.getName().equals("Mine") && !world.getName().equals("Nether")
					&& !world.getName().equals("Quelle")) {
				UUID ownerUUID = UUID.fromString(world.getName());
				if (!world.getName().equalsIgnoreCase(p.getUniqueId().toString())) {
					if (PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID) != null) {
						if (!PlayerCache.getCoopPlayerCacheUUIDs(ownerUUID).contains(p.getUniqueId().toString())) {
							e.setCancelled(true);
						}
					} else {
						e.setCancelled(true);
					}
				}
			}
		}
	}
}