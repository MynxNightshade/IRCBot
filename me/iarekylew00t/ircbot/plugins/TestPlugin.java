package me.iarekylew00t.ircbot.plugins;

import java.util.HashMap;

import org.pircbotx.hooks.events.MessageEvent;

import me.iarekylew00t.ircbot.hooks.IRCPlugin;

public class TestPlugin extends IRCPlugin {
	private static final String _NAME = "TestPlugin", _VER = "1.0.0";
	private static HashMap _CMDS;
	
	public TestPlugin() {
		super(_NAME, _VER, _CMDS);
	}
	
	@Override
	public void onEnable() {
		this.info("This is a test");
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
