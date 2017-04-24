package com.coloredcarrot.nightowl.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.coloredcarrot.nightowl.bukkit.users.BukkitConsoleUser;
import com.coloredcarrot.nightowl.bukkit.users.BukkitPlayer;
import com.coloredcarrot.nightowl.bukkit.world.BukkitLocation;
import com.coloredcarrot.nightowl.bukkit.world.BukkitWorld;

public class BukkitUtil
{
	
	private static final BukkitConsoleUser consoleUser = new BukkitConsoleUser();
	
	public static BukkitConsoleUser getConsoleUser()
	{
		return consoleUser;
	}
	
	public static org.bukkit.World toBukkitWorld(com.coloredcarrot.nightowl.world.World world)
	{
		return world instanceof BukkitWorld
				? ((BukkitWorld) world).bukkit()
				: Bukkit.getWorld(world.getName());
	}
	
	public static org.bukkit.Location toBukkitLocation(com.coloredcarrot.nightowl.world.Location loc)
	{
		return loc instanceof BukkitLocation
				? ((BukkitLocation) loc).bukkit()
				: new org.bukkit.Location(BukkitUtil.toBukkitWorld(loc.getWorld()), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
	}
	
	public static com.coloredcarrot.nightowl.users.HumanUser toHumanUser(org.bukkit.entity.Player player)
	{
		return BukkitPlayer.get(player);
	}
	
	public static com.coloredcarrot.nightowl.users.CommandUser toCommandUser(org.bukkit.command.CommandSender commandSender)
	{
		if (commandSender instanceof Player)
			return toHumanUser((Player) commandSender);
		else if (commandSender instanceof ConsoleCommandSender || commandSender instanceof BlockCommandSender)
			return getConsoleUser();
		else
			throw new IllegalArgumentException("NYI");//TODO: fix this
	}
	
	public static com.coloredcarrot.nightowl.world.World toWorld(org.bukkit.World world)
	{
		return new BukkitWorld(world);
	}
	
	public static com.coloredcarrot.nightowl.world.Location toLocation(org.bukkit.Location loc)
	{
		return new BukkitLocation(loc);
	}
	
}
