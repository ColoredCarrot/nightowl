package com.coloredcarrot.nightowl.bukkit.users;

import org.bukkit.entity.Player;

import com.coloredcarrot.nightowl.bukkit.BukkitUtil;
import com.coloredcarrot.nightowl.users.HumanUser;
import com.coloredcarrot.nightowl.world.Location;
import com.coloredcarrot.nightowl.world.World;

public class BukkitPlayer implements HumanUser
{
	
	public static BukkitPlayer get(Player player)
	{
		// TODO: implement caching
		return new BukkitPlayer(player);
	}
	
	private final Player player;
	
	private BukkitPlayer(Player player)
	{
		this.player = player;
		//this.permissions = new PermissionSet();
	}

	@Override
	public boolean hasPermission(String permission)
	{
		return player.hasPermission(permission);
	}

	@Override
	public boolean givePermission(String permission)
	{
		throw new IllegalStateException("NYI");
	}

	@Override
	public boolean takePermission(String permission)
	{
		throw new IllegalStateException("NYI");//TODO: implement taking and giving permissions
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
		return BukkitUtil.toLocation(player.getLocation());
	}
	
	@Override
	public World getWorld()
	{
		return BukkitUtil.toWorld(player.getWorld());
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
