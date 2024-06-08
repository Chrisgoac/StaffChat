package elpupas2015.staffchat.guis;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import elpupas2015.staffchat.StaffChat;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class StaffList implements Listener {
	
    public StaffChat plugin;
	
	public StaffList(StaffChat pl) {
		this.plugin = pl;
	}

	@SuppressWarnings("deprecation")
	public void createInventoryPlayers(Player p) {
		FileConfiguration config = plugin.getConfig();
		String StaffListName = "Messages.StaffOnlineGUI.stafflist-inventory-name";
		String pathstafflistrank = "Messages.StaffOnlineGUI.stafflist-rank";
		String stafflistrank = ChatColor.translateAlternateColorCodes('&', config.getString(pathstafflistrank));
		Inventory inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', config.getString(StaffListName)));
		int i=0;
		for(Player j : Bukkit.getOnlinePlayers()) {
			if(j.hasPermission("staffchat.use")) {
				ItemStack skull = new ItemStack(397,1,(short) 3);
				SkullMeta meta = (SkullMeta) skull.getItemMeta();
				meta.setOwner(j.getName());
				String StaffName = "Messages.StaffOnlineGUI.stafflist-name";
				meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString(StaffName).replaceAll("%player%", j.getName())));
				ArrayList<String> lore = new ArrayList<String>();
				String prefix = PermissionsEx.getUser(j).getGroups()[0].getPrefix();
				lore.add(stafflistrank.replaceAll("%rank%", ChatColor.translateAlternateColorCodes('&', prefix)));
				meta.setLore(lore);
				skull.setItemMeta(meta);
				inv.setItem(i, skull);
				i++;
				if(i==18) {
					break;
				}
			}
		}
		p.openInventory(inv);
		return;
	}
	
	@EventHandler
	public void clicInventoryPlayers(InventoryClickEvent e) {
		FileConfiguration config = plugin.getConfig();
		String StaffListName = "Messages.StaffOnlineGUI.stafflist-inventory-name";
		String title = ChatColor.translateAlternateColorCodes('&', config.getString(StaffListName));
		String titlem = ChatColor.stripColor(title);
		if(!ChatColor.stripColor(e.getInventory().getName()).equals(titlem)) {
			return;
		}else {
			if(e.getCurrentItem() == null) {
				e.setCancelled(true);
				return;
			}
			if(e.getCurrentItem().getType() == Material.AIR || e.getSlotType() == null) {
				e.setCancelled(true);
				return;
			}else {
				//Player p = (Player) e.getWhoClicked();
				e.setCancelled(true);
				return;
			}
		}
	}
}
