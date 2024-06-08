package elpupas2015.staffchat;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import elpupas2015.staffchat.commands.CSPYCommand;
import elpupas2015.staffchat.commands.ClearChat;
import elpupas2015.staffchat.commands.Comandos;
import elpupas2015.staffchat.commands.Freeze;
import elpupas2015.staffchat.commands.Helpop;
import elpupas2015.staffchat.commands.Report;
import elpupas2015.staffchat.commands.SCReload;
import elpupas2015.staffchat.commands.StaffListCommand;
import elpupas2015.staffchat.commands.StaffMode;
import elpupas2015.staffchat.commands.Vanish;
import elpupas2015.staffchat.events.Chat;
import elpupas2015.staffchat.events.CommandSpy;
import elpupas2015.staffchat.events.FreezeEvent;
import elpupas2015.staffchat.events.SendMessageOnJoin;
import elpupas2015.staffchat.events.StaffJoin;
import elpupas2015.staffchat.guis.StaffList;

public class StaffChat extends JavaPlugin{
	public String rutaConfig;
	PluginDescriptionFile pdffile = getDescription();
	public String version = pdffile.getVersion();
	public String nombre = "§e§l"+pdffile.getName()+"§7: ";
	public String latestversion = version;
	
	public static ArrayList<Player> Insc = new ArrayList<Player>();
	public static ArrayList<Player> Freezed = new ArrayList<Player>();
	public static ArrayList<Player> InStaff = new ArrayList<Player>();
	public static ArrayList<Player> InVanish = new ArrayList<Player>();
	
	public String getVersion() {
		return this.version;
	}
	
