package me.Adamki11s.Zombies;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class ZombiesPlayerListener extends PlayerListener {

	public static Zombies plugin;
	
	public ZombiesPlayerListener(Zombies instance) {
		plugin = instance;
	}
	
	
	public static Location loc1;	
	public static Map<Player, Boolean> loc1set = new HashMap<Player, Boolean>();
	public static Map<Player, Boolean> regionSetting = new HashMap<Player, Boolean>();
	
	public void onPlayerJoin(PlayerJoinEvent event){
		regionSetting.put(event.getPlayer(), false);
		loc1set.put(event.getPlayer(), false);
	}
	
	public void onPlayerInteract(PlayerInteractEvent event){
		Player player = event.getPlayer();
			if(regionSetting.get(player)){
				
				if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK){
					loc1 = event.getClickedBlock().getLocation();
					loc1set.put(player, true);
					player.sendMessage(ChatColor.GREEN + "Centre set : " + ChatColor.YELLOW + "X : " + ChatColor.LIGHT_PURPLE + loc1.getX() + ChatColor.YELLOW + " Y : "  + ChatColor.LIGHT_PURPLE + loc1.getY() + ChatColor.YELLOW + " Z : " + ChatColor.LIGHT_PURPLE + loc1.getZ());
				}
				
			}
	}
	
}
