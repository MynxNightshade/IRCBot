package me.iarekylew00t.ircbot;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pircbotx.Colors;
import me.iarekylew00t.ircbot.hooks.CommandManager;
import me.iarekylew00t.ircbot.hooks.IRCCommand;

public class Command {
	private String CMD;
	private long DATE;
	private String[] ARGS;
	
	public Command(String message) {
		message = Colors.removeFormattingAndColors(message);
		this.CMD = this.parseCmd(message);
		this.ARGS = this.parseArgs(message);
		this.DATE = System.currentTimeMillis();
	}
	
	public String getName() {
		return this.CMD;
	}
	
	public String getUsage() {
		return CommandManager.getUsage(this.getIRCCmd());
	}
	
	public String[] getArgs() {
		return this.ARGS;
	}
	
	public int getPermission() {
		return this.getIRCCmd().getPermissionLevel();
	}
	
	public long getCreationDate() {
		return this.DATE;
	}
	
	public IRCCommand getIRCCmd() {
		return CommandManager.getCmd(this.CMD);
	}
	
	public boolean isValid() {
		return CommandManager.contains(this.CMD);
	}
	
	public boolean hasArgs() {
		return this.ARGS != null && this.ARGS.length != 0;
	}
	
	private String parseCmd(String str) {
		if (str.trim().contains(" ")) {
			return str.substring(1, str.indexOf(" "));
		}
		return str.substring(1);
	}
	
	private String[] parseArgs(String str) {
		List list = new ArrayList<String>();
		if (str.trim().contains(" ")) {
			String args = str.substring(str.indexOf(" ")).trim();
			if (!args.isEmpty()) {
				Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(args);
				while (m.find()) {
					list.add(m.group(1).replaceAll("\"", "").trim());
				}
			}
		}
		return (String[])list.toArray();
	}
}
