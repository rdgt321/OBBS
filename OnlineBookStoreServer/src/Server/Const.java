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
	public static int MAX_CLIENT = 0;
	public static int CON_FAIL = 0;
	public static int INTEGRAL_RATE = 100;
	public static int MAX_COLLECT = 20;

	public static int WAITING_FOR_PAYMENT = 0;
	public static int DILIVERING = 1;
	public static int DILIVERING_CASH = 2;
	public static int ORDER_CANCEL = 3;
	public static int TRADE_SUCCESS = 4;

	public static String SQLPATH = null;
	public static String BACKUPPATH = null;
	public static String LOG_ACCESS_PATH = null;
	public static String dbuser = null;
	public static String dbpass = null;
	public static String LAST_BIRTH = null;

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
		MAX_CLIENT = Integer.parseInt(properties.getProperty("MAX_CLIENT"));
		CON_FAIL = Integer.parseInt(properties.getProperty("CON_FAIL"));
		INTEGRAL_RATE = Integer.parseInt(properties
				.getProperty("INTEGRAL_RATE"));
		MAX_COLLECT = Integer.parseInt(properties.getProperty("MAX_COLLECT"));

		WAITING_FOR_PAYMENT = Integer.parseInt(properties
				.getProperty("WAITING_FOR_PAYMENT"));
		DILIVERING = Integer.parseInt(properties.getProperty("DILIVERING"));
		DILIVERING_CASH = Integer.parseInt(properties
				.getProperty("DILIVERING_CASH"));
		ORDER_CANCEL = Integer.parseInt(properties.getProperty("ORDER_CANCEL"));
		TRADE_SUCCESS = Integer.parseInt(properties
				.getProperty("TRADE_SUCCESS"));

		SQLPATH = properties.getProperty("SQLPATH");
		BACKUPPATH = properties.getProperty("BACKUPPATH");
		LOG_ACCESS_PATH = properties.getProperty("LOG_ACCESS_PATH");
		LAST_BIRTH = properties.getProperty("LAST_BIRTH");

		dbuser = Encrypt.decode(properties.getProperty("dbuser"));
		dbpass = Encrypt.decode(properties.getProperty("dbpass"));
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
