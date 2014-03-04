package me.iarekylew00t.ircbot.hooks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pircbotx.Channel;
import org.pircbotx.Colors;
import org.pircbotx.User;

import me.iarekylew00t.ircbot.utils.IRC;

public class Command {
	private String CMD;
	private User USER;
	private Channel CHAN;
	private long DATE;
	private String[] ARGS;
	
	public Command(String message, User user, Channel channel) {
		message = Colors.removeFormattingAndColors(message);
		this.CMD = this.parseCmd(message);
		this.ARGS = this.parseArgs(message);
		this.USER = user;
		this.CHAN = channel;
		this.DATE = System.currentTimeMillis();
	}
	
	public boolean hasArgs() {
		return this.ARGS != null && this.ARGS.length != 0;
	}
	
	public String getName() {
		return this.CMD;
	}
	
	public String[] getArgs() {
		return this.ARGS;
	}
	
	public Channel getChannel() {
		return this.CHAN;
	}
	
	public long getCreationDate() {
		return this.DATE;
	}
	
	public IRCCommand getIRCCmd() {
		return CommandManager.getCmd(this.CMD);
	}
	
	public int getPermission() {
		return this.getIRCCmd().getPermissionLevel();
	}
	
	public boolean isValid() {
		return CommandManager.contains(this.CMD);
	}
	
	public String getUsage() {
		return CommandManager.getUsage(this.getIRCCmd());
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
