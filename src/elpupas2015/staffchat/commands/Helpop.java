package elpupas2015.staffchat.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import elpupas2015.staffchat.StaffChat;

public class Helpop implements CommandExecutor {

	public StaffChat plugin;
	
	public Helpop(StaffChat pl) {
		this.plugin = pl;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FileConfiguration config = plugin.getConfig();
		String pathprefix = "Messages.Chat.plugin-prefix";
		String pathnmsge = "Messages.Helpop.no-message-error";
		String Prefix = ChatColor.translateAlternateColorCodes('&', config.getString(pathprefix));
		String nmsge = ChatColor.translateAlternateColorCodes('&', config.getString(pathnmsge));
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(Prefix + " §cYou must be a player to execute this command.");
		}else {
			Player p = (Player) sender;
			if(args.length == 0) {
				p.sendMessage(nmsge);
			}else {
				if(args.length != 0) {
			        String msg = "";
			        for(int i = 0; i < args.length; i++) {
			        	msg = msg + args[i] + " ";
			        }
			        List<String> message = config.getStringList("Messages.Helpop.player-message");
		    		for(int i=0; i<message.size(); i++) {
		    			String text = message.get(i);
		    			p.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
		    		}
		    		for(Player sc : Bukkit.getServer().getOnlinePlayers()) {
			        	if(sc.hasPermission("staff.helpop")) {
			        		List<String> message2 = config.getStringList("Messages.Helpop.staff-message");
			    			for(int i=0;i<message2.size();i++) {
			    				String text = message2.get(i);
			    				sc.sendMessage(ChatColor.translateAlternateColorCodes('&', text.replaceAll("%player%", p.getName()).replaceAll("%msg%", msg)));
			    			}
			        	}
			        }
				}
			}
		}
	return false;
	}
}
