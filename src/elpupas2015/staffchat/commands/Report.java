package elpupas2015.staffchat.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Report implements CommandExecutor, Listener {

	public void createItem(Material material, int cantidad, String name, Inventory inv, int sitioinv, short id) {
		ItemStack item = new ItemStack(material, cantidad,(short) id);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		item.setItemMeta(meta);
		inv.setItem(sitioinv, item);
	}
	private Map<String, String> map = new HashMap<>();
	
	  @EventHandler
	    public void onInteract(InventoryClickEvent e) {
	    	Player p = (Player) e.getWhoClicked();
	    	Inventory inv = e.getInventory();
	    	String[] title = inv.getName().split(": §7");
            Player target = Bukkit.getServer().getPlayer(title[1]);
            if(inv.getName().equalsIgnoreCase("§bReportar a: §7" + target.getName())) {
            	int slot = e.getSlot();
            	e.setCancelled(true);
	    		if(slot == 0) {
	    			p.sendMessage("§6§l[§b§lReport§6§l] §aHas reportado a §7" + target.getName());
	    			p.closeInventory();
	    			for(Player staff : Bukkit.getServer().getOnlinePlayers()) {
	    				if(staff.hasPermission("staffchat.use")) {
	    					TextComponent msg = new TextComponent("§dTeletransportarse §8» §aClick aquí.");
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§dClick aquí para teletransportarse a: §7" + target.getName()).create()));
							msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + target.getName()));
							
	    					staff.sendMessage("§5§m-----------------------------------");
	    					staff.sendMessage("§dReportador §8» §e" + p.getName());
	    					staff.sendMessage("§dReportado §8» §e" + target.getName());
	    					staff.sendMessage("§dRazón §8» §cKillAura");
	    					staff.spigot().sendMessage(msg);
	    					staff.sendMessage("§5§m-----------------------------------");
	    				}
	    			}
	    		}
	    		if(slot == 1) {
	    			p.sendMessage("§6§l[§b§lReport§6§l] §aHas reportado a §7" + target.getName());
	    			p.closeInventory();
	    			for(Player staff : Bukkit.getServer().getOnlinePlayers()) {
	    				if(staff.hasPermission("staffchat.use")) {
	    					TextComponent msg = new TextComponent("§dTeletransportarse §8» §aClick aquí.");
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§dClick aquí para teletransportarse a: §7" + target.getName()).create()));
							msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + target.getName()));
							
	    					staff.sendMessage("§5§m-----------------------------------");
	    					staff.sendMessage("§dReportador §8» §e" + p.getName());
	    					staff.sendMessage("§dReportado §8» §e" + target.getName());
	    					staff.sendMessage("§dRazón §8» §cFly/Speed");
	    					staff.spigot().sendMessage(msg);
	    					staff.sendMessage("§5§m-----------------------------------");
	    				}
	    			}
	    		}
	    		if(slot == 2) {
	    			p.sendMessage("§6§l[§b§lReport§6§l] §aHas reportado a §7" + target.getName());
	    			p.closeInventory();
	    			for(Player staff : Bukkit.getServer().getOnlinePlayers()) {
	    				if(staff.hasPermission("staffchat.use")) {
	    					TextComponent msg = new TextComponent("§dTeletransportarse §8» §aClick aquí.");
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§dClick aquí para teletransportarse a: §7" + target.getName()).create()));
							msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + target.getName()));
							
	    					staff.sendMessage("§5§m-----------------------------------");
	    					staff.sendMessage("§dReportador §8» §e" + p.getName());
	    					staff.sendMessage("§dReportado §8» §e" + target.getName());
	    					staff.sendMessage("§dRazón §8» §cAutoClicker");
	    					staff.spigot().sendMessage(msg);
	    					staff.sendMessage("§5§m-----------------------------------");
	    				}
	    			}
	    		}
	    		if(slot == 3) {
	    			p.sendMessage("§6§l[§b§lReport§6§l] §aHas reportado a §7" + target.getName());
	    			p.closeInventory();
	    			for(Player staff : Bukkit.getServer().getOnlinePlayers()) {
	    				if(staff.hasPermission("staffchat.use")) {
	    					TextComponent msg = new TextComponent("§dTeletransportarse §8» §aClick aquí.");
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§dClick aquí para teletransportarse a: §7" + target.getName()).create()));
							msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + target.getName()));
							
	    					staff.sendMessage("§5§m-----------------------------------");
	    					staff.sendMessage("§dReportador §8» §e" + p.getName());
	    					staff.sendMessage("§dReportado §8» §e" + target.getName());
	    					staff.sendMessage("§dRazón §8» §cAntiKnockback");
	    					staff.spigot().sendMessage(msg);
	    					staff.sendMessage("§5§m-----------------------------------");
	    				}
	    			}
	    		}
	    		if(slot == 4) {
	    			p.sendMessage("§6§l[§b§lReport§6§l] §aHas reportado a §7" + target.getName());
	    			p.closeInventory();
	    			for(Player staff : Bukkit.getServer().getOnlinePlayers()) {
	    				if(staff.hasPermission("staffchat.use")) {
	    					TextComponent msg = new TextComponent("§dTeletransportarse §8» §aClick aquí.");
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§dClick aquí para teletransportarse a: §7" + target.getName()).create()));
							msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + target.getName()));
							
	    					staff.sendMessage("§5§m-----------------------------------");
	    					staff.sendMessage("§dReportador §8» §e" + p.getName());
	    					staff.sendMessage("§dReportado §8» §e" + target.getName());
	    					staff.sendMessage("§dRazón §8» §cAimBot");
	    					staff.spigot().sendMessage(msg);
	    					staff.sendMessage("§5§m-----------------------------------");
	    				}
	    			}
	    		}
	    		if(slot == 5) {
	    			p.sendMessage("§6§l[§b§lReport§6§l] §aHas reportado a §7" + target.getName());
	    			p.closeInventory();
	    			for(Player staff : Bukkit.getServer().getOnlinePlayers()) {
	    				if(staff.hasPermission("staffchat.use")) {
	    					TextComponent msg = new TextComponent("§dTeletransportarse §8» §aClick aquí.");
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§dClick aquí para teletransportarse a: §7" + target.getName()).create()));
							msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + target.getName()));
							
	    					staff.sendMessage("§5§m-----------------------------------");
	    					staff.sendMessage("§dReportador §8» §e" + p.getName());
	    					staff.sendMessage("§dReportado §8» §e" + target.getName());
	    					staff.sendMessage("§dRazón §8» §cChat");
	    					staff.spigot().sendMessage(msg);
	    					staff.sendMessage("§5§m-----------------------------------");
	    				}
	    			}
	    		}
	    		if(slot == 6) {
	    			p.sendMessage("§6§l[§b§lReport§6§l] §aHas reportado a §7" + target.getName());
	    			p.closeInventory();
	    			for(Player staff : Bukkit.getServer().getOnlinePlayers()) {
	    				if(staff.hasPermission("staffchat.use")) {
	    					TextComponent msg = new TextComponent("§dTeletransportarse §8» §aClick aquí.");
							msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§dClick aquí para teletransportarse a: §7" + target.getName()).create()));
							msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + target.getName()));
							
	    					staff.sendMessage("§5§m-----------------------------------");
	    					staff.sendMessage("§dReportador §8» §e" + p.getName());
	    					staff.sendMessage("§dReportado §8» §e" + target.getName());
	    					staff.sendMessage("§dRazón §8» §cOtros");
	    					staff.spigot().sendMessage(msg);
	    					staff.sendMessage("§5§m-----------------------------------");
	    				}
	    			}
	    		}
	    		if(slot == 8) {
	    			p.sendMessage("§6§l[§b§lReport§6§l] §cHas cancelado tu reporte.");
	    			p.closeInventory();
	    		}
            }
	    	
	    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage("§cYou must be a player to execute this command.");
		}else {
			Player p = (Player) sender;
			if(args.length != 1) {
				p.sendMessage("§6§l[§b§lReport§6§l] §cUso incorrecto, usa §7/report <Jugador>");
			}else {
				Player target = Bukkit.getPlayer(args[0]);
				if(target == null) {
					p.sendMessage("§6§l[§b§lReport§6§l] §cEl jugador tiene que estar conectado.");
				}else {
					map.put(p.getName(), target.getName());
					Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', "&bReportar a: &7") + target.getName());
					createItem(Material.DIAMOND_SWORD, 1, "&cKillAura", inv, 0,(short) 0);
					createItem(Material.FEATHER, 1, "&aFly/Speed", inv, 1,(short) 0);
					createItem(Material.STONE_BUTTON, 1, "&aAutoClicker", inv, 2,(short) 0);
					createItem(Material.ANVIL, 1, "&3AntiKnockback", inv, 3,(short) 0);
					createItem(Material.COMPASS, 1, "&bAimbot", inv, 4,(short) 0);
					createItem(Material.NAME_TAG, 1, "&5Chat", inv, 5,(short) 0);
					createItem(Material.PAPER, 1, "&6Otros", inv, 6,(short) 0);
					createItem(Material.REDSTONE_BLOCK, 1, "&cCancelar reporte", inv, 8,(short) 0);
					p.openInventory(inv);
				}
			}
		}
		return false;
	}
}
