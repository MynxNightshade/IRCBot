package me.iarekylew00t.ircbot;

import java.util.ArrayList;

import me.iarekylew00t.ircbot.hooks.IRCPlugin;

public final class PluginManager {
	private static ArrayList<IRCPlugin> _PLUGINS = new ArrayList<IRCPlugin>();
	
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
}
