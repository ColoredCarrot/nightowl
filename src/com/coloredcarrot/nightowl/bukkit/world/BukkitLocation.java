package com.coloredcarrot.nightowl.bukkit.world;

import org.bukkit.Location;

public class BukkitLocation implements com.coloredcarrot.nightowl.world.Location
{
	
	private final Location loc;

	public BukkitLocation(Location loc)
	{
		this.loc = loc;
	}

	public int getBlockX()
	{
		return loc.getBlockX();
	}

	public int getBlockY()
	{
		return loc.getBlockY();
	}

	public int getBlockZ()
	{
		return loc.getBlockZ();
	}

	public float getPitch()
	{
		return loc.getPitch();
	}

	public double getX()
	{
		return loc.getX();
	}

	public double getY()
	{
		return loc.getY();
	}

	public float getYaw()
	{
		return loc.getYaw();
	}

	public double getZ()
	{
		return loc.getZ();
	}
	
	@Override
	public String getWorld()
	{
		return loc.getWorld().getName();
	}
	
	public Location bukkit()
	{
		return loc.clone();
	}

}
