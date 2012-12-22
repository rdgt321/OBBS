package DBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import Book.BookPO;
import RMI.ResultMessage;
import Server.Const;

public class CollectDAOImpl implements CollectDAO {

	private ArrayList<BookPO> map(ResultSet resultSet) {
		ArrayList<BookPO> polist = null;
		String name = null, ISBN = null, author = null, press = null, description = null;
		int directoryID = 0;
		Calendar publishDate = null;
		double price = 0, specialPrice = 0;
		try {
			resultSet.last();
			int len = 0;
			if ((len = resultSet.getRow()) != 0) {
				polist = new ArrayList<BookPO>();
				resultSet.beforeFirst();
				for (int i = 0; i < len; i++) {
					resultSet.next();
					ISBN = resultSet.getString(2);
					polist.add(new BookPO(name, ISBN, author, press,
							description, directoryID, publishDate, price,
							specialPrice));
				}
				return polist;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized ResultMessage bookCollect(String bookISBN, int memberID) {
		ResultMessage isExist = queryCollect(memberID, bookISBN);
		if (isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "already collect this book");
		}
		ResultMessage booknum = getCollectedBook(memberID);
		if (booknum.isInvokeSuccess()
				&& booknum.getResultSet().size() >= Const.MAX_COLLECT) {
			return new ResultMessage(false, null, "已达书架上线，无法收藏更多图书");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into collect(memberid,bookisbn) values (?,?)";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			ps.setString(2, bookISBN);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			return new ResultMessage(true, null, "collect success");
		}
		return new ResultMessage(false, null,
				"collect book failed,please check again");
	}

	@Override
	public synchronized ResultMessage cancelCollect(String bookISBN,
			int memberID) {
		ResultMessage isExist = queryCollect(memberID, bookISBN);
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "not collect this book");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from collect where memberid=? and bookisbn=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			ps.setString(2, bookISBN);
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			return new ResultMessage(true, null, "cancel collect success");
		}
		return new ResultMessage(false, null, "cancel collect failed");
	}

	@Override
	public synchronized ResultMessage queryCollect(int memberID, String bookISBN) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from collect where memberid=? and bookisbn=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			ps.setString(2, bookISBN);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<BookPO> polist = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (polist != null) {
			return new ResultMessage(true, polist,
					"collect isbns return,need to be cast ");
		}
		return new ResultMessage(false, null, "query collect failed");
	}

	@Override
	public synchronized ResultMessage getCollectedBook(int memberID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from collect where memberid=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<BookPO> polist = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (polist != null) {
			return new ResultMessage(true, polist,
					"query ok,return collected book,collect isbns return,need to be cast ");
		}
		return new ResultMessage(false, null, "no book in collection");
	}

}
