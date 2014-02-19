package me.iarekylew00t.ircbot;

import me.iarekylew00t.ircbot.managers.PluginManager;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IRCBot extends PircBotX {
	private String VER = "1.0.0";
	private final Logger LOG = LoggerFactory.getLogger(IRCBot.class);

	public IRCBot(Configuration configuration) {
		super(configuration);
		new PluginManager(this);
	}
	
	public void setVersion(String version) {
		this.VER = version;
	}
	
	public String getVersion() {
		return this.VER;
	}
	
	public Logger getLogger() {
		return this.LOG;
	}
}
