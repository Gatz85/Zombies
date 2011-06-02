package me.Adamki11s.Zombies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Waves {
	
	public static Boolean gameStarted = false;
	public static Map<Player, Integer> score = new HashMap<Player, Integer>();
	
	public static ArrayList<Entity> monsters = new ArrayList(); 
	
	public static World world;
	public static Location spawnLocation = ZombiesPlayerListener.loc1;
	
	public static boolean waveCompleted = false;
	
	/* 
	 * Monsters
	 */
	public static CreatureType[] easyMonsters = new CreatureType[] {CreatureType.SPIDER, CreatureType.ZOMBIE, CreatureType.SKELETON};
	public static CreatureType[] mediumMonsters = new CreatureType[] {CreatureType.PIG_ZOMBIE, CreatureType.SLIME};
	public static CreatureType[] hardMonsters = new CreatureType[] {CreatureType.CREEPER, CreatureType.MONSTER};
	public static CreatureType[] bossMonsters = new CreatureType[] {CreatureType.GHAST, CreatureType.GIANT};
	public static int wave = 0, easyMonstersToSpawn = 0, mediumMonstersToSpawn = 0, hardMonstersToSpawn = 0, bossMonstersToSpawn = 0, emts = 0, mmts = 0, hmts = 0, bmts = 0;
	/* 
	 * Monsters
	 */
	
	private static void waveMonstersEasy(int wave){
		Random generator = new Random();
		
		int randomCount = generator.nextInt(6) + 3;
		LivingEntity spawned1 = null;
		
		for(int i = 0; i < randomCount * wave; i++){
			Random gen = new Random();
			int randomCreature = gen.nextInt(3);
			world.spawnCreature(spawnLocation, easyMonsters[randomCreature]);
			//spawned1 = world.spawnCreature(spawnLocation, easyMonsters[randomCreature]);
			//spawned1.setHealth(5);
			easyMonstersToSpawn++;
		}
	}
	
	private static void waveMonstersMedium(int wave){
		Random generator = new Random();
		
		int randomCount = generator.nextInt(4) + 2;
		LivingEntity spawned1 = null;
		
		for(int i = 0; i < randomCount * wave; i++){
			Random gen = new Random();
			int randomCreature = gen.nextInt(2);
			spawned1 = world.spawnCreature(spawnLocation, mediumMonsters[randomCreature]);
			//spawned1 = world.spawnCreature(spawnLocation, easyMonsters[randomCreature]);
			//spawned1.setHealth(5);
			mediumMonstersToSpawn++;
		}
	}
	
	private static void waveMonstersHard(int wave){
		Random generator = new Random();
		
		int randomCount = generator.nextInt(4) + 2;
		LivingEntity spawned1 = null;
		
		for(int i = 0; i < randomCount * wave; i++){
			Random gen = new Random();
			int randomCreature = gen.nextInt(2);
			spawned1 = world.spawnCreature(spawnLocation, hardMonsters[randomCreature]);
			
			//spawned1 = world.spawnCreature(spawnLocation, easyMonsters[randomCreature]);
			//spawned1.setHealth(5);
			hardMonstersToSpawn++;
		}
	}
	
	private static void waveMonstersBOSS(int wave){
		Random generator = new Random();
		
		LivingEntity spawned1 = null;
		
		for(int i = 0; i < 3; i++){
			Random gen = new Random();
			int randomCreature = gen.nextInt(2);
			spawned1 = world.spawnCreature(spawnLocation, bossMonsters[randomCreature]);
			
			//spawned1 = world.spawnCreature(spawnLocation, easyMonsters[randomCreature]);
			//spawned1.setHealth(5);
			bossMonstersToSpawn++;
		}
	}
	
	
	public static void startGame(){
		List<Player> players = Waves.world.getPlayers();
		for(Player player : players){
			if(player.getLocation().getX() < Arenas.trc.getX() && player.getLocation().getX() > Arenas.tlc.getX() && player.getLocation().getZ() < Arenas.tlc.getZ() && player.getLocation().getZ() > Arenas.blc.getZ()){
				player.sendMessage(ChatColor.RED + "[Zombies] " + ChatColor.YELLOW + "Zombies has started!");
			}
		 }
		 gameStarted = true;
		 spawnLocation = ZombiesPlayerListener.loc1;
		 waves();
	}
	
	public static void waves(){
		List<Player> players = Waves.world.getPlayers();
		for(Player player : players){
			if(player.getLocation().getX() < Arenas.trc.getX() && player.getLocation().getX() > Arenas.tlc.getX() && player.getLocation().getZ() < Arenas.tlc.getZ() && player.getLocation().getZ() > Arenas.blc.getZ()){
			} else {
				if(Participants.participants.contains(player)){
					Participants.playerLeft(player, Zombies.server);
				}
			}
		 }
		
		wave++;
		
		if(wave != 10 && wave != 20 && wave != 30){
		if(wave > 0){
			waveMonstersEasy(wave);
		}
		
		if(wave > 5){
			waveMonstersMedium(wave);
		}
		
		if(wave > 10){
			waveMonstersHard(wave);
		}
		} else {
			waveMonstersBOSS(wave);
		}
		
		waveCompleted = false;
		ZombiesWaveCheck.roundCompleteMsgSent = false;
		
	}
}
