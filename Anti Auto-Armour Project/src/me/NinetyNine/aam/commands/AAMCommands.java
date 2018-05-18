package me.NinetyNine.aam.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.NinetyNine.aam.utils.AAMUtils;

public class AAMCommands implements Listener, CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("aam")) {
				if (player.hasPermission("gcbanz.ban")) {
					if (args.length == 0) {
						AAMUtils.sendPlayerMessage(player, "&cUsage: /aam <player>");
						return true;
					}
					@SuppressWarnings("deprecation")
					Player target = Bukkit.getPlayer(args[0]);
					if (target.isOnline()) {
						AAMUtils.playerCheckAAM(target);
						AAMUtils.sendPlayerMessage(player, "&9" + target.getName() + " &cis being checked!");
						return true;
					} else {
						AAMUtils.sendPlayerMessage(player,
								"&c" + target.getName() + " is not online//player not found!");
						return true;
					}
				} else {
					player.sendMessage(ChatColor.RED + "You do not have permissions to execute this command!");
					return true;
				}
			}
		} else {
			sender.sendMessage(ChatColor.RED + "You must be a player!");
			return true;
		}
		return true;
	}

}
