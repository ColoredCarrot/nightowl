package com.coloredcarrot.nightowl.commands;

import com.coloredcarrot.nightowl.NightOwl;
import com.coloredcarrot.nightowl.commands.flags.FlagArgsParser;
import com.coloredcarrot.nightowl.commands.flags.FlagArgsParser.ParseResult;
import com.coloredcarrot.nightowl.lang.Lang;
import com.coloredcarrot.nightowl.users.CommandUser;
import com.coloredcarrot.nightowl.users.WorldUser;
import com.coloredcarrot.nightowl.world.World;

// Extend FlaggableCommand for -w world
public class KillallCommand extends FlaggableCommand
{

	public KillallCommand(NightOwl nightowl)
	{
		super(buildInfo		("killall")
			  .aliases		("killentities")
			  .argRangeCheck(n -> n >= 1)
			  .permission	("nightowl.cmd.killall")
			  .usage		("/killall [type]"),
			  new FlagArgsParser()
			  .withValueFlags	("-w", "--world"),
			  nightowl);
		
	}

	@Override
	protected void execute(CommandUser user, String[] args, ParseResult flags)
	{
		
		World w;
		if (user instanceof WorldUser)
			if (!flags.has("-w", "--world"))
				w = ((WorldUser) user).getWorld();
			else
			{
				w = server.getWorld(flags.getString("-w", "--world"));
				if (w == null)
				{
					user.sendMessage(lang(Lang.CMD_WORLDNOTFOUND, "%world%", flags.getString("-w", "--world")));
					w = ((WorldUser) user).getWorld();
				}
			}
		
		else if (!flags.has("-w", "--world"))
			throw errMissingConsoleFlag("-w", "--world");
		
		else
		{
			w = server.getWorld(flags.getString("-w", "--world"));
			if (w == null)
			{
				user.sendMessage(lang(Lang.CMD_WORLDNOTFOUND, "%world%", flags.getString("-w", "--world")));
				return;
			}
		}
		
		
		
	}

}
