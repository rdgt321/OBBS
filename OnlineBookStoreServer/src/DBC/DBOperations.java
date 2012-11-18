package DBC;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import com.sun.org.apache.xml.internal.security.keys.content.RetrievalMethod;

import RMI.ResultMessage;

public class DBOperations {
	public static ResultMessage insertMember(String name, String password,
			String phone, Calendar birth) throws SQLException {
		ResultMessage exist = queryMemberByName(name);
		if (exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "该用户名称已被占用，请重新输入");
		} else {
			Connection con = DBConnector.getConnection();
			String sql = "insert into member values (,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, phone);
			Date date = new Date(birth.getTime().getTime());
			ps.setDate(4, date);
			ResultSet resultSet = ps.executeQuery();
			DBConnector.closeConnection(con);
			return new ResultMessage(true, resultSet, "用户添加成功!");
		}
	}

	public static ResultMessage modifyMember(String memberID, String name,
			String password, String phone, Calendar birth) throws SQLException {
		ResultMessage exist = queryMemberByID(memberID);
		if (!exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "该用户ID不存在，请检查操作");
		} else {
			Connection con = DBConnector.getConnection();
			String sql = "update member set name=?,password=?,phone=?,birth=? where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, phone);
			Date date = new Date(birth.getTime().getTime());
			ps.setDate(4, date);
			ps.setInt(5, Integer.parseInt(memberID));
			ResultSet resultSet = ps.executeQuery();
			DBConnector.closeConnection(con);
			return new ResultMessage(true, resultSet, "用户修改成功!");
		}
	}

	public static ResultMessage deleteMemeber(String memberID)
			throws NumberFormatException, SQLException {
		ResultMessage exist = queryMemberByID(memberID);
		if (!exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "该用户ID不存在，请检查操作");
		} else {
			Connection con = DBConnector.getConnection();
			String sql = "delete from member where id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(memberID));
			ResultSet resultSet = ps.executeQuery();
			DBConnector.closeConnection(con);
			return new ResultMessage(true, resultSet, "用户删除成功!");
		}
	}

	public static ResultMessage queryMemberByName(String name)
			throws SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "select * from member where name=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ResultSet resultSet = ps.executeQuery();
		resultSet.last();
		DBConnector.closeConnection(con);
		if (resultSet.getRow() != 0) {
			resultSet.beforeFirst();
			return new ResultMessage(true, resultSet, "该用户存在");
		}
		return new ResultMessage(false, resultSet, "该用户不存在");
	}

	public static ResultMessage queryMemberByID(String memberID)
			throws SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "select * from member where ID=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, memberID);
		ResultSet resultSet = ps.executeQuery();
		resultSet.last();
		DBConnector.closeConnection(con);
		if (resultSet.getRow() != 0) {
			resultSet.beforeFirst();
			return new ResultMessage(true, resultSet, "该用户存在");
		}
		return new ResultMessage(false, resultSet, "该用户不存在");
	}

	public static ResultMessage updateMemberIntegral(String memberID,
			String offset) throws SQLException {
		ResultMessage exist = queryMemberByID(memberID);
		if (!exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "无此ID的会员");
		} else {
			Connection con = DBConnector.getConnection();
			String sql1 = "select integral from member where ID=?";
			PreparedStatement ps = con.prepareStatement(sql1);
			ps.setInt(2, Integer.parseInt(memberID));
			ResultSet resultSet1 = ps.executeQuery();
			resultSet1.next();
			int pre_integral = resultSet1.getInt(1);
			String sql2 = "update member set integral=? where ID=?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			if (pre_integral + Integer.parseInt(offset) > 0) {
				ps2.setInt(1, pre_integral + Integer.parseInt(offset));
				ps2.setInt(2, Integer.parseInt(memberID));
				ResultSet resultSet2 = ps2.executeQuery();
				return new ResultMessage(true, resultSet2, "积分更新成功");
			} else {
				return new ResultMessage(false, null, "该会员积分不够");
			}
		}

	}

	public static ResultMessage insertBook(String name, String ISBN,
			String author, String press, String description,
			String directoryID, Calendar date, double price, double specialprice)
			throws SQLException {
		ResultMessage exist = queryBook(ISBN);
		if (exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "该种图书已存在，添加失败");
		} else {
			Connection con = DBConnector.getConnection();
			String sql = "insert into book values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, ISBN);
			ps.setString(3, author);
			ps.setString(4, press);
			ps.setString(5, description);
			ps.setInt(6, Integer.parseInt(directoryID));
			Date date2 = new Date(date.getTime().getTime());
			ps.setDate(7, date2);
			ps.setDouble(8, price);
			ps.setDouble(9, specialprice);
			ResultSet resultSet = ps.executeQuery();
			return new ResultMessage(true, resultSet, "添加成功");
		}
	}

	public static ResultMessage modifyBook(String name, String ISBN,
			String author, String press, String description,
			String directoryID, Calendar date, double price, double specialprice)
			throws SQLException {
		ResultMessage exist = queryBook(ISBN);
		if (!exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "该种图书不存在，修改失败");
		} else {
			Connection con = DBConnector.getConnection();
			String sql = "update book set name=?,author=?,press=?,description=?,directroyID=?,date=?,price=?,specialprice=? where ISBN=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, author);
			ps.setString(3, press);
			ps.setString(4, description);
			ps.setInt(5, Integer.parseInt(directoryID));
			Date date2 = new Date(date.getTime().getTime());
			ps.setDate(6, date2);
			ps.setDouble(7, price);
			ps.setDouble(8, specialprice);
			ps.setString(9, ISBN);
			ResultSet resultSet = ps.executeQuery();
			return new ResultMessage(true, resultSet, "修改成功");
		}
	}

	public static ResultMessage deleteBook(String BookISBN) throws SQLException {
		ResultMessage exist = queryBook(BookISBN);
		if (!exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "该种图书不存在，删除失败");
		} else {
			Connection con = DBConnector.getConnection();
			String sql = "delete from book where ISBN=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, BookISBN);
			ResultSet resultSet = ps.executeQuery();
			DBConnector.closeConnection(con);
			return new ResultMessage(true, resultSet, "删除成功");
		}
	}

	public static ResultMessage queryBook(String BookISBN) throws SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "select * from book where ISBN=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, BookISBN);
		ResultSet resultSet = ps.executeQuery();
		resultSet.last();
		DBConnector.closeConnection(con);
		if (resultSet.getRow() != 0) {
			resultSet.beforeFirst();
			return new ResultMessage(true, resultSet, "该书存在");
		}
		return new ResultMessage(false, resultSet, "该书不存在");
	}

	public static ResultMessage insertUser(String name, String password,
			String type) throws NumberFormatException, SQLException {
		ResultMessage exist = queryUserByName(name);
		if (exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "该用户名已被占用，添加失败");
		} else {
			Connection con = DBConnector.getConnection();
			String sql = "insert into user values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setInt(3, Integer.parseInt(type));
			ResultSet resultSet = ps.executeQuery();
			DBConnector.closeConnection(con);
			return new ResultMessage(true, resultSet, "管理员添加成功");
		}

	}

	public static ResultMessage modifyUser(String UserID, String name,
			String password, String type) throws SQLException {
		ResultMessage exist = queryUserByID(UserID);
		if (!exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "该用户ID不存在，请检查操作");
		} else {
			Connection con = DBConnector.getConnection();
			String sql = "update user set name=?,password=?,type=? where ID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setInt(3, Integer.parseInt(type));
			ps.setInt(4, Integer.parseInt(UserID));
			ResultSet resultSet = ps.executeQuery();
			DBConnector.closeConnection(con);
			return new ResultMessage(true, resultSet, "修改成功");
		}
	}

	public static ResultMessage deleteUser(String UserID) throws SQLException {
		ResultMessage exist = queryUserByID(UserID);
		if (!exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "该用户ID不存在，请检查操作");
		} else {
			Connection con = DBConnector.getConnection();
			String sql = "delete from user where ID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(UserID));
			ResultSet resultSet = ps.executeQuery();
			DBConnector.closeConnection(con);
			return new ResultMessage(true, resultSet, "删除成功");
		}
	}

	public static ResultMessage queryUserByName(String UserName)
			throws SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "select * from user where name=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, UserName);
		ResultSet resultSet = ps.executeQuery();
		resultSet.last();
		DBConnector.closeConnection(con);
		if (resultSet.getRow() != 0) {
			resultSet.beforeFirst();
			return new ResultMessage(true, resultSet, "该管理员存在");
		}
		return new ResultMessage(false, resultSet, "该管理员不存在");
	}

	public static ResultMessage queryUserByID(String UserID)
			throws SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "select * from user where ID=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, UserID);
		ResultSet resultSet = ps.executeQuery();
		resultSet.last();
		DBConnector.closeConnection(con);
		if (resultSet.getRow() != 0) {
			resultSet.beforeFirst();
			return new ResultMessage(true, resultSet, "该管理员存在");
		}
		return new ResultMessage(false, resultSet, "该管理员不存在");
	}

	public static ResultMessage insertOrder(String memberID,
			ArrayList<String> bookISBNs, double totalprice, Calendar date)
			throws SQLException {
		ResultMessage exist = queryMemberByID(memberID);
		if (!exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "该客户不存在，请检查操作");
		} else {
			Connection con = DBConnector.getConnection();
			String sql = "insert into order values(,?,?,?,)";
			PreparedStatement ps = con.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, Integer.parseInt(memberID));
			ps.setDouble(2, totalprice);
			ps.setTimestamp(3, new Timestamp(date.getTime().getTime()));
			ResultSet resultSet = ps.executeQuery();
			ResultSet orderIDRS = ps.getGeneratedKeys();
			int orderID = 0;
			if (orderIDRS != null && orderIDRS.next()) {
				orderID = orderIDRS.getInt(1);
			}
			boolean success = false;
			for (String bookISBN : bookISBNs) {
				success &= insertOrderItem(orderID + "", bookISBN)
						.isInvokeSuccess();
			}
			if (success) {
				return new ResultMessage(true, resultSet, "添加Order成功");
			} else {
				return new ResultMessage(false, resultSet, "添加Order失败");
			}
		}
	}

	public static ResultMessage modifyOrder(String orderID, String memberID,
			ArrayList<String> bookISBNs, double totalprice, Calendar date,
			int state) throws SQLException {
		ResultMessage exist = queryOrderByOrderID(orderID);
		if (!exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "该订单不存在");
		} else {
			Connection con = DBConnector.getConnection();
			String sql = "update order set totalprice=?,date=?,state=? where orderID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, totalprice);
			ps.setTimestamp(2, new Timestamp(date.getTime().getTime()));
			ps.setInt(3, state);
			ps.setInt(4, Integer.parseInt(orderID));
			ResultSet resultSet = ps.executeQuery();
			String sql2 = "delete * from orderitem where ID=?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setInt(1, Integer.parseInt(orderID));
			ps2.executeQuery();
			boolean success = false;
			for (String bookISBN : bookISBNs) {
				success &= insertOrderItem(orderID + "", bookISBN)
						.isInvokeSuccess();
			}
			DBConnector.closeConnection(con);
			if (success) {
				return new ResultMessage(true, resultSet, "添加Order成功");
			} else {
				return new ResultMessage(false, resultSet, "添加Order失败");
			}
		}
	}

	public static ResultMessage deleteOrder(String orderID) throws SQLException {
		ResultMessage exist = queryOrderByOrderID(orderID);
		if (!exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "该订单不存在");
		} else {
			Connection con = DBConnector.getConnection();
			String sql = "delete * from order where ID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(orderID));
			ResultSet resultSet = ps.executeQuery();
			DBConnector.closeConnection(con);
			return new ResultMessage(true, resultSet, "删除订单成功");
		}
	}

	public static ResultMessage queryOrder(String memberID) throws SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "select * from order where memberID=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, Integer.parseInt(memberID));
		ResultSet resultSet = ps.executeQuery();
		DBConnector.closeConnection(con);
		resultSet.last();
		if (resultSet.getRow() != 0) {
			resultSet.beforeFirst();
			return new ResultMessage(true, resultSet, "订单记录存在");
		} else {
			return new ResultMessage(false, resultSet, "暂无订单记录");
		}
	}

	public static ResultMessage queryOrderByOrderID(String orderID)
			throws SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "select * from order where ID=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, Integer.parseInt(orderID));
		ResultSet resultSet = ps.executeQuery();
		DBConnector.closeConnection(con);
		resultSet.last();
		if (resultSet.getRow() != 0) {
			resultSet.beforeFirst();
			return new ResultMessage(true, resultSet, "该订单存在");
		} else {
			return new ResultMessage(false, resultSet, "该订单不存在");
		}
	}

	public static ResultMessage insertOrderItem(String orderID, String bookISBN)
			throws SQLException {
		ResultMessage orderExist = queryOrderByOrderID(orderID);
		if (!orderExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "该订单ID不存在");
		} else {
			Connection con = DBConnector.getConnection();
			int sum;
			String sql = "select count(*) from orderitem where orderID=? and bookISBN=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(orderID));
			ps.setString(2, bookISBN);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				sum = resultSet.getInt(1);
				sql = "update orderitem set sum=? where orderID=? and bookISBN=?";
				ps.setInt(1, sum + 1);
				ps = con.prepareStatement(sql);
				ps.setInt(2, Integer.parseInt(orderID));
				ps.setString(3, bookISBN);
				resultSet = ps.executeQuery();
				return new ResultMessage(true, resultSet, "添加订单项成功");
			} else {
				sql = "insert into orderitem values(,?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(orderID));
				ps.setString(2, bookISBN);
				ps.setInt(3, 1);
				resultSet = ps.executeQuery();
				return new ResultMessage(true, resultSet, "添加订单项成功");
			}
		}
	}

	public static ResultMessage modifyOrderItem(String orderID,
			String bookISBN, int sum) throws SQLException {
		ResultMessage exist = queryOrderByOrderID(orderID);
		if (!exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "无此订单存在");
		} else {
			Connection con = DBConnector.getConnection();
			String sql = "update orderitem set num=? where orderID=? and bookISBN=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, sum);
			ps.setInt(2, Integer.parseInt(orderID));
			ps.setString(3, bookISBN);
			ResultSet resultSet = ps.executeQuery();
			DBConnector.closeConnection(con);
			return new ResultMessage(true, resultSet, "修改订单项成功");
		}
	}

	public static ResultMessage deleteOrderItem(String orderID, String bookISBN)
			throws SQLException {
		ResultMessage exist = queryOrderByOrderID(orderID);
		if (!exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "无此订单存在");
		} else {
			Connection con = DBConnector.getConnection();
			String sql = "delete *from orderitem where orderID=? and bookISBN=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(orderID));
			ps.setString(2, bookISBN);
			ResultSet resultSet = ps.executeQuery();
			DBConnector.closeConnection(con);
			return new ResultMessage(true, resultSet, "删除订单项成功");
		}
	}

	public static ResultMessage queryOrderItem(String orderID)
			throws SQLException {
		ResultMessage exist = queryOrderByOrderID(orderID);
		if (!exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "无此订单存在");
		} else {
			Connection con = DBConnector.getConnection();
			String sql = "select * from orderitem where orderID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(orderID));
			ResultSet resultSet = ps.executeQuery();
			resultSet.last();
			DBConnector.closeConnection(con);
			if (resultSet.getRow() != 0) {
				resultSet.beforeFirst();
				return new ResultMessage(true, resultSet, "订单项查询成功");
			} else {
				return new ResultMessage(false, resultSet, "订单项查询失败");
			}
		}
	}

	public static ResultMessage insertPromotion(int leastIntegral,
			Calendar begin, Calendar end, double discountRate,
			double EquivalentDenomination) throws SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "insert into promotion values(,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, leastIntegral);
		ps.setDate(2, new Date(begin.getTime().getTime()));
		ps.setDate(3, new Date(end.getTime().getTime()));
		ps.setDouble(4, discountRate);
		ps.setDouble(5, EquivalentDenomination);
		ResultSet resultSet = ps.executeQuery();
		DBConnector.closeConnection(con);
		return new ResultMessage(true, resultSet, "添加促销策略成功");
	}

	public static ResultMessage modifyPromotion(String promotionID,
			int leastIntegral, Calendar begin, Calendar end,
			double discountRate, double EquivalentDenomination)
			throws NumberFormatException, SQLException {
		ResultMessage exist = queryPromotion(promotionID);
		if (!exist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "此促销ID不存在");
		} else {

		}

	}

	public static ResultMessage deletePromotion() {

	}

	public static ResultMessage queryPromotion(String promotionID)
			throws NumberFormatException, SQLException {
		Connection con = DBConnector.getConnection();
		String sql = "select * from promotion where promotionID=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, Integer.parseInt(promotionID));
		ResultSet resultSet = ps.executeQuery();
		resultSet.last();
		DBConnector.closeConnection(con);
		if (resultSet.getRow() != 0) {
			resultSet.beforeFirst();
			return new ResultMessage(true, resultSet, "此促销存在");
		} else {
			return new ResultMessage(false, resultSet, "此促销不存在");
		}
	}

	public static ResultMessage insertCoupons() {

	}

	public static ResultMessage modifyCoupons() {

	}

	public static ResultMessage deleteCoupons() {

	}

	public static ResultMessage queryCoupons() {

	}

	public static ResultMessage insertEquBond() {

	}

	public static ResultMessage modifyEquBond() {

	}

	public static ResultMessage deleteEquBond() {

	}

	public static ResultMessage queryEquBond() {

	}

	public static ResultMessage insertCollect() {

	}

	public static ResultMessage deleteCollect(String memberID, String bookISBN) {

	}

	public static ResultMessage queryCollect(String memberID) {

	}

	public static ResultMessage getAllBooks() {

	}

	public static ResultMessage getBooksByDirectory(String DirectoryID) {

	}

}
