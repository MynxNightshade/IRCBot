package me.iarekylew00t.ircbot.plugins;

import org.pircbotx.hooks.events.MessageEvent;

import me.iarekylew00t.ircbot.IRCBot;
import me.iarekylew00t.ircbot.hooks.CommandList;
import me.iarekylew00t.ircbot.hooks.Command;
import me.iarekylew00t.ircbot.hooks.IRCPlugin;

public class TestPlugin extends IRCPlugin {
	private static final String NAME = "TestPlugin", VER = "1.0.0";
	private static IRCBot BOT;
	private static CommandList CMDS = new CommandList();
	static {
		//Add Commands here
		CMDS.add("test", "", "A basic test command.", "t");
		CMDS.add("test2", "<arg0>,<arg1>,[arg2]", "Another basic test plugin w/ arguments", "t2");
		CMDS.add("restart", "", "Disconnects the bot and then reconnects.", "");
		CMDS.add("shutdown", "", "Disables all plugins and shutsdown the bot.", "");
	}
	
	public TestPlugin(IRCBot bot) {
		super(NAME, VER, CMDS);
		BOT = bot;
	}
	
	@Override
	public void onEnable() {
		this.info("Enabling " + NAME + " v" + VER);
	}
	
	@Override
	public void onDisable() {
		this.info("Disabling " + NAME + " v" + VER);
	}

	@Override
	public void onMessage(MessageEvent e) {
		if (this.isEnabled() && e.getMessage().startsWith("$")) {
			Command cmd = new Command(e.getMessage(), e.getUser());
			
			if (cmd.getCmd().equals("test") || cmd.getCmd().equals("t")) {
				e.respond("Working!");
				return;
			}
			if (cmd.getCmd().equals("test2")) {
				if (cmd.hasArgs()) {
					e.respond("You said: " + cmd.combineArgs());
					return;
				}
				e.respond("You didn't say anything.");
				return;
			}
			 if (cmd.getCmd().equals("restart")) {
				BOT.restart();
				return;
			 }
			 if (cmd.getCmd().equals("shutdown")) {
				BOT.disconnect();
				return;
			}
		}
	}
}
