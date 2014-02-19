package me.iarekylew00t.ircbot.plugins;

import java.util.HashMap;

import org.pircbotx.hooks.events.MessageEvent;

import me.iarekylew00t.ircbot.hooks.IRCPlugin;

public class TestPlugin extends IRCPlugin {
	private static final String NAME = "TestPlugin", VER = "1.0.0";
	private static HashMap<String, String> CMDS = new HashMap<String, String>();
	static {
		//Add Commands here
		CMDS.put("test", "$test");
	}
	
	public TestPlugin() {
		super(NAME, VER, CMDS);
	}
	
	@Override
	public void onEnable() {
		this.info("This is a test" + 1/0);
	}
	
	@Override
	public void onDisable() {
	}

	@Override
	public void onMessage(MessageEvent e) {
		if (this.isEnabled() && e.getMessage().startsWith("$")) {
			System.out.println(e.getMessage());
		}
	}
}
