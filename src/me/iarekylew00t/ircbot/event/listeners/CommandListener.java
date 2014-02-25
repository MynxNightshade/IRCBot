package me.iarekylew00t.ircbot.event.listeners;

import java.util.Arrays;

import me.iarekylew00t.ircbot.IRCBot;
import me.iarekylew00t.ircbot.hooks.Command;
import me.iarekylew00t.ircbot.hooks.CommandList;
import me.iarekylew00t.ircbot.hooks.CommandManager;
import me.iarekylew00t.ircbot.hooks.IRCCommand;

import org.pircbotx.Colors;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandListener extends ListenerAdapter {
	private final Logger LOG = LoggerFactory.getLogger(CommandListener.class);
	private IRCBot BOT;
	private static CommandList CMDS = new CommandList();
	static {
		CMDS.add("help","<command>","The help command provides information about commands. To get help with a command, type \"$help <command>\".","");
	}
	
	public CommandListener(IRCBot bot) {
		CommandManager.addCmds(CMDS);
		this.BOT = bot;
	}
	
	@Override
	public void onMessage(MessageEvent e) {
		if (e.getMessage().startsWith("$")) {
			Command cmd = new Command(e.getMessage(), e.getUser(), e.getChannel());

			if (cmd.hasArgs()) {
				this.LOG.info(cmd.getUser().getNick() + " issued command: " + cmd.getCmd() + " " + cmd.getArgs());
			} else {
				this.LOG.info(cmd.getUser().getNick() + " issued command: " + cmd.getCmd());
			}

			if (!cmd.isValid()) {
				e.respond("'" + cmd.getCmd() + "' is not a valid command.");
				return;
			}
			
			if (cmd.getCmd().equals("help")) {
				if (cmd.hasArgs()) {
					if (CommandManager.contains(cmd.combineArgs())) {
						IRCCommand ircCmd = CommandManager.getCmd(cmd.combineArgs());
						e.respond(Colors.NORMAL + Colors.BOLD + ircCmd.getName() + Colors.NORMAL + (ircCmd.hasAlias() ? " " + Arrays.toString(ircCmd.getAlias().toArray()) : "") + ": " + ircCmd.getDesc() + " - " + CommandManager.getUsage(ircCmd));
						return;
					}
					e.respond("'" + cmd.combineArgs() + "' is not a valid command.");
					return;
				}
				e.respond(cmd.getIRCCmd().getDesc());
				return;
			}
		}
	}
}
