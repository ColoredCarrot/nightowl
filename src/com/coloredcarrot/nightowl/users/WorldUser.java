package com.coloredcarrot.nightowl.users;

import com.coloredcarrot.nightowl.world.Location;

public interface WorldUser extends User
{
	
	public Location getLocation();
	
	public void setLocation(double x, double y, double z);
	
	public void setLocation(double x, double y, double z, float yaw, float pitch);
	
	public void setLocation(Location loc);
	
}
