package elpupas2015.staffchat.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import elpupas2015.staffchat.StaffChat;

public class StaffJoin implements Listener {
	public StaffChat plugin;
	public StaffJoin(StaffChat pl) {
		this.plugin = pl;
	}
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		FileConfiguration config = plugin.getConfig();
		
		String pathjoin = "Messages.Join-and-quit.staff-join-message";
		String format = ChatColor.translateAlternateColorCodes('&', config.getString(pathjoin));
		for(Player staff : Bukkit.getServer().getOnlinePlayers()) {
			Player p = e.getPlayer();
			if(p.hasPermission("staffchat.use")) {
				if(staff.hasPermission("staffchat.use")) {
				staff.sendMessage(format.replaceAll("%player%", p.getName()));
			}
		}
	}
}
	
	@EventHandler
	public void onExit(PlayerQuitEvent e) {
		FileConfiguration config = plugin.getConfig();
		
		String pathleave = "Messages.Join-and-quit.staff-quit-message";
		String format = ChatColor.translateAlternateColorCodes('&', config.getString(pathleave));
		for(Player staff : Bukkit.getServer().getOnlinePlayers()) {
			Player p = e.getPlayer();
			if(p.hasPermission("staffchat.use")) {
				if(staff.hasPermission("staffchat.use")) {
				staff.sendMessage(format.replaceAll("%player%", p.getName()));
				}
			}
		}
	}
}
