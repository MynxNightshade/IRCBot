package me.iarekylew00t.ircbot.hooks;

import me.iarekylew00t.ircbot.exceptions.CommandException;

public final class CommandManager {
	private static CommandList CMDS = new CommandList();
	
	public static void addCmd(IRCCommand cmd) {
		if (CMDS.contains(cmd)) {
			throw new CommandException("'" + cmd.getName() + "' is already a valid command");
		}
		CMDS.add(cmd);
	}
	
	public static void addCmds(CommandList cmds) {
		for (IRCCommand c : cmds.toList()) {
			CommandManager.addCmd(c);
		}
	}

	protected static void removeCmd(IRCCommand cmd) {
		CMDS.remove(cmd);
	}
	
	protected static void removeCmd(String cmd) {
		CMDS.remove(cmd);
	}
	
	protected static void removeCmds(CommandList cmds) {
		for (IRCCommand c : cmds.toList()) {
			CommandManager.removeCmd(c);
		}
	}
	
	protected static void removeAllCmds() {
		CMDS.clear();
	}
	
	public static IRCCommand getCmd(String cmd) {
		return CMDS.get(cmd);
	}

	public static boolean contains(String cmd) {
		return CMDS.contains(cmd);
	}
	
	public static boolean contains(IRCCommand cmd) {
		return CMDS.contains(cmd);
	}
	
	public static int totalCmds() {
		return CMDS.size();
	}
	
	public static String getUsage(IRCCommand cmd) {
		return CommandManager.getUsage(cmd.getName());
	}
	
	public static String getUsage(String cmd) {
		String usage = "$" + cmd;
		for (String arg : CMDS.get(cmd).getArgs()) {
			usage += " " + arg;
		}
		return usage.trim();
	}
	
	public static CommandList getCmds() {
		CMDS.sort();
		return CMDS;
	}
}
