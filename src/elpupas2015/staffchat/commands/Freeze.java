package elpupas2015.staffchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import elpupas2015.staffchat.StaffChat;

public class Freeze implements CommandExecutor {
	
	public StaffChat plugin;
	
	public Freeze(StaffChat pl) {
		this.plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration config = plugin.getConfig();
		String pathprefix = "Messages.Chat.plugin-prefix";
		String Prefix = ChatColor.translateAlternateColorCodes('&', config.getString(pathprefix));
		String pathnocorrect = "Messages.Freeze.no-correct-format";
		String nocorrect = ChatColor.translateAlternateColorCodes('&', config.getString(pathnocorrect));
		String pathnoperm = "Messages.No-perms-messages.nopermission-freeze";
		String noperm = ChatColor.translateAlternateColorCodes('&', config.getString(pathnoperm));
		String pathnoconnect = "Messages.Freeze.no-connected";
		String noconnect = ChatColor.translateAlternateColorCodes('&', config.getString(pathnoconnect));
		String pathtargetfreezed = "Messages.Freeze.target-freezed";
		String targetfreezed = ChatColor.translateAlternateColorCodes('&', config.getString(pathtargetfreezed));
		String pathfreezedtostaff = "Messages.Freeze.staff-freezing";
		String freezedtostaff = ChatColor.translateAlternateColorCodes('&', config.getString(pathfreezedtostaff));
		String pathtargetunfreezed = "Messages.Freeze.target-unfreezed";
		String targetunfreezed = ChatColor.translateAlternateColorCodes('&', config.getString(pathtargetunfreezed));
		String pathunfreezedtostaff = "Messages.Freeze.staff-unfreezing";
		String unfreezedtostaff = ChatColor.translateAlternateColorCodes('&', config.getString(pathunfreezedtostaff));
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Prefix + " §cYou must be a player to execute this command.");
		}else {
			Player p = (Player) sender;
			if(p.hasPermission("staff.freeze")) {
				if(args.length == 0) {
					p.sendMessage(nocorrect);
				}else {
					if(args.length == 1) {
						Player target = Bukkit.getPlayer(args[0]);
					    if (target == null) {
					    	p.sendMessage(noconnect.replaceAll("%player%", args[0]));
					    }else{
					    	if(StaffChat.Freezed.contains(target)) {
					    		StaffChat.Freezed.remove(target);
								target.sendMessage(targetunfreezed.replaceAll("%staff%", p.getName()));
								p.sendMessage(unfreezedtostaff.replaceAll("%player%", target.getName()));
					    	}else {
					    		StaffChat.Freezed.add(target);
								target.sendMessage(targetfreezed.replaceAll("%staff%", p.getName()));
								p.sendMessage(freezedtostaff.replaceAll("%player%", target.getName()));
					    	}	
						}
					}else {
						p.sendMessage(nocorrect);
					}
				}
			}else {
				p.sendMessage(noperm);
			}
			return false;
		}
		return false;
	}

}
