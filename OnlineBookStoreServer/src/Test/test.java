package Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import DBC.ConnectionFactory;
import Server.Const;

public class test {

	public static void main(String[] args) {
		Const.loadConfig();
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into order_item(orderid,memberid,bookisbn,nowprice,count) values (?,?,?,?,?)";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, 4);
			ps.setInt(2, 1);
			ps.setString(3, "testisbn4");
			ps.setDouble(4, 0.1);
			ps.setInt(5, 4);
			row =ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(row);

	}

}