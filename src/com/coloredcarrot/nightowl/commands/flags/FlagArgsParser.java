package com.coloredcarrot.nightowl.commands.flags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlagArgsParser
{
	
	private Set<String> booleanFlags;
	private Map<String, FlagValueType> valueFlags;
	
	public FlagArgsParser(Set<String> booleanFlags, Map<String, FlagValueType> valueFlags)
	{
		this.booleanFlags = booleanFlags;
		this.valueFlags = valueFlags;
	}
	
	public FlagArgsParser()
	{
		booleanFlags = new HashSet<>();
		valueFlags = new HashMap<>();
	}
	
	public FlagArgsParser(String... booleanFlags)
	{
		this.booleanFlags = new HashSet<>(Arrays.asList(booleanFlags));
		valueFlags = new HashMap<>();
	}
	
	public FlagArgsParser withValueFlags(Object... valueFlags)
	{
		for (int i = 0; i < valueFlags.length - 1;)
			if (valueFlags[i + 1] instanceof FlagValueType)
				this.valueFlags.put(String.valueOf(valueFlags[i++]), (FlagValueType) valueFlags[i++]);
		clean();
		return this;
	}
	
	public void clean()
	{
		booleanFlags.forEach(valueFlags::remove);
	}
	
	public ParseResult parse(String[] args)
	{
		StringJoiner sj = new StringJoiner(" ");
		for (String arg : args)
			sj.add(arg);
		return parse(sj.toString());
	}
	
	public ParseResult parse(String s)
	{
		// Split string by spaces, leave quoted strings
		//  e.g. "hello \"beautiful world\"" -> ["hello", "beautiful world"]
		
		List<String> res = new ArrayList<>();
		Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(s);
		while (m.find())
			res.add(m.group(1).replace("\"", ""));  // also remove quotes
		
		return parse0(res.toArray(new String[res.size()]));
	}
	
	private ParseResult parse0(String[] args)
	{
		
		final Set<String> foundBooleanFlags = new HashSet<>();
		final Map<String, Object> foundValidValueFlags = new HashMap<>();
		final List<String> restArgs = new ArrayList<>();
		
		for (String booleanFlag : booleanFlags)
			for (String arg : args)
				if (arg.equals(booleanFlag))
					foundBooleanFlags.add(booleanFlag);
				else
					restArgs.add(arg);
		
		for (Map.Entry<String, FlagValueType> valueFlag : valueFlags.entrySet())
			for (int i = 0; i < args.length - 1; i++)
				if (args[i].equals(valueFlag.getKey()) && valueFlag.getValue().canParse(args[i + 1]))
					foundValidValueFlags.put(valueFlag.getKey(), valueFlag.getValue().parse(args[++i]));
				else
					restArgs.add(args[i]);
		restArgs.add(args[args.length - 1]);
		
		return new ParseResult(foundBooleanFlags, foundValidValueFlags, restArgs.toArray(new String[restArgs.size()]));
		
	}
	
	public class ParseResult
	{
		
		private final Set<String> foundBooleanFlags;
		private final Map<String, Object> foundValidValueFlags;
		private final String[] restArgs;
		
		public ParseResult(Set<String> foundBooleanFlags, Map<String, Object> foundValidValueFlags, String[] restArgs)
		{
			this.foundBooleanFlags = foundBooleanFlags;
			this.foundValidValueFlags = foundValidValueFlags;
			this.restArgs = restArgs;
		}

		public boolean is(String booleanFlag)
		{
			return foundBooleanFlags.contains(booleanFlag);
		}
		
		public boolean has(String valueFlag)
		{
			return foundValidValueFlags.containsKey(valueFlag);
		}
		
		public boolean has(String valueFlag, String... aliases)
		{
			if (has(valueFlag))
				return true;
			for (String alias : aliases)
				if (has(alias))
					return true;
			return false;
		}
		
		public Object get(String valueFlag)
		{
			return foundValidValueFlags.get(valueFlag);
		}
		
		public Object get(String valueFlag, String... aliases)
		{
			if (has(valueFlag))
				return get(valueFlag);
			for (String alias : aliases)
				if (has(alias))
					return get(alias);
			return null;
		}
		
		public String getString(String valueFlag)
		{
			return get(valueFlag).toString();
		}
		
		public String getString(String valueFlag, String... aliases)
		{
			return get(valueFlag, aliases).toString();
		}
		
		public int getInt(String valueFlag)
		{
			return ((Number) get(valueFlag)).intValue();
		}
		
		public double getDouble(String valueFlag)
		{
			return ((Number) get(valueFlag)).doubleValue();
		}
		
		public String[] getNormalArgs()
		{
			return restArgs;
		}
		
	}
	
}
