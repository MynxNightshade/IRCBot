package me.iarekylew00t.ircbot.hooks;

import me.iarekylew00t.ircbot.managers.CommandManager;
import me.iarekylew00t.utils.ColorUtils;

import org.pircbotx.User;

public class Command {
	private String _CMD;
	private String[] _ARGS;
	private String _USER;
	
	public Command(String message) {
		this(message, null);
	}
	
	public Command(String message, User sender) {
		/* Remove all colors from line */
		message = ColorUtils.removeColors(message);
		
		if(sender != null) {
			this._USER = sender.getNick();
		} else {
			this._USER = null;
		}
		
		if (message.contains(" ")) {
			this._CMD = message.substring(1, message.indexOf(" "));
			String args = message.substring(message.indexOf(" "));
			if (!isEmpty(args)) {
				args = args.trim();
				if (!args.equals("")) {
					this._ARGS = args.split(" ");
				} else { 
					this._ARGS = null;
				}
			}
		} else {
			this._CMD = message.substring(1);
			this._ARGS = null;
		}
	}
	
	public boolean hasArgs() {
		if (this._ARGS != null) {
			if (this._ARGS.length != 0) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public String getArg(int arg) {
		if (arg > this._ARGS.length) {
			throw new IndexOutOfBoundsException();
		}
		return this._ARGS[arg];
	}
	
	public boolean containsArg(String arg) {
		for (String args : this._ARGS) {
			if (args.equals(arg)) {
				return true;
			}
		}
		return false;
	}
	
	public String combineArgs() {
		String fullArgs = "";
		for (String args : this._ARGS) {
			fullArgs += args + " ";
		}
		return fullArgs.trim();
	}
	
	public String combineArgs(int start, int end) {
		String fullArgs = "";
		if (end > this._ARGS.length) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = start; i <= end; i++) {
			fullArgs += this._ARGS[i] + " ";
		}
		return fullArgs.trim();
	}
	
	public boolean isValidCmd() {
		return CommandList.isValidCmd(this._CMD);
	}
	
	public String getSender() {
		return this._USER;
	}
	
	public String getCmd() {
		return this._CMD;
	}
	
	public String[] getArgs() {
		return this._ARGS;
	}
	
	private static boolean isEmpty(String cmd) {
		if (cmd.replace("\\s", "").isEmpty()) {
			return true;
		}
		return false;
	}
}
