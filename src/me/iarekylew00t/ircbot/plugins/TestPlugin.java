package me.iarekylew00t.ircbot.plugins;

import org.pircbotx.hooks.events.MessageEvent;

import me.iarekylew00t.ircbot.IRCBot;
import me.iarekylew00t.ircbot.hooks.Command;
import me.iarekylew00t.ircbot.hooks.CommandList;
import me.iarekylew00t.ircbot.hooks.CommandManager;
import me.iarekylew00t.ircbot.hooks.IRCPlugin;
import me.iarekylew00t.ircbot.utils.IRC;
import me.iarekylew00t.ircbot.utils.PluginConfiguration;

public class TestPlugin extends IRCPlugin {
	private static final String NAME = "TestPlugin", VER = "1.0.0", AUTHOR = "TestAuthor";
	private static PluginConfiguration CONFIG;
	private static IRCBot BOT;
	private static CommandList CMDS = new CommandList();
	static {
		//Add Commands here
		CMDS.add("test", "", "A basic test command.", "t");
		CMDS.add("test2", "<arg0>,<arg1>,[arg2]", "Another basic test plugin w/ arguments", "t2");
	}
	
	public TestPlugin(IRCBot bot) {
		super(NAME, VER, CMDS, AUTHOR);
		BOT = bot;
	}
	
	@Override
	public void onEnable() {
		this.log().info("Enabling " + NAME + " v" + VER);
		CONFIG = new PluginConfiguration(this);
		if (CONFIG.firstTimeLoad()) {
			CONFIG.setProp("testProp", "123");
			CONFIG.setProp("testProp2", "abc");
			CONFIG.update();
		}
	}
	
	@Override
	public void onDisable() {
		this.log().info("Disabling " + NAME + " v" + VER);
	}

	@Override
	public void onMessage(MessageEvent e) {
		if (this.isEnabled() && e.getMessage().startsWith("$")) {
			Command cmd = new Command(e.getMessage(), e.getUser(), e.getChannel());
			
			if (cmd.getCmd().equals("test") || cmd.getCmd().equals("t")) {
				/*e.getChannel().send().message("Nick: " + e.getUser().getNick());
				e.getChannel().send().message("Verified: " + e.getUser().isVerified());
				e.getChannel().send().message("Login: " + e.getUser().getLogin());
				e.getChannel().send().message("Hostmask: " + e.getUser().getHostmask());
				e.getChannel().send().message("Real Name: " + e.getUser().getRealName());
				e.getChannel().send().message("UUID: " + e.getUser().getUserId());*/
				e.respond("" + CommandManager.getCmds().list(IRC.NORMAL));
				e.respond("" + CommandManager.getCmds().list(IRC.VOICE));
				e.respond("" + CommandManager.getCmds().list(IRC.OP));
				return;
			}
			if (cmd.getCmd().equals("test2")) {
				if (cmd.hasArgs()) {
					e.respond("You said: " + cmd.combineArgs());
					return;
				}
				e.respond(cmd.getUsage());
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
