package me.iarekylew00t.ircbot.hooks;

import me.iarekylew00t.ircbot.IRCBot;
import me.iarekylew00t.ircbot.exceptions.PluginException;

public class PluginManager {
	private static PluginList PLUGINS;
	private static IRCBot BOT;
	
	public PluginManager(IRCBot bot) {
		PLUGINS = new PluginList();
		BOT = bot;
	}
	
	protected static void addPlugin(IRCPlugin plugin) {
		if (PLUGINS.contains(plugin)) {
			throw new PluginException("'" + plugin.getName() + "' is already a valid plugin");
		}
		PLUGINS.add(plugin);
		CommandManager.addCmds(plugin.getCommands());
	}
	
	protected static void removePlugin(IRCPlugin plugin) {
		PLUGINS.remove(plugin);
		CommandManager.removeCmds(plugin.getCommands());
		BOT.removePlugin(plugin);
	}
	
	protected static void removePlugin(String plugin) {
		PLUGINS.remove(plugin);
		CommandManager.removeCmds(getPlugin(plugin).getCommands());
		BOT.removePlugin(getPlugin(plugin));
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
	
	public void removeAllPlugins() {
		for (IRCPlugin p : PLUGINS.toList()) {
			p.onDisable();
		}
		PLUGINS.clear();
	}
	
}
