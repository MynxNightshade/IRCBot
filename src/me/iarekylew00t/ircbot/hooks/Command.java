package me.iarekylew00t.ircbot.hooks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pircbotx.User;

import me.iarekylew00t.utils.ColorUtils;

import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableList;

public class Command {
	private String CMD;
	private User USER;
	private long DATE;
	private ImmutableList<String> ARGS;
	
	public Command(String message) {
		this(message, null);
	}
	
	public Command(String message, User user) {
		message = ColorUtils.removeColors(message);
		this.CMD = this.parseCmd(message);
		this.ARGS = this.parseArgs(message);
		this.USER = user;
		this.DATE = System.currentTimeMillis();
	}
	
	public boolean hasArgs() {
		return (this.ARGS != null && this.ARGS.size() != 0 && !this.ARGS.isEmpty());
	}
	
	public String getArg(int arg) {
		return this.ARGS.get(arg);
	}
	
	public String getCmd() {
		return this.CMD;
	}
	
	public ImmutableList getArgs() {
		return this.ARGS;
	}
	
	public User getUser() {
		return this.USER;
	}
	
	public long getCreationDate() {
		return this.DATE;
	}
	
	public String combineArgs() {
		String fullArgs = "";
		for (String args : this.ARGS) {
			fullArgs += args + " ";
		}
		return fullArgs.trim();
	}
	
	public String combineArgs(int start, int end) {
		String fullArgs = "";
		for (int i = start; i <= end; i++) {
			fullArgs += this.ARGS.get(i) + " ";
		}
		return fullArgs.trim();
	}
	
	private String parseCmd(String str) {
		if (str.trim().contains(" ")) {
			return str.substring(1, str.indexOf(" "));
		}
		return str.substring(1);
	}
	
	private ImmutableList<String> parseArgs(String str) {
		ImmutableList<String> list = null;
		if (str.trim().contains(" ")) {
			String args = str.substring(str.indexOf(" ")).trim();
			if (!args.isEmpty()) {
				Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(args);
				Builder<String> builder = ImmutableList.builder();
				while (m.find()) {
					list = builder.add(m.group(1).replaceAll("\"", "").trim()).build();
				}
			}
		}
		return list;
	}
	
	public boolean isValidCmd() {
		return CommandManager.contains(this.CMD);
	}
}
