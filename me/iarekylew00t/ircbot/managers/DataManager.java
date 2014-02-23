package me.iarekylew00t.ircbot.managers;

import me.iarekylew00t.ircbot.IRCBot;

import org.slf4j.Logger;

public final class DataManager {
	private static IRCBot BOT;
	private static Logger LOG;
	
	public DataManager(IRCBot bot) {
		BOT = bot;
		LOG = bot.getLogger();
	}
	
	public static IRCBot getBot() {
		return BOT;
	}
	
	public static Logger getLogger() {
		return LOG;
	}
}
