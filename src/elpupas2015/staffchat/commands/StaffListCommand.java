package elpupas2015.staffchat.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import elpupas2015.staffchat.StaffChat;
import elpupas2015.staffchat.guis.StaffList;

public class StaffListCommand implements CommandExecutor {
	
    public StaffChat plugin;
	
	public StaffListCommand(StaffChat pl) {
		this.plugin = pl;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration config = plugin.getConfig();
		String pathprefix = "Messages.Chat.plugin-prefix";
		String Prefix = ChatColor.translateAlternateColorCodes('&', config.getString(pathprefix));
		if(!(sender instanceof Player)) {
			sender.sendMessage(Prefix + " §cYou must be a player to execute this command.");
			return false;
		}
		
		Player p = (Player) sender;
		if(p.hasPermission("staff.list") || p.isOp()) {
			StaffList inv = new StaffList(plugin);
			inv.createInventoryPlayers(p);
		}else {
			String List = "Messages.No-perms-messages.nopermission-stafflist";
			String NoPermList = ChatColor.translateAlternateColorCodes('&', Prefix + " " + config.getString(List));
			p.sendMessage(NoPermList);
		}
		return false;
	
	}
}
