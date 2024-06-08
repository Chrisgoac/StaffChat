package elpupas2015.staffchat.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import elpupas2015.staffchat.StaffChat;

public class Comandos implements CommandExecutor{
	
	public StaffChat plugin;
	
	public Comandos(StaffChat pl) {
		this.plugin = pl;
	}
	
	public static ArrayList<Player> Insc = new ArrayList<Player>();
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		FileConfiguration config = plugin.getConfig();
		String pathprefix = "Messages.Chat.plugin-prefix";
		String pathapagado = "Messages.Enabled-and-disabled.disabled-message";
		String pathencendido = "Messages.Enabled-and-disabled.enabled-message";
		String pathnopermuse = "Messages.No-perms-messages.nopermission-use";
		String texto4 = "Messages.Chat.staffchat-format";
		String path2 = "Config.sound-on-staffchat-message";
		String path = "Config.title-on-staffchat-message";
		String texto5 = "Config.sound";
		String texto = "Messages.Chat.staffchat-prefix";
		String texto2 = "Messages.Title.staffchat-title";
		String texto3 = "Messages.Title.staffchat-subtitle";
		String Prefixx = ChatColor.translateAlternateColorCodes('&', config.getString(texto));
		String format = ChatColor.translateAlternateColorCodes('&', config.getString(texto4));
		String Prefix = ChatColor.translateAlternateColorCodes('&', config.getString(pathprefix));
		String Encendido = ChatColor.translateAlternateColorCodes('&', Prefix + " " + config.getString(pathencendido));
		String Apagado = ChatColor.translateAlternateColorCodes('&', Prefix + " " + config.getString(pathapagado));
		String NoPermisoUse = ChatColor.translateAlternateColorCodes('&', Prefix + " " + config.getString(pathnopermuse));
		String Title = ChatColor.translateAlternateColorCodes('&', config.getString(texto2));
		String SubTitle = ChatColor.translateAlternateColorCodes('&', config.getString(texto3));
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("sc")) {
			if(!(sender instanceof Player)) {
				sender.sendMessage(Prefix + " §cYou must be a player to execute this command.");
				return false;
			}
			if(p.hasPermission("staffchat.use")) {
				if(args.length == 0) {
					if(Insc.contains(p)) {
						Insc.remove(p);
						p.sendMessage(Apagado);
						return true;
					} else {
						Insc.add(p);
						p.sendMessage(Encendido);
						return true;
					}		
				}
			}else {	
				p.sendMessage(NoPermisoUse);
			}
			
			if(args.length != 0) {
				String msg = "";
				for (int i = 0; i < args.length; i++) {
					msg = msg + args[i] + " ";
				}
				for(Player stafff : Bukkit.getOnlinePlayers()) {
					if(stafff.hasPermission("staffchat.use")) {
						if(config.getString(path).equals("true")) {
							if(config.getString(path2).equals("true")) {
								p.sendTitle(Title, SubTitle);
								stafff.sendMessage(Prefixx + format.replaceAll("%player%", p.getDisplayName()).replaceAll("%msg%", msg));
								stafff.playSound(stafff.getLocation(), Sound.valueOf(config.getString(texto5)), 2F, 1F);	
							}else {
								p.sendTitle(Title, SubTitle);
								stafff.sendMessage(Prefixx + format.replaceAll("%player%", p.getDisplayName()).replaceAll("%msg%", msg));	
							}
						}else {
							if(config.getString(path2).equals("true")) {
								stafff.sendMessage(Prefixx + format.replaceAll("%player%", p.getDisplayName()).replaceAll("%msg%", msg));
								stafff.playSound(stafff.getLocation(), Sound.valueOf(config.getString(texto5)), 2F, 1F);	
							}else {
								stafff.sendMessage(Prefixx + format.replaceAll("%player%", p.getDisplayName()).replaceAll("%msg%", msg));
							}
						}
	          		}
				}
	      	}
		}
	return false;
	}
	
}
