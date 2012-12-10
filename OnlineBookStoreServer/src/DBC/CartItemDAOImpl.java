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
					memberID = resultSet.getInt(2);
					bookISBN = resultSet.getString(3);
					nowprice = resultSet.getDouble(4);
					count = resultSet.getInt(5);
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
	public ResultMessage addCartItem(int memberID, ItemPO itemPO) {
		ResultMessage isExist = queryCartItem(memberID, itemPO.getBookISBN());
		if (isExist.isInvokeSuccess()) {
			return updateCartItem(memberID, itemPO);
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
	public ResultMessage updateCartItem(int memberID, ItemPO itemPO) {
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
	public ResultMessage deleteCartItem(int memberID, String bookISBN) {
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
	public ResultMessage queryCartItem(int memberID, String booISBN) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from cart_item where memberID = ? and bookisbn = ?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			ps.setString(2, booISBN);
			resultSet = ps.executeQuery();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<ItemPO> result = map(resultSet);
		if (result != null) {
			return new ResultMessage(true, result, "该物品存在 返回结果");
		}
		return new ResultMessage(false, result, "该物品不存在");
	}

	@Override
	public ResultMessage clearCart(int memberID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from cart_item where memberID = ?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			row = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			return new ResultMessage(true, null, "clear cart_item success");
		}
		return new ResultMessage(false, null, "clear cart_item fail");
	}

	@Override
	public ResultMessage getBooksInCart(int memberID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from cart_item where memberID = ?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			resultSet = ps.executeQuery();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<ItemPO> polist = map(resultSet);
		if (polist != null) {
			return new ResultMessage(true, polist, "get books in cart succes");
		}
		return new ResultMessage(true, null, "nothing in cart ");
	}

}
