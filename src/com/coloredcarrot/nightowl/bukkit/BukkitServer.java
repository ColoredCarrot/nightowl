package com.coloredcarrot.nightowl.bukkit;

import java.util.Collection;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.coloredcarrot.nightowl.Server;
import com.coloredcarrot.nightowl.users.HumanUser;
import com.coloredcarrot.nightowl.world.World;

public class BukkitServer implements Server
{
	
	private static final BukkitServer INSTANCE = new BukkitServer();
	
	public static BukkitServer getInstance()
	{
		return INSTANCE;
	}
	
	private BukkitServer()
	{
	}

	@Override
	public Collection<HumanUser> getOnline()
	{
		return Bukkit.getOnlinePlayers().stream()
					 .map(BukkitUtil::toHumanUser)
					 .collect(Collectors.toList());
	}

	@Override
	public HumanUser getHumanUser(String name)
	{
		Player player = Bukkit.getPlayer(name);
		return player != null ? BukkitUtil.toHumanUser(player) : null;
	}
	
	@Override
	public World getWorld(String name)
	{
		return BukkitUtil.toWorld(Bukkit.getWorld(name));
	}

}
