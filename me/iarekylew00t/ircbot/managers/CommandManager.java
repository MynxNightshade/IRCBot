package me.iarekylew00t.ircbot.managers;

import java.util.HashMap;

public final class CommandManager {
	private static HashMap _CMDS;
	
	public static void addCmds(HashMap commands) {
		_CMDS.putAll(commands);
	}
	
	public static boolean isValidCmd(String command) {
		if (_CMDS.containsKey(command)) {
			return true;
		}
		return false;
	}
}
