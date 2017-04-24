package com.coloredcarrot.nightowl.bukkit.world;

import org.bukkit.World;

public class BukkitWorld implements com.coloredcarrot.nightowl.world.World
{
	
	private final World world;
	
	public BukkitWorld(World world)
	{
		this.world = world;
	}
	
	@Override
	public String getName()
	{
		return world.getName();
	}
	
	public World bukkit()
	{
		return world;
	}

}
