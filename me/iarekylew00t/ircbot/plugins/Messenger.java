package me.iarekylew00t.ircbot.plugins;

import java.io.File;
import java.util.HashMap;

import org.tmatesoft.sqljet.core.SqlJetTransactionMode;
import org.tmatesoft.sqljet.core.table.SqlJetDb;

import me.iarekylew00t.ircbot.hooks.IRCPlugin;

public class Messenger extends IRCPlugin {
	private static final String _NAME = "SkaianMessenger", _VER = "1.0.0";
	private static HashMap _CMDS;
	private static final File DB_FILE = new File("./files/messages.db");
	
	public Messenger() {
		super(_NAME, _VER, _CMDS);
	}
	
	@Override
	public void onEnable() throws Exception {
		if (!DB_FILE.exists()) {
			this.getLogger().warn("NO DATABASE FOUND - CREATING");
			String createTableQuery = "CREATE TABLE messages (nickname TEXT NOT NULL PRIMARY KEY , hostname TEXT NOT NULL , read BOOLEAN NOT NULL, date INTEGER NOT NULL)";
			String nickIndexQuery = "CREATE INDEX nickname ON messages(nickname,hostname)";
			String readIndexQuery = "CREATE INDEX read ON messages(read)";
			String dateIndexQuery = "CREATE INDEX date ON messages(date)";
			SqlJetDb db = SqlJetDb.open(DB_FILE, true);
			db.getOptions().setAutovacuum(true);
			db.beginTransaction(SqlJetTransactionMode.WRITE);
			try {
				db.getOptions().setUserVersion(1);
				db.createTable(createTableQuery);
				db.createIndex(nickIndexQuery);
				db.createIndex(readIndexQuery);
				db.createIndex(dateIndexQuery);
			} finally {
				db.commit();
			}
			this.getLogger().warn("DATABASE CREATED");
		}
	}
}
