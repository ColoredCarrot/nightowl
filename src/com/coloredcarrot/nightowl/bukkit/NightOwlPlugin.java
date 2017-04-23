package com.coloredcarrot.nightowl.bukkit;

import java.io.File;
import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import com.coloredcarrot.nightowl.NightOwl;
import com.coloredcarrot.nightowl.Server;
import com.coloredcarrot.nightowl.bukkit.commands.BukkitCommandExecutor;
import com.coloredcarrot.nightowl.commands.CommandTeleport;
import com.coloredcarrot.nightowl.lang.Lang;

public class NightOwlPlugin extends JavaPlugin implements NightOwl
{
	
	private BukkitCommandExecutor cmdExecutor;
	
	private Lang lang;
	{
		try { lang = Lang.withDefaultValues(); }
		catch (IOException e) { throw new RuntimeException(e); }
	}
	
	@Override
	public void onEnable()
	{
		
		try { lang = Lang.asParentOfExternal(lang, new File(mkdirsFolder(), "lang.properties")); }
		catch (IOException e)
		{
			e.printStackTrace();
			getLogger().warning("Failed to read language file, using default values");
			// we don't need to set lang here as the above assignment failed, i.e. lang is still the one with the default values loaded in the constructor
		}
		
		cmdExecutor = new BukkitCommandExecutor(lang);
		registerApplicableCommands();
		
	}
	
	private void registerApplicableCommands()
	{
		
		cmdExecutor.registerCommand(new CommandTeleport(this));
		getCommand("teleport").setExecutor(cmdExecutor);
		
	}

	@Override
	public Lang lang()
	{
		return lang;
	}

	@Override
	public Server server()
	{
		return BukkitServer.getInstance();
	}
	
	private File mkdirsFolder()
	{
		File file = getDataFolder();
		file.mkdirs();
		return file;
	}
	
}
