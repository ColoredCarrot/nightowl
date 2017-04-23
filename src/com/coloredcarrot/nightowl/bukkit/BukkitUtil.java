package com.coloredcarrot.nightowl.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.coloredcarrot.nightowl.bukkit.users.BukkitConsoleUser;
import com.coloredcarrot.nightowl.bukkit.users.BukkitPlayer;
import com.coloredcarrot.nightowl.bukkit.world.BukkitLocation;

public class BukkitUtil
{
	
	private static BukkitConsoleUser consoleUser;
	
	public static BukkitConsoleUser getConsoleUser()
	{
		return consoleUser;
	}
	
	public static void setConsoleUser(BukkitConsoleUser consoleUser)
	{
		BukkitUtil.consoleUser = consoleUser;
	}
	
	public static org.bukkit.Location toBukkitLocation(com.coloredcarrot.nightowl.world.Location loc)
	{
		return loc instanceof BukkitLocation
				? ((BukkitLocation) loc).bukkit()
				: new org.bukkit.Location(Bukkit.getWorld(loc.getWorld()), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
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
	
}
