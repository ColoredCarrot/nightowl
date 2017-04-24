package com.coloredcarrot.nightowl.lang;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;

import com.coloredcarrot.nightowl.NightOwl;
import com.coloredcarrot.nightowl.lib.Args;

public class Lang
{
	
	public static final String CMD_USAGE				= "cmd.usage",
							   CMD_PERMS				= "cmd.perms",
							   CMD_NONHUMAN				= "cmd.nonhuman",
							   CMD_TARGETNOTFOUND		= "cmd.targetnotfound",
							   CMD_DOUBLEERR			= "cmd.doubleerr",
							   CMD_UNKNOWN				= "cmd.unknown",
							   CMD_MISSINGFLAG_CONSOLE	= "cmd.missingflag.console",
							   CMD_WORLDNOTFOUND		= "cmd.worldnotfound",
							   
							   CMD_TELEPORT_SUCCESS_TP	= "cmd.teleport.success.tp",
							   CMD_TELEPORT_SUCCESS_PTP	= "cmd.teleport.success.ptp",
							   CMD_TELEPORT_SUCCESS_TL	= "cmd.teleport.success.tl",
							   CMD_TELEPORT_SUCCESS_PTL	= "cmd.teleport.success.ptl",
							   CMD_TELEPORT_SUCCESS_TF	= "cmd.teleport.success.tf",
							   CMD_TELEPORT_SUCCESS_PTF	= "cmd.teleport.success.ptf";
	
	private Properties props;
	
	public String[] get(String key)
	{
		// Split by double-backslash (\\)
		return props.getProperty(key).split("\\\\\\\\");
	}
	
	public String[] get(String key, Object... vars)
	{
		
		Args.even(vars.length);
		
		String[] r = get(key);
		
		for (int i = 0; i < r.length; i++)
			for (int j = 0; j < vars.length - 1;)
				r[i] = r[i].replace((String) vars[j++], String.valueOf(vars[j++]));
		
		return r;
		
	}
	
	public static Lang withDefaultValues() throws IOException
	{
		
		Lang lang = new Lang();
		lang.props = new Properties();
		
		try (InputStream in = NightOwl.class.getResourceAsStream("/resources/lang.properties"))
		{
			
			// load the defaults, also using that fcking workaround
			
			StringBuilder sb = new StringBuilder();
			
			int ch;
			while ((ch = in.read()) != -1)
				sb.append((char) ch);
			
			lang.props.load(new StringReader(sb.toString().replace("\\", "\\\\")));
			
		}
		
		return lang;
		
	}
	
	public static Lang asParentOfExternal(Lang asParent, File file) throws IOException
	{
		
		Lang lang = new Lang();
		lang.props = new Properties(asParent.props);
		
		StringBuilder sb = new StringBuilder();
		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file)))
		{
			int ch;
			while ((ch = in.read()) != -1)
				sb.append((char) ch);
		}
		
		lang.props.load(new StringReader(sb.toString().replace("\\", "\\\\")));
		
		return lang;
		
	}
	
}
