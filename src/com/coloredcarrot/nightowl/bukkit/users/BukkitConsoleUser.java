package com.coloredcarrot.nightowl.bukkit.users;

import org.bukkit.Bukkit;

import com.coloredcarrot.nightowl.users.CommandUser;

public class BukkitConsoleUser implements CommandUser
{

	@Override
	public boolean hasPermission(String permission)
	{
		return true;
	}

	@Override
	public boolean givePermission(String permission)
	{
		return false;
	}

	@Override
	public boolean takePermission(String permission)
	{
		return false;
	}

	@Override
	public void sendMessage(String m)
	{
		Bukkit.getConsoleSender().sendMessage(m);
	}
	
	@Override
	public void sendMessage(String... m)
	{
		Bukkit.getConsoleSender().sendMessage(m);
	}
	
}
