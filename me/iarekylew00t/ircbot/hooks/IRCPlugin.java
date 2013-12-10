package me.iarekylew00t.ircbot.hooks;

import java.util.HashMap;

import me.iarekylew00t.ircbot.PluginManager;

import org.pircbotx.hooks.ListenerAdapter;

public class IRCPlugin extends ListenerAdapter {
	private static String _NAME, _VER;
	private static HashMap _CMDS;
	private static Object[] _PARAMS;
	private static boolean isEnabled = false;
	
	public IRCPlugin(String name, String version, HashMap commands) {
		this(name, version, commands, new Object[0]);
	}
	
	public IRCPlugin(String name, String version, HashMap commands, Object... params) {
		super();
		_NAME = name;
		_VER = version;
		_CMDS = commands;
		_PARAMS = params;
		try {
			this.onEnable(); //Automatically run onEnable()
			//TODO Display Startup info
			PluginManager.addPlugin(this);
			isEnabled = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			this.onDisable(); //Automatically run onDisable() if an error occurs
			//TODO Display Shutdown info 
			PluginManager.removePlugin(this);
			isEnabled = false;
		}
	}
	
	public void onEnable() {
	}
	
	public void onDisable() {
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public String getName() {
		return _NAME;
	}
	
	public String getVersion() {
		return _VER;
	}
	
	public HashMap getCommands() {
		return _CMDS;
	}
	
	public Object getParams() {
		return _PARAMS;
	}
	
	public Object getParam(int index) {
		return _PARAMS[index];
	}
}
