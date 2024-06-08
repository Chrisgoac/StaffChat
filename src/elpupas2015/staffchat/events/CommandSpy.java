package elpupas2015.staffchat.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import elpupas2015.staffchat.StaffChat;

public class CommandSpy implements Listener {
	
	public StaffChat plugin;
	
	public CommandSpy(StaffChat pl) {
		this.plugin = pl;
	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e) {
		FileConfiguration config = plugin.getConfig();
		Player p = e.getPlayer();
		String cmd = e.getMessage();
		String format = ChatColor.translateAlternateColorCodes('&', config.getString("Messages.CommandSpy.format"));
		for(Player staff : Bukkit.getServer().getOnlinePlayers()) {
			if(StaffChat.Insc.contains(staff)) {
				staff.sendMessage(format.replaceAll("%player%", p.getName()).replaceAll("%cmd%", cmd));
			}
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		if(StaffChat.Insc.contains(p)) {
			StaffChat.Insc.remove(p);
		}
	}
}
