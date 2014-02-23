package me.iarekylew00t.ircbot.managers;

import java.util.ArrayList;

import org.slf4j.Logger;

import me.iarekylew00t.ircbot.IRCBot;
import me.iarekylew00t.ircbot.exceptions.PluginException;
import me.iarekylew00t.ircbot.hooks.IRCPlugin;

public final class PluginManager {
	private static ArrayList<IRCPlugin> PLUGINS = new ArrayList<IRCPlugin>();
	private static IRCBot BOT;
	private static Logger LOG;
	
	public PluginManager(IRCBot ircBot) {
		BOT = ircBot;
		LOG = ircBot.getLogger();
	}
	
	public static int totalPlugins() {
		return PLUGINS.size();
	}
	
	public static void addPlugin(IRCPlugin plugin) throws PluginException {
		if (!PLUGINS.contains(plugin)) {
			PLUGINS.add(plugin);
			return;
		}
		throw new PluginException(plugin.getName() + " is already a valid plugin");
	}
	
	public static void removePlugin(IRCPlugin plugin) throws PluginException {
		if (!PLUGINS.contains(plugin)) {
			PLUGINS.remove(plugin);
			return;
		}
		throw new PluginException(plugin.getName() + " is not a valid plugin");
	}
	
	public static void removePluginByName(String name) throws PluginException {
		for (IRCPlugin plugin : PLUGINS) {
			if (plugin.getName().equals(name)) {
				PLUGINS.remove(plugin);
				return;
			}
		}
		throw new PluginException(name + " is not a valid plugin");
	}
	
	public static IRCPlugin getPluginByName(String name) throws PluginException {
		for (IRCPlugin plugin : PLUGINS) {
			if (plugin.getName().equals(name)) {
				return plugin;
			}
		}
		throw new PluginException(name + " is not a valid plugin");
	}
	
	public static boolean isValidPlugin(String name) {
		for (IRCPlugin plugin : PLUGINS) {
			if (plugin.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public static IRCBot getBot() {
		return BOT;
	}
	
	public static Logger getLogger() {
		return LOG;
	}
}
