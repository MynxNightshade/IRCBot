package me.iarekylew00t.ircbot.hooks;

import java.util.HashMap;

import me.iarekylew00t.ircbot.IRCBot;
import me.iarekylew00t.ircbot.exceptions.PluginException;
import me.iarekylew00t.ircbot.managers.PluginManager;

import org.pircbotx.hooks.ListenerAdapter;
import org.slf4j.Logger;

public class IRCPlugin extends ListenerAdapter {
	private String NAME, VER;
	private HashMap<String, String> CMDS;
	private Object[] PARAMS;
	private IRCBot BOT = PluginManager.getBot();
	private Logger LOG = PluginManager.getLogger();
	private boolean ENABLED = false;
	
	public IRCPlugin(String name, String version, HashMap commands) {
		this(name, version, commands, new Object[0]);
	}
	
	public IRCPlugin(String name, String version, HashMap<String, String> commands, Object... params) {
		super();
		this.NAME = name;
		this.VER = version;
		this.CMDS = commands;
		this.PARAMS = params;
		try {
			this.LOG.info("Enabling " + name + " v" + version);
			this.onEnable(); //Automatically run onEnable()
			PluginManager.addPlugin(this);
			this.ENABLED = true;
		} catch (Exception ex) {
			this.LOG.warn("Disabling " + name + " v" + version);
			this.LOG.error("" + ex);
			this.onDisable(); //Automatically run onDisable() if an error occurs
			try {
				PluginManager.removePlugin(this);
			} catch (PluginException e) { /*No harm*/ }
			this.ENABLED = false;
		}
	}
	
	public void onEnable() throws Exception {
	}
	
	public void onDisable() {
	}
	
	public boolean isEnabled() {
		return this.ENABLED;
	}
	
	public String getName() {
		return this.NAME;
	}
	
	public String getVersion() {
		return this.VER;
	}
	
	public HashMap getCommands() {
		return this.CMDS;
	}
	
	public Object getParams() {
		return this.PARAMS;
	}
	
	public Object getParam(int index) {
		return this.PARAMS[index];
	}
	
	public IRCBot getBot() {
		return this.BOT;
	}
	
	public Logger getLogger() {
		return this.LOG;
	}

	public void info(String message) {
		this.LOG.info("[" + this.NAME + "] " + message);
	}
	
	public void debug(String message) {
		this.LOG.debug("[" + this.NAME + "] " + message);
	}
	
	public void error(String message) {
		this.LOG.error("[" + this.NAME + "] " + message);
	}
	
	public void warn(String message) {
		this.LOG.warn("[" + this.NAME + "] " + message);
	}
	
	@Override
	public String toString() {
		return this.NAME + " (" + this.VER + ")";
	}
}
