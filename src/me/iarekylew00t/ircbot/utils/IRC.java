package me.iarekylew00t.ircbot.utils;

import org.pircbotx.Channel;
import org.pircbotx.User;

public final class IRC {
	public static final int NORMAL		= 0;
	public static final int VOICE		= 1;
	public static final int HALF_OP		= 2;
	public static final int OP			= 3;
	public static final int SUPER_OP	= 4;
	public static final int OWNER		= 5;
	public static final int IRC_OP		= 6;
	
	public static int getPermissionLevel(User u, Channel c) {
		if (u.isIrcop()) {
			return IRC_OP;
		} else if (u.getChannelsOwnerIn().contains(c)) {
			return OWNER;
		} else if (u.getChannelsSuperOpIn().contains(c)) {
			return SUPER_OP;
		} else if (u.getChannelsOpIn().contains(c)) {
			return OP;
		} else if (u.getChannelsHalfOpIn().contains(c)) {
			return HALF_OP;
		} else if (u.getChannelsVoiceIn().contains(c)) {
			return VOICE;
		}
		return NORMAL;
	}
}
