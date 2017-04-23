package com.coloredcarrot.nightowl;

import java.util.Collection;

import com.coloredcarrot.nightowl.users.HumanUser;

public interface Server
{
	
	public Collection<HumanUser> getOnline();
	
	public HumanUser getHumanUser(String name);
	
}
