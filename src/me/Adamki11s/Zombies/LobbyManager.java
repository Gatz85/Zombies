package me.Adamki11s.Zombies;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class LobbyManager {
	
	public static Server server = Zombies.server;
	
	public static Map<String, Boolean> isArenaPublic = new HashMap<String, Boolean>();
	public static Map<String, Player> playerInvited = new HashMap<String, Player>();
	public static Map<String, Player> playerInGame = new HashMap<String, Player>();
	
	
	public static void publicLobbyCreated(Server server, int slots){
		server.broadcastMessage(ChatColor.RED + "[Zombies] " + ChatColor.YELLOW + "Public Lobby with " + ChatColor.LIGHT_PURPLE + slots + ChatColor.YELLOW + " slots created!");
		server.broadcastMessage(ChatColor.RED + "[Zombies] " + ChatColor.YELLOW + "To join : " + ChatColor.LIGHT_PURPLE + "/zombies join");
		Participants.slots = slots;
	}

}
