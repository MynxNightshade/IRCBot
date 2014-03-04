package me.iarekylew00t.ircbot;

import java.nio.charset.Charset;

import org.pircbotx.Configuration;

import me.iarekylew00t.ircbot.event.listeners.CommandListener;
import me.iarekylew00t.ircbot.plugins.*;
import me.iarekylew00t.ircbot.utils.IRCConfiguration;

public class IRCBotMain {
	private static IRCBot BOT;
	
	public static void main(String[] args) throws Exception {
		/* Read IRC config file */
		IRCConfiguration config = new IRCConfiguration();
		if (config.firstTimeLoad()) {
			/* Create default config*/
			config.setProp("nick", "IRCBot");
			config.setProp("nickPass", "");
			config.setProp("login", "PircBotX");
			config.setProp("server", "irc.esper.net");
			config.setProp("serverPass", "");
			config.setProp("port", "6667");
			config.setProp("channel", "#channel1,#channel2,#channel3");
			config.setProp("identServ", "false");
			config.update();
		}
		
		Configuration botConfig = new Configuration.Builder()
			/* Hardcoded Settings */
			.setEncoding(Charset.forName("UTF-8"))
			.setAutoNickChange(true)
			.setAutoReconnect(true)
			/* Bot Settings */
			.setLogin(config.getProp("login"))
			.setName(config.getProp("nick"))
			.setNickservPassword(!config.getProp("nickPass").isEmpty() ? config.getProp("nickPass") : null)
			.setServer(config.getProp("server"), Integer.parseInt(config.getProp("port")), config.getProp("serverPass"))
			.addAutoJoinChannel(config.getProp("channel"))
			.buildConfiguration();
		BOT = new IRCBot(botConfig);
		BOT.setVersion("2.1.0_beta");
		BOT.getLogger().info("===== STARTING ARADIABOT V" + BOT.getVersion() + " =====");
		
		/* Built-in Listeners */
		BOT.addListener(new CommandListener(BOT));
		
		/* BEGIN PLUGINS */
		BOT.addPlugin(new BasicCommandPlugin());
		
		BOT.startBot(); //transformers roll out
	}
}