	public String getLatestVersion() {
		return this.latestversion;
	}
	
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("§7----------------------------------"); 
		Bukkit.getConsoleSender().sendMessage("§7");
		Bukkit.getConsoleSender().sendMessage("§6          StaffChat");
		Bukkit.getConsoleSender().sendMessage("§7");
		Bukkit.getConsoleSender().sendMessage("§eCreated by: §cELPUPAS2015");
		Bukkit.getConsoleSender().sendMessage("§eVersion: §c"+version);
		Bukkit.getConsoleSender().sendMessage("§7");
		Bukkit.getConsoleSender().sendMessage("§aThe plugin was succefully activated");
		Bukkit.getConsoleSender().sendMessage("§7");
		Bukkit.getConsoleSender().sendMessage("§7----------------------------------");
		RegistrarComandos();
		RegistrarEventos();
		RegistrarConfig();
		checkUpdate();
		checkConfig();
	}
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§7----------------------------------"); 
		Bukkit.getConsoleSender().sendMessage("§7");
		Bukkit.getConsoleSender().sendMessage("§6          StaffChat");
		Bukkit.getConsoleSender().sendMessage("§7");
		Bukkit.getConsoleSender().sendMessage("§eCreated by: §cELPUPAS2015");
		Bukkit.getConsoleSender().sendMessage("§eVersion: §c"+version);
		Bukkit.getConsoleSender().sendMessage("§7");
		Bukkit.getConsoleSender().sendMessage("§cThe plugin was succefully disabled");
		Bukkit.getConsoleSender().sendMessage("§7");
		Bukkit.getConsoleSender().sendMessage("§7----------------------------------");
	}
	public void RegistrarComandos() {
		this.getCommand("sc").setExecutor(new Comandos(this));
		this.getCommand("staffs").setExecutor(new StaffListCommand(this));
		this.getCommand("clearchat").setExecutor(new ClearChat(this));
		this.getCommand("screload").setExecutor(new SCReload(this));
		this.getCommand("helpop").setExecutor(new Helpop(this));
		this.getCommand("cspy").setExecutor(new CSPYCommand(this));
		this.getCommand("freeze").setExecutor(new Freeze(this));
		this.getCommand("staff").setExecutor(new StaffMode(this));
		this.getCommand("vanish").setExecutor(new Vanish(this));
		this.getCommand("report").setExecutor(new Report());
	}
	public void RegistrarEventos() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new Chat(this), (this));
		pm.registerEvents(new StaffJoin(this), (this));
		pm.registerEvents(new StaffList(this), (this));
		pm.registerEvents(new SendMessageOnJoin(this), (this));
		pm.registerEvents(new CommandSpy(this), (this));
		pm.registerEvents(new FreezeEvent(this), (this));
		pm.registerEvents(new StaffMode(this), (this));
		pm.registerEvents(new Report(), (this));
	}
	public void RegistrarConfig() {
		File config = new File(this.getDataFolder(),"config.yml");
		rutaConfig = config.getPath();
		if(!config.exists()) {
			this.getConfig().options().copyDefaults(true);
		}
		saveConfig();
	}
	
	public void checkConfig() {
		Path archive = Paths.get(rutaConfig);
		try {
			String text = new String(Files.readAllBytes(archive));
			if(!text.contains("bypassed-message:")) {
				getConfig().set("Messages.ClearChat.bypassed-message", "&aChat cleared by &e%player%");
				saveConfig();
			}
			if(!text.contains("sound:")) {
				getConfig().set("Config.sound", "NOTE_PLING");
				saveConfig();
			}
			if(!text.contains("no-message-error:")) {
				getConfig().set("Messages.Helpop.no-message-error", "&6[&eHelpop&6] &cYou have to put a message.");
				saveConfig();
			}
			ArrayList<String> pmsg = new ArrayList<String>();
	        pmsg.add("&aYour help message has been sent correctly.");
	        pmsg.add("&cThe abuse of this command can lead to a penalty.");
	        ArrayList<String> smsg = new ArrayList<String>();
	        smsg.add("&7&m----------------------------");
	        smsg.add("&6Player: &c%player%");
	        smsg.add("&6Message: &c%msg%");
	        smsg.add("&7&m----------------------------");
	        if(!text.contains("player-message:")) {
				getConfig().set("Messages.Helpop.player-message", pmsg);
				getConfig().set("Messages.Helpop.staff-message", smsg);
				saveConfig();
			}
	        if(!text.contains("CommandSpy:")) {
				getConfig().set("Messages.No-perms-messages.nopermission-commandspy", "&cYou don't have permissions &6(&7staff.commandspy&6)");
				getConfig().set("Messages.CommandSpy.enabled", "&6&l[&e&lCommandSpy&6&l] &7CommandSpy is now &aenabled&7.");
				getConfig().set("Messages.CommandSpy.disabled", "&6&l[&e&lCommandSpy&6&l] &7CommandSpy is now &cdisabled&7.");
				getConfig().set("Messages.CommandSpy.format", "&2&l[&6&lCSPY&2&l] &c%player%: &7%cmd%");
				saveConfig();
			}
	        if(!text.contains("Freeze:")) {
				getConfig().set("Messages.No-perms-messages.nopermission-freeze", "&cYou don't have permissions &6(&7staff.freeze&6)");
				getConfig().set("Messages.Freeze.no-correct-format", "&6&l[&b&lFreeze&6&l] &cIncorrect format &6(&7-/freeze <username>&6)");
				getConfig().set("Messages.Freeze.succeful", "&6&l[&b&lFreeze&6&l] &aYou have successfully freezed: &c%player%");
				getConfig().set("Messages.Freeze.no-connected", "&6&l[&b&lFreeze&6&l] &cThe player &7%player% &cis not connected.");
				getConfig().set("Messages.Freeze.target-freezed", "&6&l[&b&lFreeze&6&l] &7You have been freezed by: &c%staff%");
				getConfig().set("Messages.Freeze.staff-freezing", "&6&l[&b&lFreeze&6&l] &7You have successfully freezed: &a%player%");
				getConfig().set("Messages.Freeze.target-unfreezed", "&6&l[&b&lFreeze&6&l] &7You have been unfreezed by: &c%staff%");
				getConfig().set("Messages.Freeze.staff-unfreezing", "&6&l[&b&lFreeze&6&l] &7You have successfully unfreezed: &a%player%");
				getConfig().set("Messages.Freeze.left-while-frozen", "&6&l[&b&lFreeze&6&l] &cThe player &7%player% &cis not connected.");
				getConfig().set("Messages.Freeze.click-to-ban", "&6&l[&b&lFreeze&6&l] &7Click here to ban.");
				getConfig().set("Messages.Freeze.ban-command", "/ban %player% Left while frozen");
				saveConfig();
			}
	        if(!text.contains("StaffMode:")) {
				getConfig().set("Messages.StaffMode.tp-random-player", "&6&l[&b&lStaffMode&6&l] &7You teleported to: %player%.");
				getConfig().set("Messages.StaffMode.staff-activated", "&6&l[&b&lStaffMode&6&l] &7StaffMode is now &aENABLED&7.");
				getConfig().set("Messages.StaffMode.staff-disabled", "&6&l[&b&lStaffMode&6&l] &7StaffMode is now &cDISABLED&7.");
				getConfig().set("Messages.StaffMode.vanish-activated", "&6&l[&b&lStaffMode&6&l] &7Vanish is now &aENABLED&7.");
				getConfig().set("Messages.StaffMode.vanish-disabled", "&6&l[&b&lStaffMode&6&l] &7Vanish is now &cDISABLED&7.");
				getConfig().set("Messages.StaffMode.vanish-leave", "&8[&c✗&8] &e%player%");
				getConfig().set("Messages.StaffMode.vanish-join", "&8[&a✔&8] §e%player%");
				saveConfig();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void checkUpdate() {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(
                    "https://api.spigotmc.org/legacy/update.php?resource=61089").openConnection();
            int timed_out = 1250;
            con.setConnectTimeout(timed_out);
            con.setReadTimeout(timed_out);
            latestversion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
            if (latestversion.length() <= 7) {
                if(!version.equals(latestversion)){
            		Bukkit.getConsoleSender().sendMessage("§7----------------------------------"); 
            		Bukkit.getConsoleSender().sendMessage("§7");
            		Bukkit.getConsoleSender().sendMessage("§6           StaffChat");
            		Bukkit.getConsoleSender().sendMessage("§7");
                    Bukkit.getConsoleSender().sendMessage("§eThere is a new version available §7(" + latestversion + ")");
                    Bukkit.getConsoleSender().sendMessage("§7"); 
                    Bukkit.getConsoleSender().sendMessage("§aYou can download it at: §chttps://www.spigotmc.org/resources/61089/");  
                    Bukkit.getConsoleSender().sendMessage("§7"); 
                    Bukkit.getConsoleSender().sendMessage("§7----------------------------------"); 
                }          
            }
        } catch (Exception ex) {
            Bukkit.getConsoleSender().sendMessage("§6[§eStaffChat§6] §cError while checking for updates.");
        }
    }
}