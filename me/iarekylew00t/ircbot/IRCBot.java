package me.iarekylew00t.ircbot;

import me.iarekylew00t.ircbot.hooks.IRCPlugin;
import me.iarekylew00t.ircbot.managers.DataManager;

import org.pircbotx.Channel;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IRCBot extends PircBotX {
	private String VER = "1.0.0";
	private final Logger LOG = LoggerFactory.getLogger(IRCBot.class);

	public IRCBot(Configuration configuration) {
		super(configuration);
		new DataManager(this);
	}
	
	public void addPlugin(IRCPlugin plugin) {
		this.getConfiguration().getListenerManager().addListener(plugin);
	}
	
	public void setVersion(String version) {
		this.VER = version;
	}

	public void sendMessage(String channel, String message) {
		this.sendIRC().message(channel, message);
	}
	
	public void sendNotice(String channel, String notice) {
		this.sendIRC().notice(channel, notice);
	}
	
	public void sendMessageToAll(String message){
		for (Channel c : this.getUserChannelDao().getAllChannels()) {
			c.send().message(message);
		}
	}
	
	public void sendNoticeToAll(String notice) {
		for (Channel c : this.getUserChannelDao().getAllChannels()) {
			c.send().notice(notice);
		}
	}
	
	public void info(String message) {
		this.LOG.info(message);
	}
	
	public void debug(String message) {
		this.LOG.debug(message);
	}
	
	public void error(String message) {
		this.LOG.error(message);
	}
	
	public void warn(String message) {
		this.LOG.warn(message);
	}
	
	public String getVersion() {
		return this.VER;
	}
	
	public Logger getLogger() {
		return this.LOG;
	}
}
