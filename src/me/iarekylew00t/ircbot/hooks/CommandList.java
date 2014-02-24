package me.iarekylew00t.ircbot.hooks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public final class CommandList {
	private Set<IRCCommand> CMDS = new HashSet<IRCCommand>();
	
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
	
	public Set<IRCCommand> toSet() {
		return this.CMDS;
	}
	
	public Set<IRCCommand> sort() {
		return new TreeSet(this.CMDS);
	}
	
	@Override
	public String toString() {
		return Arrays.toString(this.CMDS.toArray());
	}
}
