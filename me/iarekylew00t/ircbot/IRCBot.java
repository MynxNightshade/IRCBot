package me.iarekylew00t.ircbot;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

public class IRCBot extends PircBotX {
	private static String _VER = "1.0.0";

	public IRCBot(Configuration configuration) {
		super(configuration);
	}
	
	public void setVersion(String version) {
		_VER = version;
	}
	
	public String getVersion() {
		return _VER;
	}
	
}
