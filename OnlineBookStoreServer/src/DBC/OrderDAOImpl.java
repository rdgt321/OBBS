package DBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import RMI.ResultMessage;
import Sale.OrderPO;

public class OrderDAOImpl implements OrderDAO {

	private ArrayList<OrderPO> map(ResultSet resultSet) {
		ArrayList<OrderPO> polist = null;
		int orderID = 0;
		int memberID = 0;
		double totalprice = 0;
		Calendar date = Calendar.getInstance();
		int state = 0;
		try {
			resultSet.last();
			int len = 0;
			if ((len = resultSet.getRow()) != 0) {
				polist = new ArrayList<OrderPO>();
				resultSet.beforeFirst();
				for (int i = 0; i < len; i++) {
					resultSet.next();
					orderID = resultSet.getInt(1);
					memberID = resultSet.getInt(2);
					totalprice = resultSet.getDouble(3);
					date.setTimeInMillis(resultSet.getDate(4).getTime());
					state = resultSet.getInt(5);
					polist.add(new OrderPO(orderID, memberID, null, totalprice,
							date, state));
				}
				return polist;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage addOrder(OrderPO orderPO) {
		ResultMessage isExist = orderQuery(orderPO.getOrderID());
		if (isExist.isInvokeSuccess()) {
			return new ResultMessage(true, null, "orderid exist");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into orders(memberid,date,state) values(?,?,?)";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderPO.getMemberID());
			ps.setDate(2,
					new java.sql.Date(orderPO.getDate().getTimeInMillis()));
			ps.setInt(3, orderPO.getState());
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
			return new ResultMessage(true, null, "add order success");
		}
		return new ResultMessage(false, null, "add order failed");
	}

	@Override
	public ResultMessage updateOrder(OrderPO orderPO) {
		ResultMessage isExist = orderQuery(orderPO.getOrderID());
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "no such orderid");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "update orders set memberid=?,date=?,state=? where orderid=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderPO.getMemberID());
			ps.setDate(2,
					new java.sql.Date(orderPO.getDate().getTimeInMillis()));
			ps.setInt(3, orderPO.getState());
			ps.setInt(4, orderPO.getOrderID());
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
			return new ResultMessage(true, null, "update order success");
		}
		return new ResultMessage(false, null, "update order fail");
	}

	@Override
	public ResultMessage orderQuery(int orderID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from orders where orderid=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderID);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<OrderPO> po = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (po != null) {
			return new ResultMessage(true, po, "query ok,order return");
		}
		return new ResultMessage(false, null, "no such order");
	}

	@Override
	public ResultMessage deleteOrder(int orderID) {
		ResultMessage isExist = orderQuery(orderID);
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "no such orderID");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from orders where orderid=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, orderID);
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
			return new ResultMessage(true, null, "delete order success");
		}
		return new ResultMessage(false, null, "delete order failed");
	}

	@Override
	public ResultMessage getOrder(int memberID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from orders where memberid=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<OrderPO> polist = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (polist != null) {
			return new ResultMessage(true, polist, "query ok,orders return");
		}
		return new ResultMessage(false, null, "no orders in history");
	}

}
