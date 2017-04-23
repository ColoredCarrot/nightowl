package com.coloredcarrot.nightowl.users;

public interface HumanUser extends CommandUser, WorldUser
{
	
	public String getName();
	
	public String getDisplayName();
	
	public void setDisplayName(String displayName);
	
}
