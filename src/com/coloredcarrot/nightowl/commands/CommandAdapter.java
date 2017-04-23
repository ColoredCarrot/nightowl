package com.coloredcarrot.nightowl.commands;

import com.coloredcarrot.nightowl.NightOwl;
import com.coloredcarrot.nightowl.Server;
import com.coloredcarrot.nightowl.commands.CommandInfo.Builder;
import com.coloredcarrot.nightowl.lang.Lang;
import com.coloredcarrot.nightowl.users.HumanUser;
import com.coloredcarrot.nightowl.users.User;

public abstract class CommandAdapter extends Command
{
	
	protected final Server server;

	public CommandAdapter(Builder infoBuilder, NightOwl nightowl)
	{
		super(infoBuilder, nightowl);
		server = nightowl.server();
	}
	
	protected static CommandInfo.Builder buildInfo(String name)
	{
		return CommandInfo.builder(name);
	}
	
	protected String[] lang(String key)
	{
		return lang.get(key);
	}
	
	protected String[] lang(String key, Object... vars)
	{
		return lang.get(key, vars);
	}
	
	protected void errInvalidArg(String[] m)
	{
		throw new ExecutionException(m);
	}
	
	protected void errNonHuman()
	{
		throw new ExecutionException(lang(Lang.CMD_NONHUMAN));
	}
	
	protected void errTargetNotFound(String supposedTargetName)
	{
		throw new ExecutionException(lang(Lang.CMD_TARGETNOTFOUND, "%name%", supposedTargetName));
	}
	
	protected HumanUser requireHumanUser(User user)
	{
		if (!(user instanceof HumanUser))
			errNonHuman();
		return (HumanUser) user;
	}
	
	protected HumanUser requireTarget(String name)
	{
		HumanUser target = server.getHumanUser(name);
		if (target == null)
			errTargetNotFound(name);
		return target;
	}
	
	protected double requireDouble(String arg)
	{
		try { return Double.parseDouble(arg); }
		catch (NumberFormatException e) { throw new ExecutionException(lang(Lang.CMD_DOUBLEERR, "%number%", arg)); }
	}
	
	protected float requireFloat(String arg)
	{
		try { return Float.parseFloat(arg); }
		catch (NumberFormatException e) { throw new ExecutionException(lang(Lang.CMD_DOUBLEERR, "%number%", arg)); }
	}

}
