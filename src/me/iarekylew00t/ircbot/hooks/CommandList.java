package me.iarekylew00t.ircbot.hooks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.iarekylew00t.ircbot.utils.IRC;

public final class CommandList {
	private List<IRCCommand> CMDS = new ArrayList<IRCCommand>();
	private static Comparator<IRCCommand> ALPHABETICAL_ORDER = new Comparator<IRCCommand>() {
		@Override
		public int compare(IRCCommand cmd1, IRCCommand cmd2) {
			return cmd1.getName().compareTo(cmd2.getName());
		}
	};
	
	public void add(String cmd, String args, String desc, String alias) {
		this.add(cmd, args, desc, alias, IRC.NORMAL);
	}
	
	public void add(String cmd, String args, String desc, String alias, int perm) {
		this.add(new IRCCommand(cmd, (args.isEmpty() || args.equals(null) ? new String[0] : args.split(",")), desc, (alias.isEmpty() || alias.equals(null) ? new String[0] : alias.split(",")), perm));
	}

	public void add(IRCCommand cmd) {
		this.CMDS.add(cmd);
	}

	public void remove(IRCCommand cmd) {
		this.remove(cmd.toString());
	}
	
	public void remove(String cmd) {
		for (IRCCommand c : this.CMDS) {
			if (c.equals(cmd)) {
				this.CMDS.remove(c);
			}
		}
		throw new NullPointerException("'" + cmd + "' is not a valid command");
	}
	
	public void clear() {
		this.CMDS.clear();
	}
	
	public boolean contains(IRCCommand cmd) {
		for (IRCCommand c : this.CMDS) {
			if (c.equals(cmd)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean contains(String cmd) {
		for (IRCCommand c : this.CMDS) {
			if (c.equals(cmd) || c.isAlias(cmd)) {
				return true;
			}
		}
		return false;
	}
	
	public IRCCommand get(String name) {
		for (IRCCommand cmd : this.CMDS) {
			if (cmd.equals(name) || cmd.isAlias(name)) {
				return cmd;
			}
		}
		throw new NullPointerException("'" + name + "' is not a valid command");
	}
	
	public String list() {
		return this.list(IRC.IRC_OP);
	}
	
	public String list(int perm) {
		String list = "";
		for (IRCCommand c : this.CMDS) {
			if (perm >= c.getPermissionLevel()) {
				list += c.getName() + " ";
			}
		}
		return list.trim();
	}
	
	public List<IRCCommand> toList() {
		return Collections.unmodifiableList(this.CMDS);
	}
	
	public int size() {
		return this.CMDS.size();
	}
	
	public void sort() {
		Collections.sort(this.CMDS, ALPHABETICAL_ORDER);
	}
	
	@Override
	public String toString() {
		return Arrays.toString(this.CMDS.toArray());
	}
}
