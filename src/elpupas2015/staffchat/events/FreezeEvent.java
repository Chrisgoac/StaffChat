package elpupas2015.staffchat.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import elpupas2015.staffchat.StaffChat;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class FreezeEvent implements Listener {
	
	public StaffChat plugin;
	
	public FreezeEvent(StaffChat pl) {
		this.plugin = pl;
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(StaffChat.Freezed.contains(p)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if(StaffChat.Freezed.contains(p)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof  Player) {
			Player p = (Player) e.getEntity();
			if(StaffChat.Freezed.contains(p)) {
				e.setCancelled(true);	
			}
		}
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		FileConfiguration config = plugin.getConfig();
		String pathwhilefrozen = "Messages.Freeze.left-while-frozen";
		String whilefrozen = ChatColor.translateAlternateColorCodes('&', config.getString(pathwhilefrozen));
		String pathclicktoban = "Messages.Freeze.click-to-ban";
		String clicktoban = ChatColor.translateAlternateColorCodes('&', config.getString(pathclicktoban));
		String pathbancommand = "Messages.Freeze.ban-command";
		String bancommand = ChatColor.translateAlternateColorCodes('&', config.getString(pathbancommand));
		Player p = e.getPlayer();
		for(Player staff : Bukkit.getOnlinePlayers()) {
			if(StaffChat.Freezed.contains(p)) {
				if(staff.hasPermission("staffchat.use")) {
					TextComponent msg = new TextComponent(clicktoban);
					msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, bancommand.replaceAll("%player%", p.getName())));
					staff.sendMessage(whilefrozen.replaceAll("%player%", p.getName()));
					staff.spigot().sendMessage(msg);
				}
			}
		}
	}
}
