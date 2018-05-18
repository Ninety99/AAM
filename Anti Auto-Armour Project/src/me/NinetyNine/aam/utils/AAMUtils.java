package me.NinetyNine.aam.utils;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

import me.NinetyNine.aam.AAM;

public class AAMUtils implements Listener {

	private static final String prefix = "&b[&5AAM&b] ";

	public static ArrayList<Player> checkedPlayers;

	public static HashMap<Player, Integer> time;
	public static HashMap<Player, BukkitRunnable> task;

	public static void sendPlayerMessage(Player player, String message) {
		player.sendMessage(translateAlternateColorCodes('&', prefix + message));
	}

	public static void sendConsoleMessage(String message) {
		Bukkit.getServer().getLogger().info("[AAM] " + message);
	}

	public static void playerCheckAAM(Player player) {
		setItems(player);
		removeArmor(player);
		setCheck(player);
	}

	private static void removeArmor(Player player) {
		checkedPlayers.add(player);
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setBoots(null);
	}

	private static void setItems(Player player) {
		PlayerInventory playerinv = player.getInventory();
		playerinv.setItem(27, getHelmet(player));
		playerinv.setItem(28, getChestplate(player));
		playerinv.setItem(29, getLeggings(player));
		playerinv.setItem(30, getBoots(player));
	}

	private static ItemStack getHelmet(Player player) {
		return player.getInventory().getHelmet();
	}

	private static ItemStack getChestplate(Player player) {
		return player.getInventory().getChestplate();
	}

	private static ItemStack getLeggings(Player player) {
		return player.getInventory().getLeggings();
	}

	private static ItemStack getBoots(Player player) {
		return player.getInventory().getBoots();
	}

	private static void setCheck(Player player) {
		if (!(time.containsKey(player) && task.containsKey(player))) {
			time.put(player, 3);
			task.put(player, new BukkitRunnable() {
				public void run() {
					time.put(player, time.get(player) - 1);
					if (time.get(player) == 0) {
						time.remove(player);
						task.remove(player);
						cancel();
					}
					if (getHelmet(player) != null || getChestplate(player) != null || getLeggings(player) != null
							|| getBoots(player) != null) {
						for (Player all : Bukkit.getServer().getOnlinePlayers()) {
							if (all.hasPermission("gcbanz.ban")) {
								sendPlayerMessage(all, "&c" + player.getName()
										+ " has been detected to be using auto armor! &6(Might be false if the player is crafting/melting, please check)");
							}
						}
					}
				}
			});
			task.get(player).runTaskTimer(AAM.plugin, 20L, 20L);
		}
	}
}
