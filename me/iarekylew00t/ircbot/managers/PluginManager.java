package me.iarekylew00t.ircbot.managers;

import java.util.ArrayList;

import org.slf4j.Logger;

import me.iarekylew00t.ircbot.IRCBot;
import me.iarekylew00t.ircbot.hooks.IRCPlugin;

public final class PluginManager {
	private static ArrayList<IRCPlugin> _PLUGINS = new ArrayList<IRCPlugin>();
	private static IRCBot _BOT;
	private static Logger _LOG;
	
	public PluginManager(IRCBot ircBot) {
		_BOT = ircBot;
		_LOG = ircBot.getLogger();
	}
	
	public static int totalPlugins() {
		return _PLUGINS.size();
	}
	
	public static void addPlugin(IRCPlugin plugin) {
		_PLUGINS.add(plugin);
	}
	
	public static void removePlugin(IRCPlugin plugin) {
		_PLUGINS.remove(plugin);
	}
	
	public static void removePluginByName(String name) {
		for (IRCPlugin plugin : _PLUGINS) {
			if (plugin.getName().equals(name)) {
				_PLUGINS.remove(plugin);
			}
		}
	}
	
	public static IRCBot getBot() {
		return _BOT;
	}
	
	public static Logger getLogger() {
		return _LOG;
	}
}
