package com.coloredcarrot.nightowl.bukkit.world;

import org.bukkit.Location;

import com.coloredcarrot.nightowl.bukkit.BukkitUtil;
import com.coloredcarrot.nightowl.world.World;

public class BukkitLocation implements com.coloredcarrot.nightowl.world.Location
{
	
	private final Location loc;

	public BukkitLocation(Location loc)
	{
		this.loc = loc;
	}

	@Override
	public int getBlockX()
	{
		return loc.getBlockX();
	}

	@Override
	public int getBlockY()
	{
		return loc.getBlockY();
	}

	@Override
	public int getBlockZ()
	{
		return loc.getBlockZ();
	}

	@Override
	public float getPitch()
	{
		return loc.getPitch();
	}

	@Override
	public double getX()
	{
		return loc.getX();
	}

	@Override
	public double getY()
	{
		return loc.getY();
	}

	@Override
	public float getYaw()
	{
		return loc.getYaw();
	}

	@Override
	public double getZ()
	{
		return loc.getZ();
	}
	
	@Override
	public World getWorld()
	{
		return BukkitUtil.toWorld(loc.getWorld());
	}
	
	public Location bukkit()
	{
		return loc.clone();
	}

}
