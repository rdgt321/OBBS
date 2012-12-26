package DBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Book.DirectoryPO;
import RMI.ResultMessage;

public class DirectoryDAOImpl implements DirectoryDAO {

	private ArrayList<DirectoryPO> map(ResultSet resultSet) {
		ArrayList<DirectoryPO> polist = null;
		String name = null;
		int directoryid = 0;
		try {
			resultSet.last();
			int len = 0;
			if ((len = resultSet.getRow()) != 0) {
				polist = new ArrayList<DirectoryPO>();
				resultSet.beforeFirst();
				for (int i = 0; i < len; i++) {
					resultSet.next();
					directoryid = resultSet.getInt(1);
					name = resultSet.getString(2);
					polist.add(new DirectoryPO(directoryid, name));
				}
				return polist;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized ResultMessage addDirectory(DirectoryPO directoryPO) {
		ResultMessage isExist = queryDirectoryByName(directoryPO.getName());
		if (isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "目录名称已存在");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into directory(name) values(?)";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, directoryPO.getName());
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
			return new ResultMessage(true, null, "添加成功");
		}
		return new ResultMessage(false, null, "添加失败");
	}

	@Override
	public synchronized ResultMessage deleteDirectory(int directoryID) {
		ResultMessage isExist = queryDirectoryByID(directoryID);
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "不存在对应目录");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from directory where directoryid=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, directoryID);
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
			return new ResultMessage(true, null, "删除成功");
		}
		return new ResultMessage(false, null, "删除失败");
	}

	@Override
	public synchronized ResultMessage modifyDirectory(DirectoryPO directoryPO) {
		ResultMessage isExist = queryDirectoryByID(directoryPO.getID());
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "目录不存在");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "update directory set name=? where directoryid=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, directoryPO.getName());
			ps.setInt(2, directoryPO.getID());
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
			return new ResultMessage(true, null, "修改成功");
		}
		return new ResultMessage(false, null, "修改失败");
	}

	@Override
	public synchronized ResultMessage queryDirectoryByID(int directoryID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from directory where directoryid=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, directoryID);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<DirectoryPO> polist = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (polist != null) {
			return new ResultMessage(true, polist, "返回目录");
		}
		return new ResultMessage(false, null, "目录不存在");
	}

	@Override
	public synchronized ResultMessage queryDirectoryByName(String directroyName) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from directory where name=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, directroyName);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<DirectoryPO> polist = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (polist != null) {
			return new ResultMessage(true, polist, "query ok ,directory return");
		}
		return new ResultMessage(false, null, "no such directory");
	}

	@Override
	public synchronized ResultMessage getAllDirectories() {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from directory";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<DirectoryPO> polist = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (polist != null) {
			return new ResultMessage(true, polist,
					"query ok ,all directory return");
		}
		return new ResultMessage(false, null, "no directory in db");
	}

}
