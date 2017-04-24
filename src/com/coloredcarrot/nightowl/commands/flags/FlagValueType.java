package com.coloredcarrot.nightowl.commands.flags;

public enum FlagValueType
{
	
	STRING
	{
		@Override
		public boolean canParse(String s)
		{
			return true;
		}
		@Override
		public Object parse(String s)
		{
			return s;
		}
	},
	INTEGER
	{
		@Override
		public boolean canParse(String s)
		{
			try { Integer.parseInt(s); return true; }
			catch (NumberFormatException e) { return false; }
		}
		@Override
		public Object parse(String s)
		{
			return Integer.parseInt(s);
		}
	},
	DOUBLE
	{
		@Override
		public boolean canParse(String s)
		{
			try { Double.parseDouble(s); return true; }
			catch (NumberFormatException e) { return false; }
		}
		@Override
		public Object parse(String s)
		{
			return Double.parseDouble(s);
		}
	};
	
	public abstract boolean canParse(String s);
	public abstract Object parse(String s);
	
}
