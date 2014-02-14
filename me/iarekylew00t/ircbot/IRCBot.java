package me.iarekylew00t.ircbot;

import me.iarekylew00t.ircbot.managers.PluginManager;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IRCBot extends PircBotX {
	private String _VER = "1.0.0";
	private final Logger _LOG = LoggerFactory.getLogger(IRCBot.class);

	public IRCBot(Configuration configuration) {
		super(configuration);
		new PluginManager(this);
	}
	
	public void setVersion(String version) {
		this._VER = version;
	}
	
	public String getVersion() {
		return this._VER;
	}
	
	public Logger getLogger() {
		return this._LOG;
	}
}
