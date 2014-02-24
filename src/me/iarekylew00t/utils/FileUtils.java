package me.iarekylew00t.utils;

import java.io.*;
import java.nio.charset.Charset;

public final class FileUtils {
	private static final String newLine = System.getProperty("line.separator");

	public static void createFile(File file) {
		try {
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8")));
			out.write('\ufeff');
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void createFile(String file) {
		try {
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file)), Charset.forName("UTF-8")));
			out.write('\ufeff');
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeToFile(File file, String message, boolean append) {
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), Charset.forName("UTF-8")));
			out.write(message + (append ? newLine : ""));
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String readFile(File file, int line) {
		String curLine = "";
		try {
			BufferedReader out = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
			for (int i = 0; i < line; i++) { curLine = out.readLine(); }
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return curLine;
	}
	
	public static int countLines(File file) {
		int count = 0;
		try {
			BufferedReader out = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8")));
			while (out.readLine() != null) { count++; }
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return count;
	}
}
