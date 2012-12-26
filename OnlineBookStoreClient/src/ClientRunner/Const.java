package ClientRunner;

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

	public static int WAITING_FOR_PAYMENT = 0;
	public static int DILIVERING = 1;
	public static int DILIVERING_CASH = 2;
	public static int ORDER_CANCEL = 3;
	public static int TRADE_SUCCESS = 4;
	public static int SAVE_PASS = 0;
	public static int SAVE_TYPE = 0;

	public static int INTEGRAL_RATE = 100;

	public static String SERVER = "rmi://localhost:1099/";
	public static String USER_NAME = "";
	public static String USER_PASS = "";

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

		WAITING_FOR_PAYMENT = Integer.parseInt(properties
				.getProperty("WAITING_FOR_PAYMENT"));
		DILIVERING = Integer.parseInt(properties.getProperty("DILIVERING"));
		DILIVERING_CASH = Integer.parseInt(properties
				.getProperty("DILIVERING_CASH"));
		ORDER_CANCEL = Integer.parseInt(properties.getProperty("ORDER_CANCEL"));
		TRADE_SUCCESS = Integer.parseInt(properties
				.getProperty("TRADE_SUCCESS"));

		INTEGRAL_RATE = Integer.parseInt(properties
				.getProperty("INTEGRAL_RATE"));
		SAVE_PASS = Integer.parseInt(properties.getProperty("SAVE_PASS"));
		SAVE_TYPE = Integer.parseInt(properties.getProperty("SAVE_TYPE"));

		SERVER = properties.getProperty("SERVER");
		USER_NAME = Encrypt.decode(properties.getProperty("USER_NAME"));
		USER_PASS = Encrypt.decode(properties.getProperty("USER_PASS"));
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
