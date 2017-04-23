package com.coloredcarrot.nightowl.users;

import java.util.HashSet;
import java.util.Set;

public class PermissionSet
{
	
	private final Set<String> permissions = new HashSet<>();
	
	public boolean has(String permission)
	{
		return permissions.contains(permission);
	}
	
	public boolean give(String permission)
	{
		return permissions.add(permission);
	}
	
	public boolean take(String permission)
	{
		return permissions.remove(permission);
	}
	
	public boolean clear()
	{
		if (permissions.isEmpty())
			return false;
		permissions.clear();
		return true;
	}
	
}
