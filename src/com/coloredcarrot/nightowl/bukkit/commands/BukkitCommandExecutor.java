package com.coloredcarrot.nightowl.bukkit.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.coloredcarrot.nightowl.bukkit.BukkitUtil;
import com.coloredcarrot.nightowl.commands.Command;
import com.coloredcarrot.nightowl.lang.Lang;

public class BukkitCommandExecutor implements CommandExecutor
{

	private final Lang lang;
	private final Map<String, Command> commands = new HashMap<>();
	
	public BukkitCommandExecutor(Lang lang)
	{
		this.lang = lang;
	}
	
	public void registerCommand(Command command)
	{
		commands.put(command.getInfo().getName(), command);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args)
	{
		
		String search = label.toLowerCase();
		
		Command command = commands.get(search);
		
		if (command == null)
			command = commands.values().stream().filter(e -> e.getInfo().isAlias(search)).findAny().orElse(null);
		
		if (command == null)
			sender.sendMessage(lang.get(Lang.CMD_UNKNOWN, "%cmd%", label));
		else
			command.execute(BukkitUtil.toCommandUser(sender), args);
		
		return true;
		
	}

}
