package com.coloredcarrot.nightowl.users;

public interface PermissibleUser extends User
{
	
	public boolean hasPermission(String permission);
	
	public boolean givePermission(String permission);
	
	public boolean takePermission(String permission);
	
}
