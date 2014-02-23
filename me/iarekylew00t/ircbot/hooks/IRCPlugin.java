package me.iarekylew00t.ircbot.hooks;

import me.iarekylew00t.ircbot.IRCBot;
import me.iarekylew00t.ircbot.managers.DataManager;
import me.iarekylew00t.ircbot.managers.PluginManager;

import org.pircbotx.hooks.ListenerAdapter;
import org.slf4j.Logger;

public class IRCPlugin extends ListenerAdapter {
	private String PLUGIN, VER;
	private CommandList CMDS;
	private IRCBot BOT = DataManager.getBot();
	private Logger LOG = DataManager.getLogger();
	private boolean ENABLED = false;
	
	public IRCPlugin(String name, String version, CommandList commands) {
		super();
		this.PLUGIN = name;
		this.VER = version;
		this.CMDS = commands;
		try {
			this.LOG.info("Enabling " + name + " v" + version);
			this.onEnable(); //Automatically run onEnable()
			PluginManager.addPlugin(this);
			this.ENABLED = true;
		} catch (Exception ex) {
			this.LOG.warn("Disabling " + name + " v" + version);
			ex.printStackTrace();
			this.onDisable(); //Automatically run onDisable() if an error occurs
			this.ENABLED = false;
		}
	}

	public boolean equals(IRCPlugin plugin) {
		return (this.PLUGIN.equals(plugin.getName()));
	}

	public boolean equals(String plugin) {
		return (this.PLUGIN.equals(plugin));
	}

	public boolean equalsIgnoreCase(IRCPlugin plugin) {
		return (this.PLUGIN.equalsIgnoreCase(plugin.getName()));
	}
	
	public boolean equalsIgnoreCase(String plugin) {
		return (this.PLUGIN.equalsIgnoreCase(plugin));
	}
	
	public void onEnable() throws Exception {
	}
	
	public void onDisable() {
	}
	
	public boolean isEnabled() {
		return this.ENABLED;
	}
	
	public String getName() {
		return this.PLUGIN;
	}
	
	public String getVersion() {
		return this.VER;
	}
	
	public CommandList getCommands() {
		return this.CMDS;
	}

	public void info(String message) {
		this.LOG.info("[" + this.PLUGIN + "] " + message);
	}
	
	public void debug(String message) {
		this.LOG.debug("[" + this.PLUGIN + "] " + message);
	}
	
	public void error(String message) {
		this.LOG.error("[" + this.PLUGIN + "] " + message);
	}
	
	public void warn(String message) {
		this.LOG.warn("[" + this.PLUGIN + "] " + message);
	}
	
	public IRCBot getBot() {
		return this.BOT;
	}
	
	public Logger getLogger() {
		return this.LOG;
	}
	
	@Override
	public String toString() {
		return this.PLUGIN + " (" + this.VER + ")";
	}
}
