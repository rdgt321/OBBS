package DBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import Member.MemberPO;
import RMI.ResultMessage;
import Server.Encrypt;

public class MemberDAOImpl implements MemberDAO {

	private ArrayList<MemberPO> map(ResultSet resultSet) {
		ArrayList<MemberPO> polist = null;
		int memberid = 0;
		String name = null;
		String password = null;
		String phone = null;
		Calendar birth = null;
		int integral = 0;
		try {
			resultSet.last();
			int len = 0;
			if ((len = resultSet.getRow()) != 0) {
				polist = new ArrayList<MemberPO>();
				resultSet.beforeFirst();
				for (int i = 0; i < len; i++) {
					resultSet.next();
					memberid = resultSet.getInt(1);
					name = resultSet.getString(2);
					password = resultSet.getString(3);
					phone = resultSet.getString(4);
					birth = Calendar.getInstance();
					birth.setTimeInMillis(resultSet.getDate(5).getTime());
					integral = resultSet.getInt(6);
					polist.add(new MemberPO(memberid, name, password, phone,
							birth, integral));
				}
				return polist;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized ResultMessage addMember(MemberPO memberPO) {
		ResultMessage isExist = queryMemberByName(memberPO.getName());
		if (isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "name exist,please try again");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into member(name,password,phone,birth,integral) values(?,?,?,?,0)";
		PreparedStatement ps = null;
		int row = 0;
		int id = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, memberPO.getName());
			ps.setString(2, Encrypt.md5(memberPO.getPassword()));
			ps.setString(3, memberPO.getPhone());
			ps.setDate(4, new java.sql.Date(memberPO.getBirth()
					.getTimeInMillis()));
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			try {
				ResultSet resultSet = null;
				resultSet = ps.getGeneratedKeys();
				resultSet.next();
				id = resultSet.getInt(1);
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ArrayList<Integer> idarr = new ArrayList<Integer>();
			idarr.add(id);
			return new ResultMessage(true, idarr, "add member success");
		}

		return new ResultMessage(false, null, "add member failed");
	}

	@Override
	public synchronized ResultMessage deleteMember(int memberID) {
		ResultMessage isExist = queryMember(memberID);
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "no such memberID");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from member where memberid=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, memberID);
			row = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			return new ResultMessage(true, null, "delete member success");
		}
		return new ResultMessage(false, null, "delete member fail");
	}

	@Override
	public synchronized ResultMessage modifyMember(MemberPO memberPO) {
		ResultMessage isExist = queryMember(memberPO.getID());
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null,
					"no such member id,please try again");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "update member set name=?,password=?,phone=?,birth=? where memberid=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, memberPO.getName());
			ps.setString(2, Encrypt.md5(memberPO.getPassword()));
			ps.setString(3, memberPO.getPhone());
			ps.setDate(4, new java.sql.Date(memberPO.getBirth()
					.getTimeInMillis()));
			ps.setInt(5, memberPO.getID());
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
			return new ResultMessage(true, null, "update member success");
		}
		return new ResultMessage(false, null, "update member failed");
	}

	@Override
	public synchronized ResultMessage queryMember(int memberID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from member where memberid=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<MemberPO> polist = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (polist != null) {
			return new ResultMessage(true, polist, "query ok,return member");
		}
		return new ResultMessage(false, null, "query failed,no such memberid");
	}

	@Override
	public synchronized ResultMessage queryMemberByName(String memberName) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from member where name=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, memberName);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<MemberPO> polist = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (polist != null) {
			return new ResultMessage(true, polist, "query ok,return member");
		}
		return new ResultMessage(false, null,
				"query failed,no such member name");
	}

	@Override
	public synchronized ResultMessage loginValidate(String ID, String password) {
		ResultMessage isExist = queryMemberByName(ID);
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "no such user");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from member where name=? and password=?";
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
		ArrayList<MemberPO> po = map(resultSet);
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
	public synchronized ResultMessage getMembers() {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from member";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<MemberPO> polist = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (polist != null) {
			return new ResultMessage(true, polist, "query ok,return members");
		}
		return new ResultMessage(false, null, "query failed,no member in db");
	}

	@Override
	public synchronized ResultMessage getBirthMembers() {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from member where  DATE_FORMAT(birth,'-%m-%d')=DATE_FORMAT(CURRENT_DATE(),'-%m-%d')";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<MemberPO> polist = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (polist != null) {
			return new ResultMessage(true, polist,
					"query ok,return birth members");
		}
		return new ResultMessage(false, null,
				"query failed,no member bitrh today");
	}

	@Override
	public synchronized ResultMessage updateIntegral(int memberID, int integral) {
		ResultMessage exist = queryMember(memberID);
		if (!exist.isInvokeSuccess()) {
			return exist;
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "update member set integral=integral+? where memberid=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, integral);
			ps.setInt(2, memberID);
			row = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			return new ResultMessage(true, null, "更新用户积分成功");
		}
		return new ResultMessage(false, null, "更新用户积分失败");

	}
}
