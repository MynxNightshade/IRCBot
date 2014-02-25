package me.iarekylew00t.ircbot.event.listeners;

import java.util.Arrays;

import me.iarekylew00t.ircbot.IRCBot;
import me.iarekylew00t.ircbot.hooks.Command;
import me.iarekylew00t.ircbot.hooks.CommandList;
import me.iarekylew00t.ircbot.hooks.CommandManager;
import me.iarekylew00t.ircbot.hooks.IRCCommand;
import me.iarekylew00t.ircbot.hooks.IRCPlugin;
import me.iarekylew00t.ircbot.hooks.PluginManager;
import me.iarekylew00t.ircbot.utils.IRC;

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
		CMDS.add("version","","Displays the current version of Aradiabot.","ver");
		CMDS.add("commands","[page]","Displays all of Aradiabot's commands.","cmd");
		CMDS.add("plugins","[page]","Displays all of Aradiabot's plugins.","pl");
		CMDS.add("reboot","","Disconnects Aradiabot from the IRC and then reconnects.","", IRC.OP);
		CMDS.add("shutdown","","Disconnects Aradaibot from the IRC and shutdowns all active plugins before exiting.","", IRC.OP);
		CMDS.add("help","<command>","The help command provides information about commands. To get help with a command, type \"$help <command>\".","");
	}
	
	public CommandListener(IRCBot bot) {
		CommandManager.addCmds(CMDS);
		this.BOT = bot;
	}
	
	@Override
	public void onMessage(MessageEvent e) {
		if (e.getMessage().startsWith("#")) {
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
			
			switch(cmd.getCmd()) {
			case "version":
				if (cmd.hasArgs()) {
					if (PluginManager.contains(cmd.combineArgs())) {
						IRCPlugin ircPlugin = PluginManager.getPlugin(cmd.combineArgs());
						e.respond(ircPlugin.getName() + " v" + ircPlugin.getVersion());
						return;
					}
					e.respond("'" + cmd.combineArgs() + "' is not a valid plugin.");
					return;
				}
				e.respond("Aradiabot v" + this.BOT.getVersion());
				return;
			case "commands":
				int maxCmds = CommandManager.getTotalPages();
				if (cmd.hasArgs()) {
					int page;
					try {
						page = Integer.parseInt(cmd.combineArgs());
					} catch (Exception ex) {
						e.respond("Please enter a valid number from 1-" + maxCmds);
						return;
					}
					if (page > maxCmds) {
						e.respond("Please enter a number from 1-" + maxCmds);
						return;
					}
					cmd.getUser().send().notice("Commands (" + page + "/" + CommandManager.getTotalPages() + "): " + Arrays.toString(CommandManager.getPage(page).toArray()));
					return;
				}
				cmd.getUser().send().notice("Commands (1/" + maxCmds + "): " + Arrays.toString(CommandManager.getPage(1).toArray()));
				return;
			case "plugins":
				int maxPlugins = PluginManager.getTotalPages();
				if (cmd.hasArgs()) {
					int page;
					try {
						page = Integer.parseInt(cmd.combineArgs());
					} catch (Exception ex) {
						e.respond("Please enter a valid number from 1-" + maxPlugins);
						return;
					}
					if (page > maxPlugins) {
						e.respond("Please enter a number from 1-" + maxPlugins);
					}
					cmd.getUser().send().notice("Plugins (" + page + "/" + PluginManager.getTotalPages() + "): " + Arrays.toString(PluginManager.getPage(page).toArray()));
					return;
				}
				cmd.getUser().send().notice("Plugins (1/" + maxPlugins + "): " + Arrays.toString(PluginManager.getPage(1).toArray()));
				return;
			case "reboot":
				if (cmd.hasPermission()) {
					if (!cmd.hasArgs()) {
						cmd.getChannel().send().notice(Colors.BOLD + "----- REBOOTING -----");
						this.BOT.restart();
						return;
					}
					e.respond("Usage: " + cmd.getUsage());
					return;
				}
				e.respond(Colors.RED + Colors.BOLD + "You don't have permission to do that.");
				return;
			case "shutdown":
				if (cmd.hasPermission()) {
					if (!cmd.hasArgs()) {
						cmd.getChannel().send().notice(Colors.BOLD + "----- SHUTTING DOWN -----");
						this.BOT.disconnect();
						return;
					}
					e.respond("Usage: " + cmd.getUsage());
					return;
				}
				e.respond(Colors.RED + Colors.BOLD + "You don't have permission to do that.");
				return;
			case "help":
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
