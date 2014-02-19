package me.iarekylew00t.ircbot.hooks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileConfiguration {
	private static Properties PROPS = new Properties();
	private static File CONFIG;
	
	public FileConfiguration(File config) throws IOException {
		CONFIG = config;
		if (!CONFIG.exists()) {
			CONFIG.createNewFile();
		}
		PROPS.load(new FileInputStream(CONFIG));
	}
	
	public String getProperty(String prop) {
		return PROPS.getProperty(prop);
	}
	
	public String getProperty(String prop, String defaultVal) {
		return PROPS.getProperty(prop, defaultVal);
	}
}
