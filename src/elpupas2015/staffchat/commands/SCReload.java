package elpupas2015.staffchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import elpupas2015.staffchat.StaffChat;

public class SCReload implements CommandExecutor {
	
	public StaffChat plugin;
	
	public SCReload(StaffChat pl) {
		this.plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration config = plugin.getConfig();
		String pathprefix = "Messages.Chat.plugin-prefix";
		String pathnopermreload = "Messages.No-perms-messages.nopermission-reload";
		String Prefix = ChatColor.translateAlternateColorCodes('&', config.getString(pathprefix));
		String NoPermisoReload = ChatColor.translateAlternateColorCodes('&', Prefix + " " + config.getString(pathnopermreload));
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Prefix + " §aPlugin reloaded correctly.");
			plugin.reloadConfig();
			return false;
		}else {
			Player p = (Player) sender;
			if(p.hasPermission("staffchat.reload")) {
				p.sendMessage(Prefix + " §aPlugin reloaded correctly.");
				plugin.reloadConfig();
				return true;
			}else {
				p.sendMessage(NoPermisoReload);
			}
		}
		return false;
	}

}
