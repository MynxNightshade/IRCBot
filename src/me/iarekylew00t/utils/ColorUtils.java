package me.iarekylew00t.utils;

import org.pircbotx.Colors;

public final class ColorUtils {

	public static String removeColors(String message) {
		String clearMsg = message
					.replaceAll(Colors.BLACK, "")
					.replaceAll(Colors.BLUE, "")
					.replaceAll(Colors.BOLD, "")
					.replaceAll(Colors.BROWN, "")
					.replaceAll(Colors.CYAN, "")
					.replaceAll(Colors.DARK_BLUE, "")
					.replaceAll(Colors.DARK_GRAY, "")
					.replaceAll(Colors.DARK_GREEN, "")
					.replaceAll(Colors.GREEN, "")
					.replaceAll(Colors.LIGHT_GRAY, "")
					.replaceAll(Colors.MAGENTA, "")
					.replaceAll(Colors.NORMAL, "")
					.replaceAll(Colors.OLIVE, "")
					.replaceAll(Colors.PURPLE, "")
					.replaceAll(Colors.RED, "")
					.replaceAll(Colors.REVERSE, "")
					.replaceAll(Colors.TEAL, "")
					.replaceAll(Colors.UNDERLINE, "")
					.replaceAll(Colors.WHITE, "")
					.replaceAll(Colors.YELLOW, "");
		
		return clearMsg;
	}
}
