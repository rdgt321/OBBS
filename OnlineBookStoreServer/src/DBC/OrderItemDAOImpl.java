package DBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import RMI.ResultMessage;
import Sale.ItemPO;

public class OrderItemDAOImpl implements OrderItemDAO {

	private ArrayList<ItemPO> map(ResultSet resultSet) {
		ArrayList<ItemPO> polist = null;
		int orderID = 0;
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
					orderID = resultSet.getInt(1);
					memberID = resultSet.getInt(2);
					bookISBN = resultSet.getString(3);
					nowprice = resultSet.getDouble(4);
					count = resultSet.getInt(5);
					polist.add(new ItemPO(orderID, memberID, bookISBN,
							nowprice, count));
				}
				return polist;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized ResultMessage addOrderItem(int orderID, ItemPO itemPO) {
		ResultMessage isExist = queryOrderItem(orderID, itemPO);
		if (isExist.isInvokeSuccess()) {
			return updateOrderItem(orderID, itemPO);
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into order_item(orderid,memberid,bookisbn,nowprice,count) values (?,?,?,?,?)";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderID);
			ps.setInt(2, itemPO.getmemberID());
			ps.setString(3, itemPO.getBookISBN());
			ps.setDouble(4, itemPO.getNowprice());
			ps.setInt(5, itemPO.getCount());
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			return new ResultMessage(true, null, "add order item success");
		}
		return new ResultMessage(false, null, "add order item fail");
	}

	@Override
	public synchronized ResultMessage updateOrderItem(int orderID, ItemPO itemPO) {
		ResultMessage isExist = queryOrderItem(orderID, itemPO);
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null,
					"update failed,no such order item");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "update order_item set nowprice=?,count=? where orderid=? and bookisbn=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setDouble(1, itemPO.getNowprice());
			ps.setInt(2, itemPO.getCount());
			ps.setInt(3, itemPO.getOrderID());
			ps.setString(4, itemPO.getBookISBN());
			row = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			return new ResultMessage(true, null, "update order item success");
		}
		return new ResultMessage(false, null, "update order fail");
	}

	@Override
	public synchronized ResultMessage deleteOrderItem(int orderID, ItemPO itemPO) {
		ResultMessage isExist = queryOrderItem(orderID, itemPO);
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null,
					"delete failed,no such order item");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from order_item where orderid=? and bookisbn=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderID);
			ps.setString(2, itemPO.getBookISBN());
			row = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			return new ResultMessage(true, null, "delete order item success");
		}
		return new ResultMessage(false, null, "delete order fail");
	}

	@Override
	public synchronized ResultMessage queryOrderItem(int orderID, ItemPO itemPO) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from order_item where orderid=? and bookisbn=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderID);
			ps.setString(2, itemPO.getBookISBN());
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
			return new ResultMessage(true, polist, "query ok,item return");
		}
		return new ResultMessage(false, null, "no such item in order");
	}

	@Override
	public synchronized ResultMessage getOrderItems(int orderID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from order_item where orderid=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderID);
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
			return new ResultMessage(true, polist, "query ok,items return");
		}
		return new ResultMessage(false, null, "no such orderid");
	}

	@Override
	public ResultMessage queryOrderItem(String bookISBN, int memberID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from order_item left join orders on order_item.orderid = orders.orderid where order_item.bookisbn=? and order_item.memberid=? and orders.state in (1,4)";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, bookISBN);
			ps.setInt(2, memberID);
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
			return new ResultMessage(true, null, "购买过此书");
		}
		return new ResultMessage(false, null, "未曾购买");
	}
}
