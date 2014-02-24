package me.iarekylew00t.ircbot.hooks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public final class PluginList {
	private Set<IRCPlugin> PLUGINS;
	
	public PluginList() {
		this.PLUGINS = new HashSet<IRCPlugin>();
	}

	public void add(String name, String version, CommandList commands, String author) {
		this.add(new IRCPlugin(name, version, commands, author));
	}

	public void add(IRCPlugin plugin) {
		this.PLUGINS.add(plugin);
	}

	public void remove(IRCPlugin plugin) {
		this.remove(plugin.toString());
	}
	
	public void remove(String plugin) {
		for (IRCPlugin pl : this.PLUGINS) {
			if (pl.equals(plugin)) {
				this.PLUGINS.remove(pl);
			}
		}
		throw new NullPointerException("'" + plugin + "' is not a valid plugin");
	}
	
	public void clear() {
		this.PLUGINS.clear();
	}
	
	public boolean contains(IRCPlugin plugin) {
		for (IRCPlugin pl : this.PLUGINS) {
			if (pl.equals(plugin)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean contains(String plugin) {
		for (IRCPlugin pl : this.PLUGINS) {
			if (pl.equals(plugin)) {
				return true;
			}
		}
		return false;
	}
	
	public IRCPlugin get(String name) {
		for (IRCPlugin pl : this.PLUGINS) {
			if (pl.equals(name)) {
				return pl;
			}
		}
		throw new NullPointerException("'" + name + "' is not a valid plugin");
	}
	
	public int size() {
		return this.PLUGINS.size();
	}
	
	public Set<IRCPlugin> toSet() {
		return this.PLUGINS;
	}
	
	public Set<IRCPlugin> sort() {
		return new TreeSet(this.PLUGINS);
	}
	
	@Override
	public String toString() {
		return Arrays.toString(this.PLUGINS.toArray());
	}
}
