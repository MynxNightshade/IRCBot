package me.iarekylew00t.ircbot.hooks;

import java.util.ArrayList;
import java.util.List;

import me.iarekylew00t.ircbot.IRCBot;
import me.iarekylew00t.ircbot.exceptions.PluginException;

public class PluginManager {
	private static final int PAGE = 50;
	private static PluginList PLUGINS;
	private static IRCBot BOT;
	
	public PluginManager(IRCBot bot) {
		PLUGINS = new PluginList();
		BOT = bot;
	}
	
	public static void addPlugin(IRCPlugin plugin) {
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
	
	public void removeAllPlugins() {
		for (IRCPlugin p : PLUGINS.toList()) { p.onDisable(); }
		PLUGINS.clear();
	}
	
	public static IRCPlugin getPlugin(String plugin) {
		return PLUGINS.get(plugin);
	}
	
	public static boolean contains(String plugin) {
		return PLUGINS.contains(plugin);
	}
	
	public static int getTotalPages() {
		int pages = PLUGINS.size() / PAGE;
		if (PLUGINS.size() % PAGE > 0) { pages++; }
		return pages;
	}
	
	public static List<IRCPlugin> getPage(int page) {
		if (page > getTotalPages()) { throw new IndexOutOfBoundsException(); }
		List<IRCPlugin> list = new ArrayList<IRCPlugin>();
		int max = PLUGINS.size(), index = PAGE * (page-1);
		if (index > max) { index = max; }
		for (int i = index; i < max; i++) {
			list.add(PLUGINS.get(i));
		}
		return list;
	}
	
	public static int totalPlugins() {
		return PLUGINS.size();
	}
	
	public static PluginList getPlugins() {
		PLUGINS.sort();
		return PLUGINS;
	}
	
}
