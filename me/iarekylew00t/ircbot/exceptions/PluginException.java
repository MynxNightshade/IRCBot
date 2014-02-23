package me.iarekylew00t.ircbot.exceptions;

public class PluginException extends RuntimeException {
	private static final long serialVersionUID = -6550589076149552514L;

	public PluginException() {
		super();
	}
	
	public PluginException(String message) {
		super(message);
	}
	
	public PluginException(Throwable cause) {
		super(cause);
	}
	
	public PluginException(String message, Throwable cause) {
		super(message, cause);
	}
}
