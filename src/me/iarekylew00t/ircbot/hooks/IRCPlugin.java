package me.iarekylew00t.ircbot.hooks;

import java.util.UUID;

import org.pircbotx.hooks.ListenerAdapter;

public class IRCPlugin extends ListenerAdapter {
	private String PLUGIN, VER, ID, AUTHOR;
	private CommandList CMDS;
	private PluginLogger LOG;
	private boolean ENABLED = false;
	
	public IRCPlugin(String name, String version, CommandList commands, String author) {
		super();
		this.LOG = new PluginLogger(this);
		this.PLUGIN = name;
		this.VER = version;
		this.CMDS = commands;
		this.AUTHOR = author;
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
				this.log().warn("Error disabling " + name + " v" + version);
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
	
	public String getAuthor() {
		return this.AUTHOR;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public PluginLogger log() {
		return this.LOG;
	}

	public boolean equals(IRCPlugin plugin) {
		return this.PLUGIN.equalsIgnoreCase(plugin.getName());
	}

	public boolean equals(String plugin) {
		return (this.PLUGIN.equalsIgnoreCase(plugin));
	}
	
	@Override
	public String toString() {
		return this.PLUGIN + " (" + this.VER + ")";
	}
}
