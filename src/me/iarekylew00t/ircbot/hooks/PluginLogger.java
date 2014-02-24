package me.iarekylew00t.ircbot.hooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PluginLogger {
	private final Logger LOG = LoggerFactory.getLogger(IRCPlugin.class);
	private IRCPlugin PLUGIN;
	
	public PluginLogger(IRCPlugin plugin) {
		this.PLUGIN = plugin;
	}

	public synchronized void info(String message) {
		this.LOG.info("[" + this.PLUGIN.getName() + "] " + message);
	}
	
	public synchronized void debug(String message) {
		this.LOG.debug("[" + this.PLUGIN.getName() + "] " + message);
	}
	
	public synchronized void error(String message) {
		this.LOG.error("[" + this.PLUGIN.getName() + "] " + message);
	}
	
	public synchronized void warn(String message) {
		this.LOG.warn("[" + this.PLUGIN.getName() + "] " + message);
	}
}
