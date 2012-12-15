package ClientRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

	public static String SERVER = "rmi://localhost:1099/";

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

		SERVER = properties.getProperty("SERVER");
	}
}
