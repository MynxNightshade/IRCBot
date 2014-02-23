package me.iarekylew00t.ircbot.plugins;

import org.pircbotx.hooks.events.MessageEvent;

import me.iarekylew00t.ircbot.hooks.CommandList;
import me.iarekylew00t.ircbot.hooks.IRCCommand;
import me.iarekylew00t.ircbot.hooks.IRCPlugin;

public class TestPlugin extends IRCPlugin {
	private static final String NAME = "TestPlugin", VER = "1.0.0";
	private static CommandList CMDS = new CommandList();
	static {
		//Add Commands here
		CMDS.add("test", "", "A basic test command.", "t");
		CMDS.add("test2", "<arg0>,<arg1>,[arg2]", "Another basic test plugin w/ arguments", "t2");
	}
	
	public TestPlugin() {
		super(NAME, VER, CMDS);
	}
	
	@Override
	public void onEnable() {
	}
	
	@Override
	public void onDisable() {
	}

	@Override
	public void onMessage(MessageEvent e) {
		if (this.isEnabled() && e.getMessage().startsWith("$")) {
			IRCCommand cmd = new IRCCommand(e.getMessage());
			System.out.println(cmd.getCmd());
			System.out.println(cmd.getArgs());
		}
	}
}
