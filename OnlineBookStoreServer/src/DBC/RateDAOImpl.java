package DBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import RMI.ResultMessage;

public class RateDAOImpl implements RateDAO {

	@Override
	public ResultMessage addRate(String bookISBN, int memberID, double rate) {
		ResultMessage exist = queryRate(bookISBN, memberID);
		if (!exist.isInvokeSuccess()) {
			return exist;
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into rate(isbn,memberid,rate) values(?,?,?)";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, bookISBN);
			ps.setInt(2, memberID);
			ps.setDouble(3, rate);
			row = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			return new ResultMessage(true, null, "评分成功！");
		}
		return new ResultMessage(false, null, "评分失败");

	}

	@Override
	public ResultMessage queryRate(String bookISBN, int memberID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from rate where isbn=? and memberid=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, bookISBN);
			ps.setInt(2, memberID);
			resultSet = ps.executeQuery();
			resultSet.last();
			row = resultSet.getRow();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row == 0) {
			return new ResultMessage(true, null, "尚未评价过");
		}
		return new ResultMessage(false, null, "已评价");
	}

	@Override
	public ResultMessage getRate(String bookISBN) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select avg(rate) from rate where isbn=? group by isbn";
		PreparedStatement ps;
		ResultSet resultSet = null;
		int row = 0;
		double rate = -1;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, bookISBN);
			resultSet = ps.executeQuery();
			resultSet.last();
			row = resultSet.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			try {
				rate = resultSet.getDouble(1);
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ArrayList<Double> dblarr = new ArrayList<Double>();
			dblarr.add(rate);
			return new ResultMessage(true, dblarr, "评价平均值");
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ResultMessage(false, null, "尚未有人评价过此书");
	}

}
