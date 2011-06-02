package me.Adamki11s.Zombies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class Participants {

	public static int slots = 0;
	
	//public static Map<Player, Boolean> participants = new HashMap<Player, Boolean>();
	public static ArrayList<Player> participants = new ArrayList(); 
	
	public static void playerLeft(Player player, Server server){
		slots++;
		participants.remove(player);
		server.broadcastMessage(ChatColor.RED + "[Zombies] " + ChatColor.YELLOW + player.getDisplayName() + " left a game of zombies. There are " + ChatColor.LIGHT_PURPLE + slots + ChatColor.YELLOW + " slots available");
	}
	
	public static void playerJoin(Player player, Server server){
		if((slots - 1) < 0){
			player.sendMessage(ChatColor.RED + "Sorry, this game is full or has not been hosted.");
			player.teleport(ZombiesCommandManager.prevLoc.get(player));
			return;
		}
		slots--;
		participants.add(player);
		if(slots > 0){
			server.broadcastMessage(ChatColor.RED + "[Zombies] " + ChatColor.YELLOW + player.getDisplayName() + " joined a game of zombies.");
			server.broadcastMessage(ChatColor.RED + "[Zombies] " + ChatColor.YELLOW + "There are " + ChatColor.LIGHT_PURPLE + slots + ChatColor.YELLOW + " slots available");
		} else {
			server.broadcastMessage(ChatColor.RED + "[Zombies] " + ChatColor.YELLOW + player.getDisplayName() + " joined a game of zombies. This game is now full.");
		}
	}
	
}
