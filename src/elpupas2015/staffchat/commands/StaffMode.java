package elpupas2015.staffchat.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import elpupas2015.staffchat.StaffChat;

public class StaffMode implements CommandExecutor, Listener {

	public StaffChat plugin;

	public StaffMode(StaffChat pl) {
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
		FileConfiguration config = plugin.getConfig();
		String pathnoperm = "Messages.No-perms-messages.nopermission-staffmode";
		String noperm = ChatColor.translateAlternateColorCodes('&', config.getString(pathnoperm));
		String pathactivated = "Messages.StaffMode.staff-activated";
		String activated = ChatColor.translateAlternateColorCodes('&', config.getString(pathactivated));
		String pathdeactivated = "Messages.StaffMode.staff-disabled";
		String deactivated = ChatColor.translateAlternateColorCodes('&', config.getString(pathdeactivated));
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage("§6§l[§e§lStaffChat§6§l] §cYou must be a player to execute this command.");
		}else {
			Player p = (Player) sender;
			if(p.hasPermission("staff.staffmode")) {
				if(StaffChat.InStaff.contains(p)) {
					StaffChat.InStaff.remove(p);
					p.sendMessage(deactivated);
					p.getInventory().clear();
				}else {
					StaffChat.InStaff.add(p);
					p.getInventory().clear();
					p.sendMessage(activated);
					p.setGameMode(GameMode.CREATIVE);
					createItem(Material.WOOD_AXE, 1, "§6§lWorldEdit", 0, p, 0);
					createItem(Material.ENDER_PEARL, 1, "§6§lJugador Aleatorio", 1, p, 0);
					createItem(Material.ICE, 1, "§6§lCongelar", 4, p, 0);
					if(StaffChat.InVanish.contains(p)) {
						createItem(Material.TORCH, 1, "§6§lInvisibilidad §a(Activada)", 8, p, 8);
					}else {
						createItem(Material.REDSTONE_TORCH_ON, 1, "§6§lInvisibilidad §c(Desactivada)", 8, p, 8);
					}
					
				}
			}else {
				p.sendMessage(noperm);
			}
		}
		return false;
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		FileConfiguration config = plugin.getConfig();
		String pathtp = "Messages.StaffMode.tp-random-player";
		String tp = ChatColor.translateAlternateColorCodes('&', config.getString(pathtp));
		Random random = new Random();
		int rnd = random.nextInt(Bukkit.getOnlinePlayers().size());
		Player randomPlayer = (Player) Bukkit.getOnlinePlayers().toArray()[rnd];
		Player p = e.getPlayer();
		Action a = e.getAction();
		if ((a == Action.PHYSICAL) || (e.getItem() == null)) {
			return;
		}else {
			if(StaffChat.InStaff.contains(p)) {
				if(e.getItem().getType() == Material.ENDER_PEARL) {
					p.sendMessage(tp.replaceAll("%player%", randomPlayer.getName()));
					p.teleport(randomPlayer);
				}
			}else {
				return;
			}
		}
	}
	
	@EventHandler
	public void onClickPlayer(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if (p.getItemInHand() == null) {
			return;
		}else {
			Entity en = e.getRightClicked();
			if(StaffChat.InStaff.contains(p)) {
				if(p.getItemInHand() != null && p.getItemInHand().getType() == Material.ICE) {
					if(en instanceof Player) {
						p.performCommand("freeze " + en.getName());
					}else {
						return;
					}
				}
			}else {
				return;
			}
		}
	}
	
	@EventHandler
	public void onClickPlayerVanish(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action a = e.getAction();
		if ((a == Action.PHYSICAL) || (e.getItem() == null)) {
			return;
		}else {
			if(StaffChat.InStaff.contains(p)) {
				if(e.getItem().getType() == Material.REDSTONE_TORCH_ON) {
					createItem(Material.TORCH, 1, "§6§lInvisibilidad §a(Activada)", 8, p, 0);
					p.performCommand("vanish");
				}
				if(e.getItem().getType() == Material.TORCH) {
					createItem(Material.REDSTONE_TORCH_ON, 1, "§6§lInvisibilidad §c(Desactivada)", 8, p, 0);
					p.performCommand("vanish");	
				}
			}else {
				return;
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if(StaffChat.InStaff.contains(p)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if(StaffChat.InStaff.contains(p)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if(StaffChat.InStaff.contains(p)) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onChangeWorldHide(PlayerChangedWorldEvent e) {
		Player p = e.getPlayer();
		if(StaffChat.InVanish.contains(p)) {
			for(Player pl : Bukkit.getServer().getOnlinePlayers()) {
				pl.showPlayer(p);
				Bukkit.getScheduler ().runTaskLater (plugin, () -> pl.hidePlayer(p), 20);
			}
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		for(int i = 0; i < StaffChat.InVanish.size(); i++) {
			Player player = StaffChat.InVanish.get(i);
			if(StaffChat.InVanish.contains(player)) {
				p.hidePlayer(StaffChat.InVanish.get(i));
			}
		}
	}
}
