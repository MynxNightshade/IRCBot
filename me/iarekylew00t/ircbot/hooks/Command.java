package me.iarekylew00t.ircbot.hooks;

import java.util.Arrays;

public class Command {
	private String CMD, DESC;
	private String[] ARGS, ALIAS;

	public Command(String cmd, String[] args, String desc, String[] alias) {
		this.CMD = cmd;
		this.ARGS = args;
		this.DESC = desc;
		this.ALIAS = alias;
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

	public boolean equals(Command cmd) {
		if (this.CMD.equals(cmd.getName())) {
			return true;
		}
		for (int i = 0; i < this.ALIAS.length; i++) {
			if (this.ALIAS[i].equals(cmd.getAlias()[i])) {
				return true;
			}
		}
		return false;
	}

	public boolean equals(String cmd) {
		if (this.CMD.equals(cmd)) {
			return true;
		}
		for (int i = 0; i < this.ALIAS.length; i++) {
			if (this.ALIAS[i].equals(cmd)) {
				return true;
			}
		}
		return false;
	}

	public boolean equalsIgnoreCase(Command cmd) {
		if (this.CMD.equalsIgnoreCase(cmd.getName())) {
			return true;
		}
		for (int i = 0; i < this.ALIAS.length; i++) {
			if (this.ALIAS[i].equalsIgnoreCase(cmd.getAlias()[i])) {
				return true;
			}
		}
		return false;
	}
	
	public boolean equalsIgnoreCase(String cmd) {
		if (this.CMD.equalsIgnoreCase(cmd)) {
			return true;
		}
		for (int i = 0; i < this.ALIAS.length; i++) {
			if (this.ALIAS[i].equalsIgnoreCase(cmd)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.CMD + "," + Arrays.toString(this.ARGS) + "," + this.DESC + "," + Arrays.toString(this.ALIAS);
	}
}
