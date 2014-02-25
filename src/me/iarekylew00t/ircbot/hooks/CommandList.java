package me.iarekylew00t.ircbot.hooks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class CommandList {
	private List<IRCCommand> CMDS = new ArrayList<IRCCommand>();
	private static Comparator<IRCCommand> ALPHABETICAL_ORDER = new Comparator<IRCCommand>() {
		@Override
		public int compare(IRCCommand cmd1, IRCCommand cmd2) {
			return cmd1.getName().compareTo(cmd2.getName());
		}
	};
	
	public void add(String cmd, String args, String desc, String alias) {
		this.add(new IRCCommand(cmd, args.split(","), desc, alias.split(",")));
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
	
	public int size() {
		return this.CMDS.size();
	}
	
	public List<IRCCommand> toList() {
		return this.CMDS;
	}
	
	public void sort() {
		Collections.sort(this.CMDS, ALPHABETICAL_ORDER);
	}
	
	@Override
	public String toString() {
		return Arrays.toString(this.CMDS.toArray());
	}
}
