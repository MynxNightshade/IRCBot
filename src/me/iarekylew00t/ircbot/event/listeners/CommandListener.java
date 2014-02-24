package me.iarekylew00t.ircbot.event.listeners;

import me.iarekylew00t.ircbot.hooks.Command;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandListener extends ListenerAdapter{
	private final Logger LOG = LoggerFactory.getLogger(CommandListener.class);
	
	@Override
	public void onMessage(MessageEvent e) {
		if (e.getMessage().startsWith("$")) {
			Command cmd = new Command(e.getMessage());

			if (cmd.hasArgs()) {
				this.LOG.info(e.getUser().getNick() + " issued command: " + cmd.getCmd() + " " + cmd.getArgs());
			} else {
				this.LOG.info(e.getUser().getNick() + " issued command: " + cmd.getCmd());
			}

			if (!cmd.isValidCmd()) {
				e.respond("'" + cmd.getCmd() + "' is not a valid command.");
			}
		}
	}
}
