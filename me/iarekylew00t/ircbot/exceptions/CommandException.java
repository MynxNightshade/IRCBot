package me.iarekylew00t.ircbot.exceptions;

public class CommandException extends RuntimeException {
	private static final long serialVersionUID = -71346675812889319L;

	public CommandException() {
		super();
	}
	
	public CommandException(String message) {
		super(message);
	}
	
	public CommandException(Throwable cause) {
		super(cause);
	}
	
	public CommandException(String message, Throwable cause) {
		super(message, cause);
	}
}
