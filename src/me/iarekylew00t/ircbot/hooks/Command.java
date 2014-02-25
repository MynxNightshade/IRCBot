package me.iarekylew00t.ircbot.hooks;

import java.util.ArrayList;
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
	private List<String> ARGS;
	
	public Command(String message, User user, Channel channel) {
		message = Colors.removeFormattingAndColors(message);
		this.CMD = this.parseCmd(message);
		this.ARGS = this.parseArgs(message);
		this.USER = user;
		this.CHAN = channel;
		this.DATE = System.currentTimeMillis();
	}
	
	public boolean hasArgs() {
		return this.ARGS != null && this.ARGS.size() != 0 && !this.ARGS.isEmpty();
	}
	
	public String getArg(int arg) {
		return this.ARGS.get(arg);
	}
	
	public String getCmd() {
		return this.CMD;
	}
	
	public List<String> getArgs() {
		return this.ARGS;
	}
	
	public User getUser() {
		return this.USER;
	}
	
	public long getCreationDate() {
		return this.DATE;
	}
	
	public IRCCommand getIRCCmd() {
		return CommandManager.getCmd(this.CMD);
	}
	
	public int getPermissionLevel() {
		return this.getIRCCmd().getPermissionLevel();
	}
	
	public boolean hasPermission() {
		switch (this.getPermissionLevel()) {
		case IRC.IRC_OP:
			return this.USER.isIrcop();
		case IRC.OWNER:
			return this.USER.getChannelsOwnerIn().contains(this.CHAN);
		case IRC.SUPER_OP:
			return this.USER.getChannelsSuperOpIn().contains(this.CHAN);
		case IRC.OP:
			return this.USER.getChannelsOpIn().contains(this.CHAN);
		case IRC.HALF_OP:
			return this.USER.getChannelsHalfOpIn().contains(this.CHAN);
		case IRC.VOICE:
			return this.USER.getChannelsVoiceIn().contains(this.CHAN);
		case IRC.NORMAL:
			return this.USER.getChannels().contains(this.CHAN);
		default:
			return false;
		}
	}
	
	public boolean isValid() {
		return CommandManager.contains(this.CMD);
	}
	
	public String getUsage() {
		return CommandManager.getUsage(this.getIRCCmd());
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
	
	private List<String> parseArgs(String str) {
		List<String> list = new ArrayList<String>();
		if (str.trim().contains(" ")) {
			String args = str.substring(str.indexOf(" ")).trim();
			if (!args.isEmpty()) {
				Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(args);
				while (m.find()) {
					list.add(m.group(1).replaceAll("\"", "").trim());
				}
			}
		}
		return Collections.unmodifiableList(list);
	}
}
