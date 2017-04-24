package com.coloredcarrot.nightowl;

import java.util.Collection;

import com.coloredcarrot.nightowl.users.HumanUser;
import com.coloredcarrot.nightowl.world.World;

public interface Server
{
	
	public Collection<HumanUser> getOnline();
	
	public HumanUser getHumanUser(String name);
	
	public World getWorld(String name);
	
}
