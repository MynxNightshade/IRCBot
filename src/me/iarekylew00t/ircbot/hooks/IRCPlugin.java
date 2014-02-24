package me.iarekylew00t.ircbot.hooks;

import java.util.UUID;

import org.pircbotx.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IRCPlugin extends ListenerAdapter {
	private String PLUGIN, VER;
	private CommandList CMDS;
	private Logger LOG = LoggerFactory.getLogger(IRCPlugin.class);
	private boolean ENABLED = false;
	private String ID;
	
	public IRCPlugin(String name, String version, CommandList commands) {
		super();
		this.PLUGIN = name;
		this.VER = version;
		this.CMDS = commands;
		this.ID = UUID.randomUUID().toString().split("-")[0]; //Generate a random ID
		try {
			this.onEnable(); //Automatically run onEnable()
			PluginManager.addPlugin(this);
			this.ENABLED = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				PluginManager.removePlugin(this);
				this.onDisable(); //Automatically run onDisable() if an error occurs
				this.ENABLED = false;
			} catch (NullPointerException ex1) {
				//Plugin was already removed
			} catch (Exception ex1) {
				ex1.printStackTrace();
				this.warn("Error disabling " + name + " v" + version);
			}
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
		return this.PLUGIN;
	}
	
	public String getVersion() {
		return this.VER;
	}
	
	public CommandList getCommands() {
		return this.CMDS;
	}
	
	public String getID() {
		return this.ID;
	}

	public synchronized void info(String message) {
		this.LOG.info("[" + this.PLUGIN + "] " + message);
	}
	
	public synchronized void debug(String message) {
		this.LOG.debug("[" + this.PLUGIN + "] " + message);
	}
	
	public synchronized void error(String message) {
		this.LOG.error("[" + this.PLUGIN + "] " + message);
	}
	
	public synchronized void warn(String message) {
		this.LOG.warn("[" + this.PLUGIN + "] " + message);
	}

	public boolean equals(IRCPlugin plugin) {
		return this.PLUGIN.equalsIgnoreCase(plugin.getName());
	}

	public boolean equals(String plugin) {
		return (this.PLUGIN.equalsIgnoreCase(plugin));
	}
	
	public Logger getLogger() {
		return this.LOG;
	}
	
	@Override
	public String toString() {
		return this.PLUGIN + " (" + this.VER + ")";
	}
}
