package me.Adamki11s.Zombies;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Arenas {
	
	public static int rBlock = 0;
	
	public static Location arenaLocation;
	public static int Radius;	
	public static Player arenaHost;
	public static boolean arenaCreated;
	
	public static Location trc, tlc, brc, blc;

	public static void createBorder(Location loc, int radius, Player player, int blockType) {
		
		if(arenaCreated){
			player.sendMessage(ChatColor.RED + "There is already a game in progress!");
			return;
		}
		
		double trcx, trcz, tlcx, tlcz, brcx, brcz, blcx, blcz;
		Location tmploc;
		Block block, block1, block2, block3;
		rBlock = blockType;
		
		arenaHost = player;
		Radius = radius;
		
		//top right
		tmploc = loc;
		
		trcx = tmploc.getX() + radius;
		trcz = tmploc.getZ() + radius;
		//top right
		
		//top left
		tlcx = tmploc.getX() - radius;
		tlcz = tmploc.getZ() + radius;
		//top left
		
		//bottom right
		brcx = tmploc.getX() + radius;
		brcz = tmploc.getZ() - radius;
		//bottom right
		
		//bottom left		
		blcx = tmploc.getX() - radius;
		blcz = tmploc.getZ() - radius;
		//bottom left
		
		trc = new Location(player.getWorld(), trcx, loc.getY(), trcz);
		tlc = new Location(player.getWorld(), tlcx, loc.getY(), tlcz);
		brc = new Location(player.getWorld(), brcx, loc.getY(), brcz);
		blc = new Location(player.getWorld(), blcx, loc.getY(), blcz);
		
		block = player.getWorld().getBlockAt(trc);
		block.setTypeId(rBlock);
		
		block = player.getWorld().getBlockAt(tlc);
		block.setTypeId(rBlock);
		
		block = player.getWorld().getBlockAt(brc);
		block.setTypeId(rBlock);
		
		block = player.getWorld().getBlockAt(blc);
		block.setTypeId(rBlock);
		
		
		player.sendMessage(ChatColor.GREEN + "Creating area...Rendering " + ChatColor.LIGHT_PURPLE + calculateVolume(radius) + ChatColor.GREEN + " chunks.");
		
		
		if(trc.getX() > tlc.getX()){
			for(int a = 1; (tlc.getX() + a) < trc.getX(); a++){
				Location tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY(), trc.getZ());
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
				tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY(), brc.getZ());
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
				for(int b = 1; b < (radius * 2); b++){//floor
					tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY(), brc.getZ() + b);
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
				}
				for(int b = 1; b < (radius * 2); b++){ //ceil
					tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY() + 15, brc.getZ() + b);
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(20);
				}
				for(int c = 1; c < 15; c++){
					tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY() + c, brc.getZ());
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
					tmp = new Location(player.getWorld(), (brc.getX() - a), loc.getY() + c, trc.getZ());//br >> tr
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
				}
			}
		} else if(trc.getX() < tlc.getX()){
			for(int a = -1;  trc.getX() < (tlc.getX() + a); a--){
				Location tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY(), trc.getZ());
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
				tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY(), brc.getZ());
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
				for(int b = 1; b < (radius * 2); b++){ //floor
					tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY(), brc.getZ() + b);
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
				}
				for(int b = 1; b < (radius * 2); b++){ //ceil
					tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY() + 15, brc.getZ() + b);
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(20);
				}
				for(int c = 1; c < 15; c++){
					tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY() + c, brc.getZ());
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
					tmp = new Location(player.getWorld(), (brc.getX() - a), loc.getY() + c, trc.getZ());
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
				}
			}
		}
		
		
		if(tlc.getZ() > blc.getZ()){//trc >> tlc &&            tlc >> blc
			for(int a = 1; (blc.getZ() + a) < tlc.getZ(); a++){
				Location tmp = new Location(player.getWorld(), (blc.getX()), loc.getY(), (blc.getZ() + a));
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
			    tmp = new Location(player.getWorld(), (brc.getX()), loc.getY(), (blc.getZ() + a));
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
				for(int c = 1; c < 15; c++){
						tmp = new Location(player.getWorld(), (tlc.getX()), loc.getY() + c, brc.getZ() + a);
						block = player.getWorld().getBlockAt(tmp);
						block.setTypeId(rBlock);
						tmp = new Location(player.getWorld(), (brc.getX()), loc.getY() + c, trc.getZ() - a);//br >> tr
						block = player.getWorld().getBlockAt(tmp);
						block.setTypeId(rBlock);
				}
			}
		} else if(tlc.getZ() < blc.getZ()){
			for(int a = -1;  tlc.getZ() < (blc.getZ() + a); a--){
				Location tmp = new Location(player.getWorld(), (blc.getX()), loc.getY(), (blc.getZ() + a));
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
				tmp = new Location(player.getWorld(), (brc.getX()), loc.getY(), (blc.getZ() + a));//brc + a
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
				for(int c = 1; c < 15; c++){
					tmp = new Location(player.getWorld(), (tlc.getX()), loc.getY() + c, brc.getZ() + a);
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
					tmp = new Location(player.getWorld(), (brc.getX()), loc.getY() + c, trc.getZ() - a);//br >> tr
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
				}
			}
		}
		
		player.sendMessage(ChatColor.GREEN + "Zombie Arena created successfully!");
		player.sendMessage(ChatColor.GREEN + "You can now host the game." + ChatColor.LIGHT_PURPLE + " /zombies host <slots>");
		arenaCreated = true;
		arenaLocation = loc;
		
	}
	
	public static boolean isHost(String arenaName, Player player){
		if(player.isOp() || arenaHost == player){
			return true;
		}
		return false;
	}
	
	
	
	
	public static void removeBorder(Location loc, int radius, Player player, int blockType) {
		
		if(!arenaCreated){
			player.sendMessage(ChatColor.RED + "There is no location to delete!");
		}
		
		double trcx, trcz, tlcx, tlcz, brcx, brcz, blcx, blcz;
		Location tmploc;
		Block block, block1, block2, block3;
		rBlock = blockType;
		
		//top right
		tmploc = loc;
		
		trcx = tmploc.getX() + radius;
		trcz = tmploc.getZ() + radius;
		//top right
		
		//top left
		tlcx = tmploc.getX() - radius;
		tlcz = tmploc.getZ() + radius;
		//top left
		
		//bottom right
		brcx = tmploc.getX() + radius;
		brcz = tmploc.getZ() - radius;
		//bottom right
		
		//bottom left		
		blcx = tmploc.getX() - radius;
		blcz = tmploc.getZ() - radius;
		//bottom left
		
		trc = new Location(player.getWorld(), trcx, loc.getY(), trcz);
		tlc = new Location(player.getWorld(), tlcx, loc.getY(), tlcz);
		brc = new Location(player.getWorld(), brcx, loc.getY(), brcz);
		blc = new Location(player.getWorld(), blcx, loc.getY(), blcz);
		
		block = player.getWorld().getBlockAt(trc);
		block.setTypeId(rBlock);
		
		block = player.getWorld().getBlockAt(tlc);
		block.setTypeId(rBlock);
		
		block = player.getWorld().getBlockAt(brc);
		block.setTypeId(rBlock);
		
		block = player.getWorld().getBlockAt(blc);
		block.setTypeId(rBlock);
		
		
		player.sendMessage(ChatColor.GREEN + "Deleting area...Unloading " + ChatColor.LIGHT_PURPLE + calculateVolume(radius) + ChatColor.GREEN + " chunks.");
		
		
		if(trc.getX() > tlc.getX()){
			for(int a = 1; (tlc.getX() + a) < trc.getX(); a++){
				Location tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY(), trc.getZ());
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
				tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY(), brc.getZ());
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
				for(int b = 1; b < (radius * 2); b++){//floor
					tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY(), brc.getZ() + b);
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
				}
				for(int b = 1; b < (radius * 2); b++){ //ceil
					tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY() + 15, brc.getZ() + b);
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
				}
				for(int c = 1; c < 15; c++){
					tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY() + c, brc.getZ());
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
					tmp = new Location(player.getWorld(), (brc.getX() - a), loc.getY() + c, trc.getZ());//br >> tr
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
				}
			}
		} else if(trc.getX() < tlc.getX()){
			for(int a = -1;  trc.getX() < (tlc.getX() + a); a--){
				Location tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY(), trc.getZ());
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
				tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY(), brc.getZ());
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
				for(int b = 1; b < (radius * 2); b++){ //floor
					tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY(), brc.getZ() + b);
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
				}
				for(int b = 1; b < (radius * 2); b++){ //ceil
					tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY() + 15, brc.getZ() + b);
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
				}
				for(int c = 1; c < 15; c++){
					tmp = new Location(player.getWorld(), (tlc.getX() + a), loc.getY() + c, brc.getZ());
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
					tmp = new Location(player.getWorld(), (brc.getX() - a), loc.getY() + c, trc.getZ());
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
				}
			}
		}
		
		
		if(tlc.getZ() > blc.getZ()){//trc >> tlc &&            tlc >> blc
			for(int a = 1; (blc.getZ() + a) < tlc.getZ(); a++){
				Location tmp = new Location(player.getWorld(), (blc.getX()), loc.getY(), (blc.getZ() + a));
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
			    tmp = new Location(player.getWorld(), (brc.getX()), loc.getY(), (blc.getZ() + a));
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
				for(int c = 1; c < 15; c++){
						tmp = new Location(player.getWorld(), (tlc.getX()), loc.getY() + c, brc.getZ() + a);
						block = player.getWorld().getBlockAt(tmp);
						block.setTypeId(rBlock);
						tmp = new Location(player.getWorld(), (brc.getX()), loc.getY() + c, trc.getZ() - a);//br >> tr
						block = player.getWorld().getBlockAt(tmp);
						block.setTypeId(rBlock);
				}
			}
		} else if(tlc.getZ() < blc.getZ()){
			for(int a = -1;  tlc.getZ() < (blc.getZ() + a); a--){
				Location tmp = new Location(player.getWorld(), (blc.getX()), loc.getY(), (blc.getZ() + a));
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
				tmp = new Location(player.getWorld(), (brc.getX()), loc.getY(), (blc.getZ() + a));//brc + a
				block = player.getWorld().getBlockAt(tmp);
				block.setTypeId(rBlock);
				for(int c = 1; c < 15; c++){
					tmp = new Location(player.getWorld(), (tlc.getX()), loc.getY() + c, brc.getZ() + a);
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
					tmp = new Location(player.getWorld(), (brc.getX()), loc.getY() + c, trc.getZ() - a);//br >> tr
					block = player.getWorld().getBlockAt(tmp);
					block.setTypeId(rBlock);
				}
			}
		}
		
		player.sendMessage(ChatColor.GREEN + "Zombie Arena deleted successfully.");
		arenaCreated = false;
		
	}
	
	
	private static double calculateVolume(int radius) {		
		return (radius) * (radius) * 15;
	}


	public static void createRoof(){
		
	}
	
	public static void createFloor(){
		
	}

}
