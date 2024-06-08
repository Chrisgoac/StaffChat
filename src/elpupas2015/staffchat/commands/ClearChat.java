package elpupas2015.staffchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import elpupas2015.staffchat.StaffChat;

public class ClearChat implements CommandExecutor {
	
	public StaffChat plugin;
	
	public ClearChat(StaffChat pl) {
		this.plugin = pl;
	}
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration config = plugin.getConfig();
		String pathprefix = "Messages.Chat.plugin-prefix";
		String pathcc = "Messages.ClearChat.bypassed-message";
		String pathnpcc = "Messages.No-perms-messages.nopermission-clearchat";
		String Prefix = ChatColor.translateAlternateColorCodes('&', config.getString(pathprefix));
		String NoPermCC = ChatColor.translateAlternateColorCodes('&', Prefix + " " + config.getString(pathnpcc));
		String MessageClearChat = ChatColor.translateAlternateColorCodes('&', config.getString(pathcc));
		Player p = (Player) sender;
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Prefix + " §cYou must be a player to execute this command.");
		}
		if(args.length == 0) {
		if(p.hasPermission("staff.clearchat")) {
			for(Player j : Bukkit.getOnlinePlayers()) {
				if(!j.hasPermission("staff.clearchat.bypass")) {
					int i=0;
					while(i < 150) {
						j.sendMessage("");
						i++;
					}
				}else {
					j.sendMessage(MessageClearChat.replaceAll("%player%", p.getName()));
				}
			}
		}else {
			p.sendMessage(NoPermCC);
		}
	}
		return false;
	}

}
