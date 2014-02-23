package me.iarekylew00t.ircbot.hooks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CommandList {
	private Set<Command> CMDS;
	
	public CommandList() {
		this.CMDS = new HashSet<Command>();
	}
	
	public void add(String cmd, String args, String desc, String alias) {
		this.add(new Command(cmd, args.split(","), desc, alias.split(",")));
	}

	public void add(Command cmd) {
		this.CMDS.add(cmd);
	}

	public void remove(Command cmd) {
		this.remove(cmd.toString());
	}
	
	public void remove(String cmd) {
		for (Command c : this.CMDS) {
			if (c.equals(cmd)) {
				this.CMDS.remove(c);
			}
		}
		throw new NullPointerException("'" + cmd + "' is not a valid command");
	}
	
	public boolean contains(Command cmd) {
		for (Command c : this.CMDS) {
			if (c.equalsIgnoreCase(cmd)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean contains(String cmd) {
		for (Command c : this.CMDS) {
			if (c.equalsIgnoreCase(cmd)) {
				return true;
			}
		}
		return false;
	}
	
	public Command get(String name) {
		for (Command cmd : this.CMDS) {
			if (cmd.equalsIgnoreCase(name)) {
				return cmd;
			}
		}
		throw new NullPointerException("'" + name + "' is not a valid command");
	}
	
	public int size() {
		return this.CMDS.size();
	}
	
	public Set<Command> toSet() {
		return this.CMDS;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(this.CMDS.toArray());
	}
}
