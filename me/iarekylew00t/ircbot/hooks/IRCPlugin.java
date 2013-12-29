package me.iarekylew00t.ircbot.hooks;

import java.util.HashMap;

import me.iarekylew00t.ircbot.IRCBot;
import me.iarekylew00t.ircbot.PluginManager;

import org.pircbotx.hooks.ListenerAdapter;
import org.slf4j.Logger;

public class IRCPlugin extends ListenerAdapter {
	private static String _NAME, _VER;
	private static HashMap _CMDS;
	private static Object[] _PARAMS;
	private static IRCBot _BOT = PluginManager.getBot();
	private static Logger _LOG = PluginManager.getLogger();
	private static boolean _ENABLED = false;
	
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
			_LOG.info("Enabling " + name + "v" + version);
			this.onEnable(); //Automatically run onEnable()
			PluginManager.addPlugin(this);
			_ENABLED = true;
		} catch (Exception ex) {
			_LOG.warn("Disabling " + name + "v" + version);
			_LOG.error("" + ex);
			this.onDisable(); //Automatically run onDisable() if an error occurs
			PluginManager.removePlugin(this);
			_ENABLED = false;
		}
	}
	
	public void onEnable() {
	}
	
	public void onDisable() {
	}
	
	public boolean isEnabled() {
		return _ENABLED;
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
	
	public IRCBot getBot() {
		return _BOT;
	}
	
	public Logger getLogger() {
		return _LOG;
	}
}
