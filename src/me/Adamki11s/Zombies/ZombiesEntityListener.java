package me.Adamki11s.Zombies;

import org.bukkit.event.entity.EntityListener;

public class ZombiesEntityListener extends EntityListener {

	public static Zombies plugin;
	
	public ZombiesEntityListener(Zombies instance) {
		plugin = instance;
	}
	
}
