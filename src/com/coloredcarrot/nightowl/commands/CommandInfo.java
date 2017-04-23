package com.coloredcarrot.nightowl.commands;

import com.coloredcarrot.nightowl.lib.IntBooleanFunction;

public class CommandInfo
{
	
	private final String name;
	private final String[] aliases;
	private final String permission;
	private final String usage;
	private final IntBooleanFunction rangeCheck;
	
	public CommandInfo(String name, String[] aliases, String permission, String usage, IntBooleanFunction rangeCheck)
	{
		this.name = name;
		this.aliases = aliases;
		this.permission = permission;
		this.usage = usage;
		this.rangeCheck = rangeCheck;
	}
	
	public String getName()
	{
		return name;
	}

	public String[] getAliases()
	{
		return aliases;
	}
	
	public boolean hasAliases()
	{
		return aliases != null && aliases.length != 0;
	}

	public String getPermission()
	{
		return permission;
	}
	
	public boolean hasPermission()
	{
		return permission != null;
	}

	public String getUsage()
	{
		return usage;
	}
	
	public boolean hasUsage()
	{
		return usage != null;
	}

	public IntBooleanFunction getArgRangeCheck()
	{
		return rangeCheck;
	}
	
	public boolean hasArgRangeCheck()
	{
		return rangeCheck != null;
	}
	
	public boolean argRangeCheck(int nArgs)
	{
		return !hasArgRangeCheck() || getArgRangeCheck().execute(nArgs);
	}
	
	public boolean isAlias(String search)
	{
		for (String alias : aliases)
			if (alias.equals(search))
				return true;
		return false;
	}

	public static Builder builder(String name)
	{
		return new Builder(name);
	}
	
	public static class Builder
	{
		
		private final String name;
		private String[] aliases;
		private String permission;
		private String usage;
		private IntBooleanFunction rangeCheck;
		
		private Builder(String name)
		{
			this.name = name;
		}
		
		public CommandInfo build()
		{
			return new CommandInfo(name, aliases, permission, usage, rangeCheck);
		}
		
		public Builder aliases(String... aliases)
		{
			this.aliases = aliases;
			return this;
		}
		
		public Builder permission(String permission)
		{
			this.permission = permission;
			return this;
		}
		
		public Builder usage(String usage)
		{
			this.usage = usage;
			return this;
		}
		
		public Builder argRangeCheck(IntBooleanFunction argRangeCheck)
		{
			this.rangeCheck = argRangeCheck;
			return this;
		}
		
	}
	
}
