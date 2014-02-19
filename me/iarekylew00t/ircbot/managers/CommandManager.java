package me.iarekylew00t.ircbot.managers;

import java.util.HashMap;

public final class CommandManager {
	private static HashMap CMDS;
	
	public static void addCmds(HashMap commands) {
		CMDS.putAll(commands);
	}
	
	public static boolean isValidCmd(String command) {
		if (CMDS.containsKey(command)) {
			return true;
		}
		return false;
	}
}
