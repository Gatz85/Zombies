package me.Adamki11s.Zombies;

import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Zombies extends JavaPlugin {
	
	public static double version = 1.0;
	
	private final ZombiesPlayerListener playerListener = new ZombiesPlayerListener(this);
	private final ZombiesEntityListener entityListener = new ZombiesEntityListener(this);
	
	private ZombiesWaveCheck waveChecks = null;
	private int taskId = 0;

	Logger log = Logger.getLogger("Minecraft");
	
	public static Server server;
	
	@Override
	public void onDisable() {
		 this.getServer().getScheduler().cancelTask(this.taskId);
		log.info("[Zombies] v" + version + " - Plugin Disabled.");
	}

	@Override
	public void onEnable() {
		
		PluginManager pm = this.getServer().getPluginManager();
		
		getCommand("zombies").setExecutor(new ZombiesCommandManager(this));
		ZombiesCommandManager.server = this.getServer();
		LobbyManager.server = this.getServer();
		server = this.getServer();
		
		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.High, this);
		pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.High, this);
		
		this.waveChecks = new ZombiesWaveCheck(this);
		this.taskId = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, this.waveChecks, 1L, 21L * 1);
		
		log.info("[Zombies] v" + version + " - Plugin Enabled.");
	}

}