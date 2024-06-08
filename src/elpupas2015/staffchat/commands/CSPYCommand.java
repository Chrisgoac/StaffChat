package elpupas2015.staffchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import elpupas2015.staffchat.StaffChat;

public class CSPYCommand implements CommandExecutor {

	public StaffChat plugin;
	
	public CSPYCommand(StaffChat pl) {
		this.plugin = pl;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage("Test");
			return false;
		}else {
			Player p = (Player) sender;
			FileConfiguration config = plugin.getConfig();
			String pathprefix = "Messages.Chat.plugin-prefix";
			String Prefix = ChatColor.translateAlternateColorCodes('&', config.getString(pathprefix));
			String noperm = ChatColor.translateAlternateColorCodes('&', config.getString("Messages.No-perms-messages.nopermission-commandspy"));
			String enabled = ChatColor.translateAlternateColorCodes('&', config.getString("Messages.CommandSpy.enabled"));
			String disabled = ChatColor.translateAlternateColorCodes('&', config.getString("Messages.CommandSpy.disabled"));
			if(args.length == 0) {
				if(p.hasPermission("staff.commandspy")) {
					if(StaffChat.Insc.contains(p)) {
						StaffChat.Insc.remove(p);
						p.sendMessage(disabled);
						return false;
					}else {
						StaffChat.Insc.add(p);
						p.sendMessage(enabled);
						return false;
					}
				}else {
					p.sendMessage(Prefix + " " + noperm);
					return false;
				}
			}
		}
		return false;
	}
}
