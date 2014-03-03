package me.iarekylew00t.ircbot.hooks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class PluginList {
	private List<IRCPlugin> PLUGINS;
	private static Comparator<IRCPlugin> ALPHABETICAL_ORDER = new Comparator<IRCPlugin>() {
		@Override
		public int compare(IRCPlugin pl1, IRCPlugin pl2) {
			return pl1.getName().compareTo(pl2.getName());
		}
	};
	
	public PluginList() {
		this.PLUGINS = new ArrayList<IRCPlugin>();
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
	
	public IRCPlugin get(int index) {
		return this.PLUGINS.get(index);
	}
	
	public int size() {
		return this.PLUGINS.size();
	}
	
	public List<IRCPlugin> toList() {
		return Collections.unmodifiableList(this.PLUGINS);
	}
	
	public void sort() {
		Collections.sort(this.PLUGINS, ALPHABETICAL_ORDER);
	}
	
	@Override
	public String toString() {
		return Arrays.toString(this.PLUGINS.toArray());
	}
}
