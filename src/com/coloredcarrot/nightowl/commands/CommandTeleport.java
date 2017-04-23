package com.coloredcarrot.nightowl.commands;

import com.coloredcarrot.nightowl.NightOwl;
import com.coloredcarrot.nightowl.lang.Lang;
import com.coloredcarrot.nightowl.lib.DecimalFormats;
import com.coloredcarrot.nightowl.users.CommandUser;
import com.coloredcarrot.nightowl.users.HumanUser;

public class CommandTeleport extends CommandAdapter
{

	public CommandTeleport(NightOwl nightowl)
	{
		super(buildInfo("teleport")
			  .aliases		("tp")
			  .argRangeCheck(n -> n >= 1 && n <= 6)
			  .permission	("nightowl.cmd.teleport")
			  .usage		("/tp <x y z> OR /tp <player> [player OR x y z]"),
			  nightowl);
	}

	@Override
	public void execute0(CommandUser user, String[] args)
	{
		
		if (args.length == 1)
		{
			// to player
			
			HumanUser human = requireHumanUser(user);
			HumanUser target = requireTarget(args[0]);
			
			human.setLocation(target.getLocation());
			human.sendMessage(lang(Lang.CMD_TELEPORT_SUCCESS_TP, "%name%", target.getName()));
			
		}
		else if (args.length == 2)
		{
			// player to player
			
			HumanUser targetTeleporter = requireTarget(args[0]);
			HumanUser target = requireTarget(args[1]);
			
			targetTeleporter.setLocation(target.getLocation());
			
			user.sendMessage(lang(Lang.CMD_TELEPORT_SUCCESS_PTP, "%teleporter%", targetTeleporter.getName(), "%target%", target.getName()));
			
		}
		else if (args.length == 3)
		{
			// to simple location
			
			HumanUser human = requireHumanUser(user);
			
			double x = args[0].equals("~") ? human.getLocation().getX() : requireDouble(args[0]);
			double y = args[1].equals("~") ? human.getLocation().getY() : requireDouble(args[1]);
			double z = args[2].equals("~") ? human.getLocation().getZ() : requireDouble(args[2]);
			
			human.setLocation(x, y, z);
			human.sendMessage(lang(Lang.CMD_TELEPORT_SUCCESS_TL, "%x%", DecimalFormats.DEFAULT.format(x),
																 "%y%", DecimalFormats.DEFAULT.format(y),
																 "%z%", DecimalFormats.DEFAULT.format(z)));
			
		}
		else if (args.length == 4)
		{
			// player to simple location
			
			HumanUser target = requireTarget(args[0]);
			
			double x = args[1].equals("~") ? target.getLocation().getX() : requireDouble(args[1]);
			double y = args[2].equals("~") ? target.getLocation().getY() : requireDouble(args[2]);
			double z = args[3].equals("~") ? target.getLocation().getZ() : requireDouble(args[3]);
			
			target.setLocation(x, y, z);
			target.sendMessage(lang(Lang.CMD_TELEPORT_SUCCESS_PTL, "%x%", DecimalFormats.DEFAULT.format(x),
																   "%y%", DecimalFormats.DEFAULT.format(y),
																   "%z%", DecimalFormats.DEFAULT.format(z)));
			
		}
		else if (args.length == 5)
		{
			// to full location
			
			HumanUser human = requireHumanUser(user);
			
			double x     = args[0].equals("~") ? human.getLocation().getX() : requireDouble(args[0]);
			double y     = args[1].equals("~") ? human.getLocation().getY() : requireDouble(args[1]);
			double z     = args[2].equals("~") ? human.getLocation().getZ() : requireDouble(args[2]);
			float  yaw   = args[3].equals("~") ? human.getLocation().getYaw() : requireFloat(args[3]);
			float  pitch = args[4].equals("~") ? human.getLocation().getPitch() : requireFloat(args[4]);
			
			human.setLocation(x, y, z, yaw, pitch);
			human.sendMessage(lang(Lang.CMD_TELEPORT_SUCCESS_TF, "%x%", DecimalFormats.DEFAULT.format(x),
																 "%y%", DecimalFormats.DEFAULT.format(y),
																 "%z%", DecimalFormats.DEFAULT.format(z),
																 "%yaw%", DecimalFormats.DEFAULT.format(yaw),
																 "%pitch%", DecimalFormats.DEFAULT.format(pitch)));
			
		}
		else if (args.length == 6)
		{
			// player to full location
			
			HumanUser target = requireTarget(args[0]);
			
			double x     = args[1].equals("~") ? target.getLocation().getX() : requireDouble(args[1]);
			double y     = args[2].equals("~") ? target.getLocation().getY() : requireDouble(args[2]);
			double z     = args[3].equals("~") ? target.getLocation().getZ() : requireDouble(args[3]);
			float  yaw   = args[4].equals("~") ? target.getLocation().getYaw() : requireFloat(args[4]);
			float  pitch = args[5].equals("~") ? target.getLocation().getPitch() : requireFloat(args[5]);
			
			target.setLocation(x, y, z, yaw, pitch);
			user.sendMessage(lang(Lang.CMD_TELEPORT_SUCCESS_PTF, "%x%", DecimalFormats.DEFAULT.format(x),
																 "%y%", DecimalFormats.DEFAULT.format(y),
																 "%z%", DecimalFormats.DEFAULT.format(z),
																 "%yaw%", DecimalFormats.DEFAULT.format(yaw),
																 "%pitch%", DecimalFormats.DEFAULT.format(pitch),
																 "%teleporter%", target.getName()));
			
		}
		
	}

}
