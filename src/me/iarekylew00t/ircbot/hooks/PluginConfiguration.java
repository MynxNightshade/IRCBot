package me.iarekylew00t.ircbot.hooks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PluginConfiguration {
	private Properties PROPS = new Properties();
	private File DIR = new File("./plugins/");
	private File CONFIG;
	private IRCPlugin PLUGIN;
	private boolean FIRST = false;
	
	public PluginConfiguration(IRCPlugin plugin) {
		this.CONFIG = new File(this.DIR +"\\"+ plugin.getName() + ".ini");
		this.PLUGIN = plugin;
		this.load();
	}
	
	public String getProp(String prop) {
		return this.PROPS.getProperty(prop);
	}
	
	public String getProp(String prop, String defaultVal) {
		return this.PROPS.getProperty(prop, defaultVal);
	}
	
	public void setProp(String prop, String newValue) {
		this.PROPS.setProperty(prop, newValue);
	}
	
	public boolean firstTimeLoad() {
		return this.FIRST;
	}
	
	public void update() {
		this.save();
		this.load();
	}
	
	public void load() {
		try {
			if (!this.DIR.exists()) { this.DIR.mkdir(); this.FIRST = true; }
			if (!this.CONFIG.exists()) { this.CONFIG.createNewFile(); this.FIRST = true; }
			this.PROPS.load(new FileInputStream(this.CONFIG));
		} catch (Exception e) {
			this.PLUGIN.warn("Error loading configuration file.");
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			this.PROPS.store(new FileOutputStream(this.CONFIG), this.PLUGIN.getName() + " v" + this.PLUGIN.getVersion() + " Configuration File");
		} catch (Exception e) {
			this.PLUGIN.warn("Error saving configuration file.");
			e.printStackTrace();
		}
	}
}
