package DBC;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	public synchronized static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			String serverName = "localhost:3306";
			String databaseName = "OBSS";
			String url = "jdbc:mysql://" + serverName + "/" + databaseName;
			String userName = "root";
			String password = "lczw123";
			conn = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection(Connection con) {
		if (con != null) {
			try {
				if (!con.isClosed())
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 如果编码有问题i- -
	public static String encode(String in) {
		try {
			if (in == null || in.length() < 1)
				return "";
			in = in.trim();
			return new String(in.getBytes("GBK"), "latin1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}

	}

	public static String decode(String in) {
		try {
			if (in == null || in.length() < 1)
				return "";
			in = in.trim();
			return new String(in.getBytes("latin1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
}
