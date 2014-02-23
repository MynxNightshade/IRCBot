package me.iarekylew00t.ircbot.managers;

import me.iarekylew00t.ircbot.exceptions.PluginException;
import me.iarekylew00t.ircbot.hooks.IRCPlugin;
import me.iarekylew00t.ircbot.hooks.PluginList;

public final class PluginManager {
	private static PluginList PLUGINS = new PluginList();
	
	public static void addPlugin(IRCPlugin plugin) {
		if (PLUGINS.contains(plugin)) {
			throw new PluginException("'" + plugin.getName() + "' is already a valid plugin");
		}
		PLUGINS.add(plugin);
		CommandManager.addCmds(plugin.getCommands());
	}
	
	public static void removePlugin(IRCPlugin plugin) {
		PLUGINS.remove(plugin);
		CommandManager.removeCmds(plugin.getCommands());
	}
	
	public static void removePlugin(String plugin) {
		PLUGINS.remove(plugin);
		CommandManager.removeCmds(getPlugin(plugin).getCommands());
	}
	
	public static IRCPlugin getPlugin(String plugin) {
		return PLUGINS.get(plugin);
	}
	
	public static boolean contains(String plugin) {
		return PLUGINS.contains(plugin);
	}
	
	public static int totalPlugins() {
		return PLUGINS.size();
	}
	
	public static PluginList getPlugins() {
		return PLUGINS;
	}
}
