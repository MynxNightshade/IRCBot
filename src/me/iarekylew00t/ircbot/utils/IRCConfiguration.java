package me.iarekylew00t.ircbot.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class IRCConfiguration {
	private Properties PROPS = new Properties();
	private File CONFIG;
	private boolean FIRST = false;
	
	public IRCConfiguration() {
		this.CONFIG = new File("config.ini");
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
			if (!this.CONFIG.exists()) { this.CONFIG.createNewFile(); this.FIRST = true; }
			this.PROPS.load(new FileInputStream(this.CONFIG));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		try {
			this.PROPS.store(new FileOutputStream(this.CONFIG), "Aradiabot Configuration File");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
