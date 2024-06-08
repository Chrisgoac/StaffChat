package elpupas2015.staffchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import elpupas2015.staffchat.StaffChat;

public class Vanish implements CommandExecutor {
	
	public StaffChat plugin;

	public Vanish(StaffChat pl) {
		this.plugin = pl;
	}

	
	private ItemStack createItem(Material type, int amount, String name, int lugarinv, Player pl, int i) {
    	PlayerInventory inv = pl.getInventory();
    	ItemStack is = new ItemStack(type, amount, (short) i);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        is.setItemMeta(im);
        inv.setItem(lugarinv, is);
        return is;
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage("§6§l[§e§lStaffChat§6§l] §cYou must be a player to execute this command.");
		}else {
			Player p = (Player) sender;
			FileConfiguration config = plugin.getConfig();
			String pathactivated = "Messages.StaffMode.vanish-activated";
			String activated = ChatColor.translateAlternateColorCodes('&', config.getString(pathactivated));
			String pathdeactivated = "Messages.StaffMode.vanish-disabled";
			String deactivated = ChatColor.translateAlternateColorCodes('&', config.getString(pathdeactivated));
			String pathjoin = "Messages.StaffMode.vanish-join";
			String join = ChatColor.translateAlternateColorCodes('&', config.getString(pathjoin));
			String pathleave = "Messages.StaffMode.vanish-leave";
			String leave = ChatColor.translateAlternateColorCodes('&', config.getString(pathleave));
			if(p.hasPermission("staff.vanish")) {
				if(StaffChat.InVanish.contains(p)) {
					StaffChat.InVanish.remove(p);
					p.sendMessage(deactivated);
					for(Player pl : Bukkit.getServer().getOnlinePlayers()) {
						pl.showPlayer(p);
					}
					if(StaffChat.InStaff.contains(p)) {
						
					}
					Bukkit.broadcastMessage(join.replaceAll("%player%", p.getName()));
				}else {
					StaffChat.InVanish.add(p);
					p.sendMessage(activated);
					for(Player pl : Bukkit.getServer().getOnlinePlayers()) {
						pl.hidePlayer(p);
					}
					Bukkit.broadcastMessage(leave.replaceAll("%player%", p.getName()));
				}
			}else {
				p.sendMessage("§cYou don't have permissions §6(§7staff.vanish§6)");
			}
			
		}
		return false;
	}

}
