package me.iarekylew00t.ircbot.event.listeners;

import me.iarekylew00t.ircbot.IRCBot;
import me.iarekylew00t.ircbot.hooks.IRCCommand;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.slf4j.Logger;

public class CommandListener extends ListenerAdapter{
	private static Logger LOG;
	
	public CommandListener(IRCBot bot) {
		LOG = bot.getLogger();
	}
	
	@Override
	public void onMessage(MessageEvent e) {
		if (e.getMessage().startsWith("$")) {
			
			IRCCommand cmd = new IRCCommand(e.getMessage());

			if (cmd.hasArgs()) {
				LOG.info(e.getUser().getNick() + " issued command: " + cmd.getCmd() + " '" + cmd.combineArgs() + "'");
			} else {
				LOG.info(e.getUser().getNick() + " issued command: " + cmd.getCmd());
			}
			
			if (!cmd.isValidCmd()) {
				e.respond("'" + cmd.getCmd() + "' is not a valid command.");
			}
		}
	}
}
