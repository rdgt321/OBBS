package DBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import RMI.UserAgent;
import Server.Const;

public class ConnectionFactory {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			String serverName = "localhost";
			String databaseName = "OBSS";
			String url = "jdbc:mysql://" + serverName + "/" + databaseName
					+ "?useUnicode=true&characterEncoding=gbk";
			String userName = Const.dbuser;
			String password = Const.dbpass;
			con = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static Connection getConnection(UserAgent userAgent) {
		Connection con = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			String serverName = "localhost";
			String databaseName = "OBSS";
			String url = "jdbc:mysql://" + serverName + "/" + databaseName
					+ "?useUnicode=true&characterEncoding=gbk";
			String userName = userAgent.getName();
			String password = userAgent.getPassword();
			con = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static Connection firstConnection(UserAgent userAgent) {
		Connection con = null;
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			String serverName = "localhost";
			String url = "jdbc:mysql://" + serverName;
			String userName = userAgent.getName();
			String password = userAgent.getPassword();
			con = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
