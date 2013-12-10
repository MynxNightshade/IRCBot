package me.iarekylew00t.ircbot.hooks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileConfiguration {
	private static Properties _PROPS = new Properties();
	private static File _CONFIG;
	
	public FileConfiguration(File config) throws IOException {
		_CONFIG = config;
		if (!_CONFIG.exists()) {
			_CONFIG.createNewFile();
		}
		_PROPS.load(new FileInputStream(_CONFIG));
	}
	
	public String getProperty(String prop) {
		return _PROPS.getProperty(prop);
	}
	
	public String getProperty(String prop, String defaultVal) {
		return _PROPS.getProperty(prop, defaultVal);
	}
}
