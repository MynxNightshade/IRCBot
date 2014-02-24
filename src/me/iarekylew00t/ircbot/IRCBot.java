package me.iarekylew00t.ircbot;

import me.iarekylew00t.ircbot.hooks.IRCPlugin;
import me.iarekylew00t.ircbot.hooks.PluginManager;

import org.pircbotx.Channel;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IRCBot extends PircBotX {
	private final Logger LOG = LoggerFactory.getLogger(IRCBot.class);
	private String VER = "1.0.0";
	private PluginManager PLUGINS;

	public IRCBot(Configuration configuration) {
		super(configuration);
		this.PLUGINS = new PluginManager(this); //Added for security reasons
	}
	
	public void addPlugin(IRCPlugin plugin) {
		this.getConfiguration().getListenerManager().addListener(plugin);
	}
	
	public void removePlugin(IRCPlugin plugin) {
		this.getConfiguration().getListenerManager().removeListener(plugin); //Doesn't work?
	}
	
	public void setVersion(String version) {
		this.VER = version;
	}

	public synchronized void sendMessage(String channel, String message) {
		this.sendIRC().message(channel, message);
	}
	
	public synchronized void sendNotice(String channel, String notice) {
		this.sendIRC().notice(channel, notice);
	}
	
	public synchronized void sendMessageToAll(String message){
		for (Channel c : this.getUserChannelDao().getAllChannels()) {
			c.send().message(message);
		}
	}
	
	public synchronized void sendNoticeToAll(String notice) {
		for (Channel c : this.getUserChannelDao().getAllChannels()) {
			c.send().notice(notice);
		}
	}
	
	public synchronized void info(String message) {
		this.LOG.info(message);
	}
	
	public synchronized void debug(String message) {
		this.LOG.debug(message);
	}
	
	public synchronized void error(String message) {
		this.LOG.error(message);
	}
	
	public synchronized void warn(String message) {
		this.LOG.warn(message);
	}
	
	public void disconnect() {
		this.stopBotReconnect();
		this.sendRaw().rawLine("QUIT");
		this.PLUGINS.removeAllPlugins();
	}
	
	public void restart() {
		this.sendRaw().rawLine("QUIT");
	}
	
	public String getVersion() {
		return this.VER;
	}
	
	public Logger getLogger() {
		return this.LOG;
	}
}
