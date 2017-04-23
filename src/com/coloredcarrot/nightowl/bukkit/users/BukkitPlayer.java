package com.coloredcarrot.nightowl.bukkit.users;

import org.bukkit.entity.Player;

import com.coloredcarrot.nightowl.bukkit.BukkitUtil;
import com.coloredcarrot.nightowl.bukkit.world.BukkitLocation;
import com.coloredcarrot.nightowl.users.HumanUser;
import com.coloredcarrot.nightowl.users.PermissionSet;
import com.coloredcarrot.nightowl.world.Location;

public class BukkitPlayer implements HumanUser
{
	
	public static BukkitPlayer get(Player player)
	{
		// TODO: implement caching
		return new BukkitPlayer(player);
	}
	
	private final Player player;
	private PermissionSet permissions;
	
	private BukkitPlayer(Player player)
	{
		this.player = player;
		this.permissions = new PermissionSet();
	}

	@Override
	public boolean hasPermission(String permission)
	{
		return permissions.has(permission);
	}

	@Override
	public boolean givePermission(String permission)
	{
		return permissions.give(permission);
	}

	@Override
	public boolean takePermission(String permission)
	{
		return permissions.take(permission);
	}

	@Override
	public String getName()
	{
		return player.getName();
	}

	@Override
	public String getDisplayName()
	{
		return player.getDisplayName();
	}

	@Override
	public void setDisplayName(String displayName)
	{
		player.setDisplayName(displayName);
	}

	@Override
	public void sendMessage(String m)
	{
		player.sendMessage(m);
	}
	
	@Override
	public void sendMessage(String... m)
	{
		player.sendMessage(m);
	}

	@Override
	public Location getLocation()
	{
		return new BukkitLocation(player.getLocation());
	}

	@Override
	public void setLocation(double x, double y, double z)
	{
		player.teleport(new org.bukkit.Location(player.getWorld(), x, y, z));
	}

	@Override
	public void setLocation(double x, double y, double z, float yaw, float pitch)
	{
		player.teleport(new org.bukkit.Location(player.getWorld(), x, y, z, yaw, pitch));
	}

	@Override
	public void setLocation(Location loc)
	{
		player.teleport(BukkitUtil.toBukkitLocation(loc));
	}

}
