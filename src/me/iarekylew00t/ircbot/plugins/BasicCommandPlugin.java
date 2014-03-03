package me.iarekylew00t.ircbot.plugins;

import org.pircbotx.Channel;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

import me.iarekylew00t.ircbot.hooks.Command;
import me.iarekylew00t.ircbot.hooks.CommandList;
import me.iarekylew00t.ircbot.hooks.IRCPlugin;

public class BasicCommandPlugin extends IRCPlugin {
	private static final String NAME = "BasicCommandPlugin", VER = "1.0.0", AUTHOR = "IAreKyleW00t";
	private static CommandList CMDS = new CommandList();
	static {
		CMDS.add("8ball","<question>","Simulates an 8-ball and ouput's an 8-ball response.","");
		CMDS.add("date","","Displays the current date.","");
		CMDS.add("time","","Displays the current time (EST).","");
		CMDS.add("fliptable","","Performs an awesome tableflip.","flip");
		CMDS.add("unfliptable","","Performs a not-so-awesome untableflip...","unflip");
		CMDS.add("mspa","","Provides a link to the MSPA website.","");
		CMDS.add("gigapause","","Provides a link to the Gigapause website.","giga");
		CMDS.add("lmtyahs","","Provides a link to the official \"Let me tell you about Homestuck\" page from the Kickstarter.","");
		CMDS.add("gearup","","Gives you armor for battle - or just to look cool.","");
		CMDS.add("heal","[user]","Heals yourself or a specific user.","");
		CMDS.add("kill","<user>","Kills a user.","");
		CMDS.add("radio","","Provides a link to Skaianet Radio.","");
		CMDS.add("irc","","Provides a link to the Webchat.","");
		CMDS.add("rules","","Provides a link to the Rules.","");
		CMDS.add("random","[min],[max]","Outputs a random number from 0-10, a number from 0-[MIN], or even from [MIN]-[MAX].","rand");
		CMDS.add("pap","<user>","Paps a user.","");
		CMDS.add("poke","","Pokes a user.","");
		CMDS.add("revive","<user>","Revives a fallen comrade.","");
		CMDS.add("serve","<item>,[user]","Serves something of your choosing to everyone, or to a specific user.","");
		CMDS.add("shoot","<user>","Shoots a user.","");
		CMDS.add("slap","<user>","Slaps a user.","");
		CMDS.add("source","","Provides a link to Aradiabot's source code.","src,git");
		CMDS.add("songlist","","Provides a link to Skaianet's songlist.","");
		CMDS.add("ping","[url]","Tests your latency with the server or tests a websites response time if a URL is given.","");
	}
	
	public BasicCommandPlugin() {
		super(NAME, VER, CMDS, AUTHOR);
	}
	
	/*@Override
	public void onMessage(MessageEvent e) {
		if (this.isEnabled() && e.getMessage().startsWith("$")) {
			Command cmd = new Command(e.getMessage(), e.getUser(), e.getChannel());
			
			if (cmd.getCmd().equals("8ball")) {
				//TODO Write 8-ball command
				return;
			}
		}
	}*/

	@Override
	public void onCommand(User sender, Channel channel, String cmd, Object[] args) {
		if(cmd.equalsIgnoreCase("8ball")) {
			channel.send().message("Workings great!");
			return;
		}
	}

}
