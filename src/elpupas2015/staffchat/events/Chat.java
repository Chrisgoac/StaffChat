package elpupas2015.staffchat.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import elpupas2015.staffchat.StaffChat;
import elpupas2015.staffchat.commands.Comandos;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class Chat implements Listener {
	public StaffChat plugin;
	public Chat(StaffChat pl) {
		this.plugin = pl;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		FileConfiguration config = plugin.getConfig();
		
		String texto = "Messages.Chat.staffchat-prefix";
		String texto2 = "Messages.Title.staffchat-title";
		String texto3 = "Messages.Title.staffchat-subtitle";
		String texto4 = "Messages.Chat.staffchat-format";
		String texto5 = "Config.sound";
		Player p = e.getPlayer();
		String format = ChatColor.translateAlternateColorCodes('&', config.getString(texto4));
		String Prefix = ChatColor.translateAlternateColorCodes('&', config.getString(texto));
		String Title = ChatColor.translateAlternateColorCodes('&', config.getString(texto2));
		String SubTitle = ChatColor.translateAlternateColorCodes('&', config.getString(texto3));
		String path2 = "Config.sound-on-staffchat-message";
		String path = "Config.title-on-staffchat-message";
		String msg = e.getMessage();
		if(Comandos.Insc.contains(p)) {
			e.setCancelled(true);
			for(Player staff : Bukkit.getServer().getOnlinePlayers()) {
			if(staff.hasPermission("staffchat.use")) {
				if(config.getString(path).equals("true")) {
					if(config.getString(path2).equals("true")) {
						PacketPlayOutTitle titles = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"" + Title + "\"}"), 5, 15, 5);
						PacketPlayOutTitle subtitles = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\"" + SubTitle + "\"}"), 5, 15, 5);
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(titles);
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitles);
						staff.sendMessage(Prefix + format.replaceAll("%player%", p.getDisplayName()).replaceAll("%msg%", msg));
						staff.playSound(staff.getLocation(), Sound.valueOf(config.getString(texto5)), 2F, 1F);	
					}else {
						PacketPlayOutTitle titles = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"" + Title + "\"}"), 5, 15, 5);
						PacketPlayOutTitle subtitles = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\"" + SubTitle + "\"}"), 5, 15, 5);
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(titles);
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitles);
						staff.sendMessage(Prefix + format.replaceAll("%player%", p.getDisplayName()).replaceAll("%msg%", msg));	
						}
				}else {
					if(config.getString(path2).equals("true")) {
						staff.sendMessage(Prefix + format.replaceAll("%player%", p.getDisplayName()).replaceAll("%msg%", msg));
						staff.playSound(staff.getLocation(), Sound.valueOf(config.getString(texto5)), 2F, 1F);	
					}else {
						staff.sendMessage(Prefix + format.replaceAll("%player%", p.getDisplayName()).replaceAll("%msg%", msg));
					}
					
					}
				}
			
			}
		}
	}
}

