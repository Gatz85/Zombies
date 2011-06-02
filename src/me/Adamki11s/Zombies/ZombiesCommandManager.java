package me.Adamki11s.Zombies;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ZombiesCommandManager implements CommandExecutor {

	public static Zombies plugin;
	public static Server server;
	public static Map<Player, Location> prevLoc = new HashMap<Player, Location>();
	
	public static List<LivingEntity> entities;
	
	public static Cube cube;
	
	public ZombiesCommandManager(Zombies instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("zombies")){
			
			Player player = (Player) sender;
			
			if(args.length == 1 && args[0].equalsIgnoreCase("set")){
				if(ZombiesPlayerListener.regionSetting.get(player)){
					player.sendMessage(ChatColor.RED + "You are already setting the arena!");
					return true;
				} else {
					ZombiesPlayerListener.regionSetting.put(player, true);
					player.sendMessage(ChatColor.GREEN + "You are now setting the arena. Set the centre point.");
					return true;
				}
			}
			
			if(args.length == 2 && args[0].equalsIgnoreCase("create")){
				if(!ZombiesPlayerListener.regionSetting.get(player)){
					player.sendMessage(ChatColor.RED + "You must set a point before creating an arena!");
				}
				int radius = 0;
				if(!ZombiesPlayerListener.loc1set.get(player)){
					player.sendMessage(ChatColor.RED + "You must set the centre point before creating the arena!");
					player.sendMessage(ChatColor.YELLOW + "/zombies set");
					return true;
				}
				try{
					radius = Integer.parseInt(args[1]);
				} catch (NumberFormatException ex){
					player.sendMessage(ChatColor.RED + "The radius must be an integer! " + ChatColor.YELLOW + "/zombies create radius");
					return true;
				}
				
				ZombiesPlayerListener.regionSetting.put(player, false);
				Arenas.createBorder(ZombiesPlayerListener.loc1, radius, player, 7);
				LobbyManager.playerInGame.put(args[1], player);
				prevLoc.put(player, player.getLocation());
				Arenas.arenaLocation.setY(Arenas.arenaLocation.getY() + 1);
				player.teleport(Arenas.arenaLocation);
				return true;
			}
			
			if(args.length == 1 && args[0].equalsIgnoreCase("join")){
				if(Arenas.arenaCreated){
						prevLoc.put(player, player.getLocation());
						player.teleport(Arenas.arenaLocation);
						Participants.playerJoin(player, server);
						player.getInventory().addItem(new ItemStack(261, 1));
						player.getInventory().addItem(new ItemStack(262, 64));
						player.getInventory().addItem(new ItemStack(276, 1));
						player.sendMessage(ChatColor.RED + "[Zombies] " + ChatColor.YELLOW + "Wating for host to start game...");
						return true;
						
				} else {
					player.sendMessage(ChatColor.RED + "[Zombies] " + ChatColor.RED + "There is no game to join!");
					return true;
				}
						
			}
			
			if(args.length == 1 && args[0].equalsIgnoreCase("leave")){
				if(Participants.participants.contains(player)){
					Participants.playerLeft(player, server);
					player.teleport(prevLoc.get(player));
					return true;
				} else {
					player.sendMessage(ChatColor.RED + "[Zombies] You are not in  a game!");
					return true;
				}
			}
			
			if(args.length == 1 && args[0].equalsIgnoreCase("start")){
				if(!Waves.gameStarted){
					if(Arenas.arenaHost == player){
						Waves.world = player.getWorld();
						Waves.startGame();
						return true;
					} else {
						player.sendMessage(ChatColor.RED + "You must wait for the host to start the game.");
						return true;
					}
				} else {
					player.sendMessage(ChatColor.RED + "The game has already started!");
					return true;
				}
			}
			
			if(args.length == 2 && args[0].equalsIgnoreCase("host")){
				int slots;
				
				if(!Arenas.arenaCreated){
					player.sendMessage(ChatColor.RED + "No arena has been created!");
					return true;
				}
				
				try{
					slots = Integer.parseInt(args[1]);
				} catch (NumberFormatException ex){
					player.sendMessage(ChatColor.RED + "The number of slots must be an integer!");
					return true;
				}
				
				if(slots > 20){
					player.sendMessage(ChatColor.RED + "A game of zombies can only contain a maximum of 20 players.");
					return true;
				} else if(slots < 1){
					player.sendMessage(ChatColor.RED + "A game of zombies must contain a minimum of 2 players.");
					return true;
				}
				
				
				
				
				if(!(Arenas.arenaHost == player)){
					player.sendMessage(ChatColor.RED + "You are not the host of this arena!");
					return true;
				}
				
					
				LobbyManager.publicLobbyCreated(server, slots);
				return true;		
					
			}
			
			
			if(args.length == 1 && args[0].equalsIgnoreCase("delete")){
				int radius = 0;
				
				if(!Arenas.arenaCreated){
					player.sendMessage(ChatColor.RED + "There is no arena to delete.");
					return true;
				}
				
				if(Arenas.arenaHost == player){
					ZombiesPlayerListener.regionSetting.put(player, false);
					Arenas.removeBorder(Arenas.arenaLocation, Arenas.Radius, player, 0);
					Waves.gameStarted = false;
					entities = Waves.world.getLivingEntities();
					List<Player> players = Waves.world.getPlayers();
					for(Entity entity : entities){
						if(entity.getLocation().getX() < Arenas.trc.getX() && entity.getLocation().getX() > Arenas.tlc.getX() && entity.getLocation().getZ() < Arenas.tlc.getZ() && entity.getLocation().getZ() > Arenas.blc.getZ()   ){
							//server.broadcastMessage("Entity in arena!");
							entity.remove();
						}
					}
					return true;
				} else {
					player.sendMessage(ChatColor.RED + "Only the host can delete the arena!");
					return true;
				}
				
				
				
			}
			
			
			
			return true;
		}
		return false;
	}

}
