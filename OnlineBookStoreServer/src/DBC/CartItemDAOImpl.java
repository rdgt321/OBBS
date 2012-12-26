package DBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import RMI.ResultMessage;
import Sale.ItemPO;

public class CartItemDAOImpl implements CartItemDAO {

	private ArrayList<ItemPO> map(ResultSet resultSet) {
		ArrayList<ItemPO> polist = null;
		int memberID = 0;
		String bookISBN = null;
		double nowprice = 0;
		int count = 0;
		try {
			resultSet.last();
			int len = 0;
			if ((len = resultSet.getRow()) != 0) {
				polist = new ArrayList<ItemPO>();
				resultSet.beforeFirst();
				for (int i = 0; i < len; i++) {
					resultSet.next();
					memberID = resultSet.getInt(1);
					bookISBN = resultSet.getString(2);
					nowprice = resultSet.getDouble(3);
					count = resultSet.getInt(4);
					polist.add(new ItemPO(-1, memberID, bookISBN, nowprice,
							count));
				}
				return polist;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized ResultMessage addCartItem(int memberID, ItemPO itemPO) {
		ResultMessage isExist = queryCartItem(memberID, itemPO.getBookISBN());
		if (isExist.isInvokeSuccess()) {
			return isExist;
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into cart_item(memberid,bookisbn,nowprice,count) values (?,?,?,?)";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			ps.setString(2, itemPO.getBookISBN());
			ps.setDouble(3, itemPO.getNowprice());
			ps.setInt(4, itemPO.getCount());
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
			return new ResultMessage(true, null, "添加至购物车成功");
		}
		return new ResultMessage(false, null, "添加失败 请重新操作");
	}

	@Override
	public synchronized ResultMessage updateCartItem(int memberID, ItemPO itemPO) {
		ResultMessage isExist = queryCartItem(memberID, itemPO.getBookISBN());
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "该Item不存在 请重新操作");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "update cart_item set count=?,nowprice=? where memberid= ? and bookisbn = ?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, itemPO.getCount());
			ps.setDouble(2, itemPO.getNowprice());
			ps.setInt(3, memberID);
			ps.setString(4, itemPO.getBookISBN());
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			return new ResultMessage(true, null, "update cart_item success");
		}
		return new ResultMessage(false, null, "update cart_item fail");
	}

	@Override
	public synchronized ResultMessage deleteCartItem(int memberID,
			String bookISBN) {
		ResultMessage isExist = queryCartItem(memberID, bookISBN);
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "该Item不存在 请重新操作");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from cart_item where memberID = ? and bookisbn = ?";
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
			return new ResultMessage(true, null, "delete cart_item success");
		}
		return new ResultMessage(false, null, "delete cart_item fail");
	}

	@Override
	public synchronized ResultMessage queryCartItem(int memberID, String booISBN) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from cart_item where memberID = ? and bookisbn = ?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			ps.setString(2, booISBN);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<ItemPO> result = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result != null) {
			return new ResultMessage(true, result, "该书在购物车中");
		}
		return new ResultMessage(false, result, "该书不在购物车中");
	}

	@Override
	public synchronized ResultMessage clearCart(int memberID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from cart_item where memberID = ?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
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
			return new ResultMessage(true, null, "清空购物车成功");
		}
		return new ResultMessage(false, null, "清空购物车成功");
	}

	@Override
	public synchronized ResultMessage getBooksInCart(int memberID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from cart_item where memberID = ?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<ItemPO> polist = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (polist != null) {
			return new ResultMessage(true, polist, "查询成功，返回购物车");
		}
		return new ResultMessage(false, null, "购物车中尚未存放商品");
	}

}
