package me.Adamki11s.Zombies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public class ZombiesWaveCheck implements Runnable {

	public static Zombies plugin;
	public static Server server = Zombies.server;
	public static World world;
	public static Map<Player, Location> prevLoc = new HashMap<Player, Location>();
	
	public static List<LivingEntity> entities;
	
	public static boolean entityRemains = true, roundCompleteMsgSent = false;
	public static int breakTimer = 20;
	
	public ZombiesWaveCheck(Zombies instance) {
		plugin = instance;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(Waves.gameStarted){
			entityRemains = false;
			entities = Waves.world.getLivingEntities();
			List<Player> players = Waves.world.getPlayers();
			for(Entity entity : entities){
				if(entity.getLocation().getX() < Arenas.trc.getX() && entity.getLocation().getX() > Arenas.tlc.getX() && entity.getLocation().getZ() < Arenas.tlc.getZ() && entity.getLocation().getZ() > Arenas.blc.getZ()   ){
					//server.broadcastMessage("Entity in arena!");
					entityRemains = true;
				}
			}
				
					if(breakTimer <= 0){
						breakTimer = 20;
						Waves.waves();
						if(Waves.wave != 10 && Waves.wave != 20 && Waves.wave != 30){
							for(Player player : players){
								if(player.getLocation().getX() < Arenas.trc.getX() && player.getLocation().getX() > Arenas.tlc.getX() && player.getLocation().getZ() < Arenas.tlc.getZ() && player.getLocation().getZ() > Arenas.blc.getZ()){
									player.sendMessage(ChatColor.RED + "[Zombies] " + ChatColor.YELLOW + "New wave spawned!");
								}
							}
						} else {
							for(Player player : players){
								if(player.getLocation().getX() < Arenas.trc.getX() && player.getLocation().getX() > Arenas.tlc.getX() && player.getLocation().getZ() < Arenas.tlc.getZ() && player.getLocation().getZ() > Arenas.blc.getZ()){
									player.sendMessage(ChatColor.RED + "[Zombies] " + ChatColor.YELLOW + "BOSS ROUND!");
								}
							}
						}
					}
					breakTimer--;				
			}
			
			
			
			
			//System.out.println(entities);
			//entities = Waves.world.getEntities();
			//System.out.println(entities);
	}

}
