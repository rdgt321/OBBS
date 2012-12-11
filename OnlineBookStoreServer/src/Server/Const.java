package Server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Const {
	private static Properties properties = new Properties();
	public static int ADMIN = 0;
	public static int GENERALMANAGER = 1;
	public static int SALESMANAGER = 2;
	public static int MEMBER = 3;

	public static int SEARCH_BY_MULTI = 0;
	public static int SEARCH_BY_NAME = 1;
	public static int SEARCH_BY_AUTHOR = 2;

	public static int TIMEOUT = 10;
	public static int ROUTINE = 10;
	public static int BACKUP = 7;
	public static int FIRST_RUN = 1;
	public static String SQLPATH = null;
	public static String BACKUPPATH = null;
	public static String LOGPATH = null;
	public static String dbuser = null;
	public static String dbpass = null;

	public static void loadConfig() {
		InputStream is;
		try {
			is = new FileInputStream("config.xml");
			properties.loadFromXML(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ADMIN = Integer.parseInt(properties.getProperty("ADMIN"));
		GENERALMANAGER = Integer.parseInt(properties
				.getProperty("GENERALMANAGER"));
		SALESMANAGER = Integer.parseInt(properties.getProperty("SALESMANAGER"));
		MEMBER = Integer.parseInt(properties.getProperty("MEMBER"));

		SEARCH_BY_MULTI = Integer.parseInt(properties
				.getProperty("SEARCH_BY_MULTI"));
		SEARCH_BY_NAME = Integer.parseInt(properties
				.getProperty("SEARCH_BY_NAME"));
		SEARCH_BY_AUTHOR = Integer.parseInt(properties
				.getProperty("SEARCH_BY_AUTHOR"));
		ROUTINE = Integer.parseInt(properties.getProperty("ROUTINE"));
		TIMEOUT = Integer.parseInt(properties.getProperty("TIMEOUT"));
		FIRST_RUN = Integer.parseInt(properties.getProperty("FIRSTRUN"));
		BACKUP = Integer.parseInt(properties.getProperty("BACKUP"));
		SQLPATH = properties.getProperty("SQLPATH");
		BACKUPPATH = properties.getProperty("BACKUPPATH");
		LOGPATH = properties.getProperty("LOGPATH");
		dbuser = properties.getProperty("dbuser");
		dbpass = properties.getProperty("dbpass");
	}

	public static void store(String key, String value) {
		OutputStream os;
		try {
			os = new FileOutputStream("config.xml");
			properties.setProperty(key, value);
			properties.storeToXML(os, "Const");
			loadConfig();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
