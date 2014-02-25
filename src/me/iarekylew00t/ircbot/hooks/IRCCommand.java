package me.iarekylew00t.ircbot.hooks;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class IRCCommand {
	private String CMD, DESC;
	private List<String> ARGS, ALIAS;
	private String ID;
	private int PERM;

	public IRCCommand(String cmd, String[] args, String desc, String[] alias, int perm) {
		if (perm < 0 || perm > 6) { throw new IllegalArgumentException("Command permission must be >= 0 and <= 6"); }
		this.CMD = cmd;
		this.ARGS = Arrays.asList(args);
		this.DESC = desc;
		this.PERM = perm;
		this.ALIAS = Arrays.asList(alias);
		this.ID = UUID.randomUUID().toString().split("-")[0]; //Generate a random ID
	}
	
	public String getName() {
		return this.CMD;
	}
	
	public List<String> getArgs() {
		return Collections.unmodifiableList(this.ARGS);
	}
	
	public String getDesc() {
		return this.DESC;
	}
	
	public List<String> getAlias() {
		return Collections.unmodifiableList(this.ALIAS);
	}
	
	public int getPermissionLevel() {
		return this.PERM;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public boolean hasArgs() {
		return this.ARGS != null && this.ARGS.size() != 0 && !this.ARGS.isEmpty();
	}
	
	public boolean hasAlias() {
		return this.ALIAS != null && this.ALIAS.size() != 0 && !this.ALIAS.isEmpty();
	}

	public boolean equals(IRCCommand cmd) {
		return this.CMD.equalsIgnoreCase(cmd.getName());
	}
	
	public boolean equals(String cmd) {
		return this.CMD.equalsIgnoreCase(cmd);
	}
	
	public boolean isAlias(String cmd) {
		for (int i = 0; i < this.ALIAS.size(); i++) {
			if (this.ALIAS.get(i).equalsIgnoreCase(cmd)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.CMD;
	}
}
