package elpupas2015.staffchat.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import elpupas2015.staffchat.StaffChat;

public class SendMessageOnJoin implements Listener {
	private StaffChat plugin;
	
	public SendMessageOnJoin(StaffChat pl) {
		this.plugin = pl;
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(p.isOp() && !plugin.getVersion().equals(plugin.getLatestVersion())) {
            p.sendMessage("§6§l[§e§lStaffChat§6§l] §eThere is a new version available §7(" + plugin.getLatestVersion() + ")");
            p.sendMessage("§aYou can download it at: §chttps://www.spigotmc.org/resources/61089/");
		}
	}
}
