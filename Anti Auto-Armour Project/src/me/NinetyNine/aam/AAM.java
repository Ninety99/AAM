package me.NinetyNine.aam;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.NinetyNine.aam.commands.AAMCommands;
import me.NinetyNine.aam.utils.AAMUtils;

public class AAM extends JavaPlugin {

	public static AAM plugin;

	@Override
	public void onEnable() {
		plugin = this;

		registerListeners();
		registerStatic();
		registerCommand();
		AAMUtils.sendConsoleMessage("Enabled!");
	}

	@Override
	public void onDisable() {
		AAMUtils.sendConsoleMessage("Disabled!");
	}

	private void registerListeners() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new AAMCommands(), this);
		pm.registerEvents(new AAMUtils(), this);
	}

	private void registerStatic() {
		AAMUtils.checkedPlayers = new ArrayList<Player>();
		AAMUtils.time = new HashMap<Player, Integer>();
		AAMUtils.task = new HashMap<Player, BukkitRunnable>();
	}

	private void registerCommand() {
		getCommand("aam").setExecutor(new AAMCommands());
	}
}