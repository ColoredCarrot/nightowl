package com.coloredcarrot.nightowl.commands;

import com.coloredcarrot.nightowl.NightOwl;
import com.coloredcarrot.nightowl.lang.Lang;
import com.coloredcarrot.nightowl.users.CommandUser;
import com.coloredcarrot.nightowl.users.PermissibleUser;

public abstract class Command
{
	
	private final CommandInfo info;
	protected final NightOwl nightowl;
	protected final Lang lang;
	
	public Command(CommandInfo info, NightOwl nightowl)
	{
		this.info = info;
		this.nightowl = nightowl;
		this.lang = nightowl.lang();
	}
	
	public Command(CommandInfo.Builder infoBuilder, NightOwl nightowl)
	{
		this(infoBuilder.build(), nightowl);
	}

	protected abstract void execute0(CommandUser user, String[] args);
	
	public void execute(CommandUser user, String[] args)
	{
		if (!checkPermission(user))
			user.sendMessage(lang.get(Lang.CMD_PERMS));
		else if (!info.argRangeCheck(args.length))
			user.sendMessage(lang.get(Lang.CMD_USAGE, "%usage%", info.getUsage()));
		else
			try { execute0(user, args); }
			catch (ExecutionException e) { user.sendMessage(e.m); }
	}
	
	public boolean checkPermission(PermissibleUser user)
	{
		return !info.hasPermission() || user.hasPermission(info.getPermission());
	}
	
	public CommandInfo getInfo()
	{
		return info;
	}
	
	public static class ExecutionException extends RuntimeException
	{
		private static final long serialVersionUID = -514072646477495498L;
		private final String[] m;
		public ExecutionException(String[] m)
		{
			this.m = m;
		}
	}
	
}
