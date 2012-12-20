package DBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import RMI.ResultMessage;
import Server.Const;
import User.AdminPO;
import User.GeneralManagerPO;
import User.SalesManagerPO;
import User.UserPO;

public class UserDAOImpl implements UserDAO {

	private ArrayList<UserPO> map(ResultSet resultSet) {
		ArrayList<UserPO> polist = null;
		int userid = 0;
		String name = null;
		String password = null;
		int type = -1;
		try {
			resultSet.last();
			int len = 0;
			if ((len = resultSet.getRow()) != 0) {
				polist = new ArrayList<UserPO>();
				resultSet.beforeFirst();
				for (int i = 0; i < len; i++) {
					resultSet.next();
					userid = resultSet.getInt(1);
					name = resultSet.getString(2);
					password = resultSet.getString(3);
					type = resultSet.getInt(4);
					if (type == Const.ADMIN) {
						polist.add(new AdminPO(userid, name, password));
					} else if (type == Const.GENERALMANAGER) {
						polist.add(new GeneralManagerPO(userid, name, password));
					} else if (type == Const.SALESMANAGER) {
						polist.add(new SalesManagerPO(userid, name, password));
					} else {
						return null;
					}
				}
				return polist;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized ResultMessage addUser(UserPO userPO) {
		ResultMessage isExist = queryUser(userPO.getName());
		if (isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "user name exist,add fail");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into user(name,password,type) values(?,?,?)";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userPO.getName());
			ps.setString(2, Encrypt.md5(userPO.getPassword()));
			ps.setInt(3, userPO.getType());
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
			return new ResultMessage(true, null, "add user success");
		}
		return new ResultMessage(false, null, "add user fail");
	}

	@Override
	public synchronized ResultMessage deleteUser(int userID) {
		ResultMessage isExist = queryUserByID(userID);
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null,
					"user name does not exist,delete fail");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from user where userid=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, userID);
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
			return new ResultMessage(true, null, "delete user success");
		}
		return new ResultMessage(false, null, "delete user fail");
	}

	@Override
	public synchronized ResultMessage updateUser(UserPO userPO) {
		ResultMessage isExist = queryUserByID(userPO.getID());
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null,
					"user name does not exist,update fail");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "update user set name=?,password=?,type=? where userid=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userPO.getName());
			ps.setString(2, Encrypt.md5(userPO.getPassword()));
			ps.setInt(3, userPO.getType());
			ps.setInt(4, userPO.getID());
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
			return new ResultMessage(true, null, "update user success");
		}
		return new ResultMessage(false, null, "update user fail");
	}

	@Override
	public synchronized ResultMessage queryUser(String name) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from user where name=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			resultSet = ps.executeQuery();
			resultSet.last();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<UserPO> po = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (po != null) {
			return new ResultMessage(true, po, "query ok,user return");
		}
		return new ResultMessage(false, null, "no such user");
	}

	@Override
	public synchronized ResultMessage queryUserByID(int userID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from user where userid=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, userID);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<UserPO> po = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (po != null) {
			return new ResultMessage(true, po, "query ok,user return");
		}
		return new ResultMessage(false, null, "no such user");
	}

	@Override
	public synchronized ResultMessage loginValidate(String ID, String password) {
		ResultMessage isExist = queryUser(ID);
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "no such user");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from user where name=? and password=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, ID);
			ps.setString(2, Encrypt.md5(password));
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<UserPO> po = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (po != null) {
			return new ResultMessage(true, po, "login success");
		}
		return new ResultMessage(false, null, "login failed,password wrong");
	}

	@Override
	public synchronized ResultMessage getUsers() {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from user";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<UserPO> po = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (po != null) {
			return new ResultMessage(true, po, "return users success");
		}
		return new ResultMessage(false, null, "no users in db");
	}
}
