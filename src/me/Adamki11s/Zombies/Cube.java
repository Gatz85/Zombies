package me.Adamki11s.Zombies;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Cube {

	/*
	 * Functions:
	 * __________
	 * Distance between a player and a point.
	 * Check if a player is in a given radius.
	 * Check if a player is in a cuboid (2 points must be given)
	 * Replace Blocks of a certain type to another block(In a radius)
	 * Replace All blocks to a different block(In a radius)
	 * Blocks of a certain type to another block(In a Cuboid)
	 * Replace All blocks to a different block(In a Cuboid)
	 * __________
	 */
	
	//Get distance between player and central location
	public double distFromPoint(Location point, Player player){
		
		if(player == null){
			return 0;
		}
		
		double xdist = 0, ydist = 0, zdist = 0, dist = 0,
		       lx = point.getX(), ly = point.getY(), lz = point.getZ(),
		       px = player.getLocation().getX(), py = player.getLocation().getY(), pz = player.getLocation().getZ();
		
		if(px < lx){
			xdist = lx - px;
		} else {
			xdist = px - lx;
		}
		if(py < ly){
			ydist = ly - py;
		} else {
			ydist = py - ly;
		}
		if(pz < lz){
			zdist = lz - pz;
		} else {
			zdist = pz - lz;
		}
		
		return pythagDist(xdist, ydist, zdist);
		
	}
	//Get distance between player and central location
	
	//Calculates distance between point and player.
	private double pythagDist(double xdist, double ydist, double zdist){
		xdist = Math.ceil(xdist);
		ydist = Math.ceil(ydist);
		zdist = Math.ceil(zdist);
		
		double Dim2_DIST = 0, Dim3_DIST = 0, xSQ, zSQ;
		
		xSQ = Math.pow(xdist, 2);
		zSQ = Math.pow(xdist, 2);
		
		Dim2_DIST = Math.sqrt(xSQ + zSQ);
		
		Dim3_DIST = Math.sqrt(Math.pow(Dim2_DIST, 2) + Math.pow(ydist, 2));
		
		return Dim3_DIST;
	}
	//Calculates distance between point and player.
	
	//Replace Blocks in Radius
	public void replaceBlockToBlockInRadius(Location point, double radius, World world, int source, int change){
		
		double x1 = point.getX() + radius,
	       	   x2 = point.getX() - radius,
	           y1 = point.getY() + radius,
	           y2 = point.getY() - radius,
	           z1 = point.getZ() + radius,
	           z2 = point.getZ() - radius;
		
		double a, b, c;
		Block bl;
		
		for(a = x2; x2 <= x1; a++){
			for(b = z2; z2 <= z1; b++){
				for(c = y2; y2 <= y1; c++){
					bl = world.getBlockAt(new Location(world, a, c, b));
					if(bl.getTypeId() == source){
						bl.setTypeId(change);
					}
				}
			}
		}
				
	}
	
	public void replaceAllBlocksInRadius(Location point, double radius, World world, int change){
		
		double x1 = point.getX() + radius,
	       	   x2 = point.getX() - radius,
	           y1 = point.getY() + radius,
	           y2 = point.getY() - radius,
	           z1 = point.getZ() + radius,
	           z2 = point.getZ() - radius;
		
		double a, b, c;
		Block bl;
		
		for(a = x2; x2 <= x1; a++){
			for(b = z2; z2 <= z1; b++){
				for(c = y2; y2 <= y1; c++){
					bl = world.getBlockAt(new Location(world, a, c, b));
					if(bl.getType() != Material.AIR){
						bl.setTypeId(change);
					}
				}
			}
		}
				
	}
	//Replace Blocks in Radius
	
	
	//Is player in radius
	public boolean inRadius(Location point, double radius, Player player){
		
		double x1 = point.getX() + radius,
		       x2 = point.getX() - radius,
		       y1 = point.getY() + radius,
		       y2 = point.getY() - radius,
		       z1 = point.getZ() + radius,
		       z2 = point.getZ() - radius;
		
		if( (((point.getY() <= y1) && (point.getY() >= y2)) || ((point.getY() >= y1) && (point.getY() <= y2))) && (((point.getZ() <= z1) && (point.getZ() >= z2)) || ((point.getZ() >= z1) && (point.getZ() <= z2)))  &&  (((point.getX() <= x1) && (point.getX() >= x2)) || ((point.getX() >= x1) && (point.getX() <= x2))) && (((point.getX() <= x1) && (point.getX() >= x2)) || ((point.getX() >= x1) && (point.getX() <= x2)))  ){
			return true;
		}
		
		return false;
	}
	//Is player in radius
	
	public void replaceAllBlocksInCuboid(Location p1, Location p2, double radius, World world, int change){

		Block bl;
		double x1 = p1.getX(), x2 = p2.getX(), y1 = p1.getY() , y2 = p2.getY(), z1 = p1.getZ(), z2 = p2.getZ();
		
		if(!formatVals(x1, x2)){
			double tmp = x1;
			x1 = x2;
			x2 = tmp;
		}		
		if(!formatVals(y1, y2)){
			double tmp = y1;
			y1 = y2;
			y2 = tmp;
		}		
		if(!formatVals(z1, z2)){
			double tmp = z1;
			z1 = z2;
			z2 = tmp;
		}
		
		double a, b, c;
		
		for(a = x2; x2 <= x1; a++){
			for(b = z2; z2 <= z1; b++){
				for(c = y2; y2 <= y1; c++){
					bl = world.getBlockAt(new Location(world, a, c, b));
					if(bl.getType() != Material.AIR){
						bl.setTypeId(change);
					}
				}
			}
		}
		
	}
	
	public void replaceBlockToBlockInCuboid(Location p1, Location p2, double radius, World world, int source, int change){

		Block bl;
		double x1 = p1.getX(), x2 = p2.getX(), y1 = p1.getY() , y2 = p2.getY(), z1 = p1.getZ(), z2 = p2.getZ();
		
		if(!formatVals(x1, x2)){
			double tmp = x1;
			x1 = x2;
			x2 = tmp;
		}		
		if(!formatVals(y1, y2)){
			double tmp = y1;
			y1 = y2;
			y2 = tmp;
		}		
		if(!formatVals(z1, z2)){
			double tmp = z1;
			z1 = z2;
			z2 = tmp;
		}
		
		double a, b, c;
		
		for(a = x2; x2 <= x1; a++){
			for(b = z2; z2 <= z1; b++){
				for(c = y2; y2 <= y1; c++){
					bl = world.getBlockAt(new Location(world, a, c, b));
					if(bl.getTypeId() == source){
						bl.setTypeId(change);
					}
				}
			}
		}
		
	}
	
	
	//Is player in cuboid
	public boolean inCuboid(Location p1, Location p2, Player player){
		
		if(player == null){
			return false;
		}
		
		Location playerLocation = player.getLocation();
		double x1 = p1.getX(), x2 = p2.getX(), y1 = p1.getY() , y2 = p2.getY(), z1 = p1.getZ(), z2 = p2.getZ();
		
		if(!formatVals(x1, x2)){
			double tmp = x1;
			x1 = x2;
			x2 = tmp;
		}		
		if(!formatVals(y1, y2)){
			double tmp = y1;
			y1 = y2;
			y2 = tmp;
		}		
		if(!formatVals(z1, z2)){
			double tmp = z1;
			z1 = z2;
			z2 = tmp;
		}
		
		if( (((playerLocation.getY() <= y1) && (playerLocation.getY() >= y2)) || ((playerLocation.getY() >= y1) && (playerLocation.getY() <= y2))) && (((playerLocation.getZ() <= z1) && (playerLocation.getZ() >= z2)) || ((playerLocation.getZ() >= z1) && (playerLocation.getZ() <= z2)))  &&  (((playerLocation.getX() <= x1) && (playerLocation.getX() >= x2)) || ((playerLocation.getX() >= x1) && (playerLocation.getX() <= x2))) && (((playerLocation.getX() <= x1) && (playerLocation.getX() >= x2)) || ((playerLocation.getX() >= x1) && (playerLocation.getX() <= x2)))  ){
			return true;
		}
		
		return false;
	}
	//Is player in cuboid
	
	private boolean formatVals(double v1, double v2){	
		if(v1 > v2){
			return true;
		} else {
			return false;
		}	
	}
	
}
