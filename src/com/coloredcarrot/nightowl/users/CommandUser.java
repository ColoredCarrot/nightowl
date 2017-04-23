package com.coloredcarrot.nightowl.users;

public interface CommandUser extends PermissibleUser
{
	
	public void sendMessage(String m);
	
	public default void sendMessage(String... m)
	{
		for (String e : m)
			sendMessage(e);
	}
	
}
