package com.coloredcarrot.nightowl.commands;

import java.util.StringJoiner;

import com.coloredcarrot.nightowl.NightOwl;
import com.coloredcarrot.nightowl.commands.CommandInfo.Builder;
import com.coloredcarrot.nightowl.commands.flags.FlagArgsParser;
import com.coloredcarrot.nightowl.lang.Lang;
import com.coloredcarrot.nightowl.users.CommandUser;

public abstract class FlaggableCommand extends CommandAdapter
{
	
	private final FlagArgsParser parser;

	public FlaggableCommand(Builder infoBuilder, FlagArgsParser parser, NightOwl nightowl)
	{
		super(infoBuilder, nightowl);
		this.parser = parser;
	}

	public FlaggableCommand(Builder infoBuilder, NightOwl nightowl)
	{
		super(infoBuilder, nightowl);
		this.parser = new FlagArgsParser();
	}

	@Override
	protected void execute0(CommandUser user, String[] args)
	{
		FlagArgsParser.ParseResult parseRes = parser.parse(args);
		execute(user, parseRes.getNormalArgs(), parseRes);
	}
	
	protected RuntimeException errMissingConsoleFlag(String flag)
	{
		throw new ExecutionException(lang(Lang.CMD_MISSINGFLAG_CONSOLE, "%flags%", flag));
	}
	
	protected RuntimeException errMissingConsoleFlag(String flag, String... aliases)
	{
		StringJoiner sj = new StringJoiner("|");
		sj.add(flag);
		for (String alias : aliases)
			sj.add(alias);
		throw new ExecutionException(lang(Lang.CMD_MISSINGFLAG_CONSOLE, "%flags%", sj));
	}
	
	protected abstract void execute(CommandUser user, String[] args, FlagArgsParser.ParseResult flags);

}
