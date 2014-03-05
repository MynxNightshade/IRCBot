package me.iarekylew00t.ircbot.hooks;

import java.util.UUID;

import me.iarekylew00t.ircbot.Command;
import me.iarekylew00t.ircbot.CommandSender;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public abstract class IRCPlugin extends ListenerAdapter {
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
		} catch (Exception eException) {
			eException.printStackTrace();
			try {
				PluginManager.removePlugin(this);
				this.onDisable(); //Automatically run onDisable() if an error occurs
				this.ENABLED = false;
			} catch (NullPointerException dException) { /*Plugin was already removed*/ }
		}
	}
	
	//WORK IN PROGRESS
	@Override
	public void onMessage(MessageEvent e) {
		if (e.getMessage().startsWith("$")) {
			Command cmd = new Command(e.getMessage());
			CommandSender sender = new CommandSender(e.getUser(), e.getChannel());
			if (cmd.isValid()) {
				this.onCommand(cmd, sender, cmd.getArgs());
				return;
			}
			e.respond("'" + cmd.getName() + "' is not a valid command.");
			return;
		}
	}
	
	public abstract void onCommand(Command command, CommandSender sender, String[] args);
	public abstract void onEnable();
	public abstract void onDisable();
	
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
