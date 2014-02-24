package me.iarekylew00t.ircbot.hooks;

import java.util.UUID;

public class IRCCommand {
	private String CMD, DESC;
	private String[] ARGS, ALIAS;
	private String ID;

	public IRCCommand(String cmd, String[] args, String desc, String[] alias) {
		this.CMD = cmd;
		this.ARGS = args;
		this.DESC = desc;
		this.ALIAS = alias;
		this.ID = UUID.randomUUID().toString().split("-")[0]; //Generate a random ID
	}
	
	public String getName() {
		return this.CMD;
	}
	
	public String[] getArgs() {
		return this.ARGS;
	}
	
	public String getDesc() {
		return this.DESC;
	}
	
	public String[] getAlias() {
		return this.ALIAS;
	}
	
	public String getID() {
		return this.ID;
	}

	public boolean equals(IRCCommand cmd) {
		return this.CMD.equalsIgnoreCase(cmd.getName());
	}
	
	public boolean equals(String cmd) {
		return this.CMD.equalsIgnoreCase(cmd);
	}
	
	public boolean isAlias(String cmd) {
		for (int i = 0; i < this.ALIAS.length; i++) {
			if (this.ALIAS[i].equalsIgnoreCase(cmd)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.CMD + " (" + this.ID + ")";
	}
}
