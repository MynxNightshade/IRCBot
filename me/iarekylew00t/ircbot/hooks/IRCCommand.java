package me.iarekylew00t.ircbot.hooks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.iarekylew00t.ircbot.managers.CommandManager;
import me.iarekylew00t.utils.ColorUtils;

import com.google.common.collect.ImmutableList.Builder;
import com.google.common.collect.ImmutableList;

public class IRCCommand {
	private String CMD;
	private ImmutableList<String> ARGS;
	
	public IRCCommand(String message) {
		message = ColorUtils.removeColors(message);
		
		if (message.contains(" ")) {
			this.CMD = message.substring(1, message.indexOf(" "));
			String args = message.substring(message.indexOf(" ")).trim();
			
			if (!args.isEmpty()) {
				Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(args);
				Builder<String> builder = ImmutableList.builder();
				
				while (m.find()) {
					this.ARGS = builder.add(m.group(1).replaceAll("\"", "").trim()).build();
				}
				return;
			}
			this.ARGS = null;
			return;
		}
		this.CMD = message.substring(1);
		this.ARGS = null;
	}
	
	public boolean hasArgs() {
		return (this.ARGS != null && this.ARGS.size() != 0);
	}
	
	public String getArg(int arg) {
		return this.ARGS.get(arg);
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
		if (end > this.ARGS.size()) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = start; i <= end; i++) {
			fullArgs += this.ARGS.get(i) + " ";
		}
		return fullArgs.trim();
	}
	
	public boolean isValidCmd() {
		return CommandManager.contains(this.CMD);
	}
	
	public String getCmd() {
		return this.CMD;
	}
	
	public ImmutableList getArgs() {
		return this.ARGS;
	}
}