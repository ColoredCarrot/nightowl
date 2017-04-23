package com.coloredcarrot.nightowl.lib;

public class Args
{
	
	public static void inRange(int x, int min, int max)
	{
		if (x < min || x > max)
			throw new IndexOutOfBoundsException();
	}
	
	public static void inRange(int x, int min, int max, String m)
	{
		if (x < min || x > max)
			throw new IndexOutOfBoundsException(m);
	}
	
	public static void even(int x)
	{
		if (x % 2 != 0)
			throw new IllegalArgumentException();
	}
	
	public static void even(int x, String m)
	{
		if (x % 2 != 0)
			throw new IllegalArgumentException(m);
	}
	
}
