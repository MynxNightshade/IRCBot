package me.iarekylew00t.ircbot.managers;

import me.iarekylew00t.ircbot.exceptions.CommandException;
import me.iarekylew00t.ircbot.hooks.Command;
import me.iarekylew00t.ircbot.hooks.CommandList;

public final class CommandManager {
	private static CommandList CMDS = new CommandList();
	
	public static void addCmd(Command cmd) {
		if (CMDS.contains(cmd)) {
			throw new CommandException("'" + cmd.getName() + "' is already a valid command");
		}
		CMDS.add(cmd);
	}
	
	public static void addCmds(CommandList cmds) {
		for (Command c : cmds.toSet()) {
			CommandManager.addCmd(c);
		}
	}

	public static void removeCmd(Command cmd) {
		CMDS.remove(cmd);
	}
	
	public static void removeCmd(String cmd) {
		CMDS.remove(cmd);
	}
	
	public static void removeCmds(CommandList cmds) {
		for (Command c : cmds.toSet()) {
			CommandManager.removeCmd(c);
		}
	}
	
	public static Command getCmd(String cmd) {
		return CMDS.get(cmd);
	}
	
	public static boolean contains(String cmd) {
		return CMDS.contains(cmd);
	}
	
	public static int totalCmds() {
		return CMDS.size();
	}
	
	public static CommandList getCmds() {
		return CMDS;
	}
}
