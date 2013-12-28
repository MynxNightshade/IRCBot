package me.iarekylew00t.ircbot.event.listeners;

import me.iarekylew00t.ircbot.IRCBot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.ActionEvent;
import org.pircbotx.hooks.events.ConnectEvent;
import org.pircbotx.hooks.events.DisconnectEvent;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.KickEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.ModeEvent;
import org.pircbotx.hooks.events.NickChangeEvent;
import org.pircbotx.hooks.events.NoticeEvent;
import org.pircbotx.hooks.events.PartEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.events.QuitEvent;
import org.pircbotx.hooks.events.ServerPingEvent;
import org.slf4j.Logger;

public class LogListener extends ListenerAdapter {
	private static IRCBot _BOT;
	private final Logger _LOG;
	
	public LogListener(IRCBot bot) {
		_BOT = bot;
		this._LOG = bot.getLogger();
	}
	
	@Override
	public void onConnect(ConnectEvent e) {
		this._LOG.info("CONNECTED TO " + _BOT.getServerInfo());
	}
	
	@Override
	public void onDisconnect(DisconnectEvent e) {
		this._LOG.info("DISCONNECTED FROM " + _BOT.getServerInfo());
	}
	
	@Override
	public void onServerPing(ServerPingEvent e) {
		this._LOG.info("<<< PING " + e.getResponse());
	}
	
	@Override
	public void onJoin(JoinEvent e) {
		this._LOG.info(e.getUser().getNick() + "!" + e.getUser().getLogin() + "@" + e.getUser().getHostmask() + " JOINED " + e.getChannel().getName());
	}
	
	@Override
	public void onPart(PartEvent e) {
		this._LOG.info(e.getUser().getNick() + "!" + e.getUser().getLogin() + "@" + e.getUser().getHostmask() + " PARTED " + e.getChannel().getName());
	}
	
	@Override
	public void onQuit(QuitEvent e) {
		this._LOG.info(e.getUser().getNick() + "!" + e.getUser().getLogin() + "@" + e.getUser().getHostmask() + " QUIT " + "(" + e.getReason() + ")");
	}
	
	@Override
	public void onAction(ActionEvent e) {
		this._LOG.info(e.getUser().getNick() + "!" + e.getUser().getLogin() + " ACTION " + e.getAction());
	}
	
	@Override
	public void onMode(ModeEvent e) {
		if (e.getUser().getNick().equals("NickServ") || e.getUser().getNick().equals("ChanServ")) {
			this._LOG.info(e.getUser().getNick() + e.getUser().getLogin() + " SET MODE " + e.getMode());
		} else {
			this._LOG.info(e.getUser().getNick() + "!" + e.getUser().getLogin() + " SET MODE " + e.getMode());
		}
	}
	
	@Override
	public void onNickChange(NickChangeEvent e) {
		this._LOG.info(e.getOldNick() + "!" + e.getUser().getLogin() + " SET NICK " + e.getNewNick());
	}
	
	@Override
	public void onMessage(MessageEvent e) {
		if (!e.getMessage().startsWith("$")) {
			this._LOG.info(e.getUser().getNick() + "!" + e.getUser().getLogin() +  " (" + e.getChannel().getName() + "): " + e.getMessage());
		}
	}
	
	@Override
	public void onPrivateMessage(PrivateMessageEvent e) {
		this._LOG.info("<<< PRIVMSG " + e.getUser().getNick() + "!" + e.getUser().getHostmask() + ": " + e.getMessage());
	}
	
	@Override
	public void onNotice(NoticeEvent e) {
		if (e.getUser().getNick().equals("NickServ") || e.getUser().getNick().equals("ChanServ")) {
			this._LOG.info(e.getUser().getNick() + e.getUser().getLogin() + " NOTICE " + e.getNotice());
		} else {
			this._LOG.info(e.getUser().getNick() + "!" + e.getUser().getLogin() + " NOTICE " + e.getNotice());
		}
	}
	
	@Override
	public void onKick(KickEvent e) {
		this._LOG.info(e.getUser().getNick() + "!" + e.getUser().getLogin() + " KICKED " + e.getRecipient().getNick() + "!" + e.getRecipient().getLogin() + "(" + e.getReason() + ")");
	}
}
