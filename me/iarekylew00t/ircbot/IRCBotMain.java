package me.iarekylew00t.ircbot;

import java.io.File;
import java.nio.charset.Charset;

import me.iarekylew00t.ircbot.hooks.FileConfiguration;
import me.iarekylew00t.ircbot.plugins.*;
import me.iarekylew00t.ircbot.event.listeners.*;

import org.pircbotx.Configuration;

public class IRCBotMain {
	
	public static void main(String[] args) throws Exception {
		FileConfiguration config = new FileConfiguration(new File("./config.ini"));
		Configuration botConfig = new Configuration.Builder()
			/* Hardcoded Settings */
			.setEncoding(Charset.forName("UTF-8"))
			.setAutoNickChange(true)
			.setAutoReconnect(true)
			/* Bot Settings */
			.setLogin(config.getProperty("login"))
			.setName(config.getProperty("nick"))
			.setNickservPassword(!config.getProperty("nickPass").isEmpty() ? config.getProperty("nickPass") : null)
			.setServer(config.getProperty("server"), Integer.parseInt(config.getProperty("port")), config.getProperty("serverPass"))
			.addAutoJoinChannel(config.getProperty("channel"))
			.buildConfiguration();
		IRCBot bot = new IRCBot(botConfig);
		bot.setVersion("2.1.0_alpha");
		bot.getLogger().info("===== STARTING ARADIABOT V" + bot.getVersion() + " =====");
		
		/* BEGIN PLUGINS */
		bot.getConfiguration().getListenerManager().addListener(new TestPlugin());
		
		bot.startBot(); //transformers roll out
	}
}
