package me.iarekylew00t.ircbot.hooks;

import java.util.HashMap;

import me.iarekylew00t.ircbot.IRCBot;
import me.iarekylew00t.ircbot.managers.PluginManager;

import org.pircbotx.hooks.ListenerAdapter;
import org.slf4j.Logger;

public class IRCPlugin extends ListenerAdapter {
	private String _NAME, _VER;
	private HashMap _CMDS;
	private Object[] _PARAMS;
	private IRCBot _BOT = PluginManager.getBot();
	private Logger _LOG = PluginManager.getLogger();
	private boolean _ENABLED = false;
	
	public IRCPlugin(String name, String version, HashMap commands) {
		this(name, version, commands, new Object[0]);
	}
	
	public IRCPlugin(String name, String version, HashMap commands, Object... params) {
		super();
		this._NAME = name;
		this._VER = version;
		this._CMDS = commands;
		this._PARAMS = params;
		try {
			this._LOG.info("Enabling " + name + " v" + version);
			this.onEnable(); //Automatically run onEnable()
			PluginManager.addPlugin(this);
			this._ENABLED = true;
		} catch (Exception ex) {
			this._LOG.warn("Disabling " + name + " v" + version);
			this._LOG.error("" + ex);
			this.onDisable(); //Automatically run onDisable() if an error occurs
			PluginManager.removePlugin(this);
			this._ENABLED = false;
		}
	}
	
	public void onEnable() throws Exception {
	}
	
	public void onDisable() {
	}
	
	public boolean isEnabled() {
		return this._ENABLED;
	}
	
	public String getName() {
		return this._NAME;
	}
	
	public String getVersion() {
		return this._VER;
	}
	
	public HashMap getCommands() {
		return this._CMDS;
	}
	
	public Object getParams() {
		return this._PARAMS;
	}
	
	public Object getParam(int index) {
		return this._PARAMS[index];
	}
	
	public IRCBot getBot() {
		return this._BOT;
	}
	
	public Logger getLogger() {
		return this._LOG;
	}

	public void info(String message) {
		this._LOG.info("[" + this._NAME + "] " + message);
	}
	
	public void debug(String message) {
		this._LOG.debug("[" + this._NAME + "] " + message);
	}
	
	public void error(String message) {
		this._LOG.error("[" + this._NAME + "] " + message);
	}
	
	public void warn(String message) {
		this._LOG.warn("[" + this._NAME + "] " + message);
	}
}
