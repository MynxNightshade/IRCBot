package me.iarekylew00t.ircbot;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IRCBot extends PircBotX {
	private static String _VER = "1.0.0";
	private final static Logger _LOG = LoggerFactory.getLogger(IRCBot.class);

	public IRCBot(Configuration configuration) {
		super(configuration);
		new PluginManager(this);
	}
	
	public void setVersion(String version) {
		_VER = version;
	}
	
	public String getVersion() {
		return _VER;
	}
	
	public Logger getLogger() {
		return _LOG;
	}
}
