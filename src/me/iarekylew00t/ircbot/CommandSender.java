package me.iarekylew00t.ircbot;

import me.iarekylew00t.ircbot.utils.IRC;

import org.pircbotx.Channel;
import org.pircbotx.User;

public class CommandSender {
	private final User USER;
	private final Channel CHAN;
	
	public CommandSender(User user, Channel channel) {
		this.USER = user;
		this.CHAN = channel;
	}
	
	public void respond(String response) {
		this.CHAN.send().message(this.getName() + ": " + response);
	}
	
	public void sendMessage(String message) {
		this.CHAN.send().message(message);
		return;
	}
	
	public void sendPrivateMessage(String message) {
		this.USER.send().message(message);
		return;
	}
	
	public void sendNotice(String notice) {
		this.USER.send().notice(notice);
		return;
	}
	
	public void sendAction(String action) {
		this.USER.send().action(action);
		return;
	}
	
	public Channel getChannel() {
		return this.CHAN;
	}
		
	public String getName() {
		return this.USER.getNick();
	}
	
	public String getLogin() {
		return this.USER.getLogin();
	}
	
	public String getHostmask() {
		return this.USER.getHostmask();
	}
	
	public String getServer() {
		return this.USER.getServer();
	}
	
	public boolean isVerified() {
		return this.USER.isVerified();
	}
	
	public boolean isAway() {
		return this.USER.isAway();
	}
	
	public boolean isIrcOp() {
		return this.USER.isIrcop();
	}
	
	public boolean isConnected() {
		return this.USER.getChannels().contains(this.CHAN);
	}
	
	public boolean hasPermission(int perm) {
		return IRC.getPermissionLevel(this.USER, this.CHAN) >= perm;
	}

}
