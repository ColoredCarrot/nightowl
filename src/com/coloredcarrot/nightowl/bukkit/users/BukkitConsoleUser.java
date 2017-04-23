package com.coloredcarrot.nightowl.bukkit.users;

import java.util.logging.Logger;

import com.coloredcarrot.nightowl.users.CommandUser;

public class BukkitConsoleUser implements CommandUser
{
	
	private final Logger log;

	public BukkitConsoleUser(Logger log)
	{
		this.log = log;
	}

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
		log.info(m);
	}

}
