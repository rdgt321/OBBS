package DBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

import Member.MemberPO;
import RMI.ResultMessage;

public class MemberDAOImpl implements MemberDAO {

	private ArrayList<MemberPO> map(ResultSet resultSet) {
		ArrayList<MemberPO> polist = null;
		int memberid = 0;
		String name = null;
		String password = null;
		String phone = null;
		Calendar birth = null;
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
					polist.add(new MemberPO(memberid, name, password, phone,
							birth));
				}
				return polist;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage addMember(MemberPO memberPO) {
		ResultMessage isExist = queryMemberByName(memberPO.getName());
		if (isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "name exist,please try again");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into member(name,password,phone,birth) values(?,?,?,?)";
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
	public ResultMessage deleteMember(int memberID) {
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
	public ResultMessage modifyMember(MemberPO memberPO) {
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
			ps.setInt(1, memberPO.getID());
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
	public ResultMessage queryMember(int memberID) {
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
	public ResultMessage queryMemberByName(String memberName) {
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
	public ResultMessage getMembers(int pagenum, int num_per_page) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from member limit ?,?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, pagenum * num_per_page);
			ps.setInt(2, num_per_page);
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
				"query failed,no such member in this page");
	}

	@Override
	public ResultMessage getTotalNum() {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select count(*) from member";
		ResultSet resultSet = null;
		ArrayList<Integer> totalnum = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			resultSet = ps.executeQuery();
			resultSet.next();
			totalnum = new ArrayList<Integer>();
			totalnum.add(resultSet.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return new ResultMessage(false, null,
					"query fail,can not get total num");
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ResultMessage(true, totalnum, "query ok total num return");
	}

	@Override
	public ResultMessage loginValidate(String ID, String password) {
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
	public ResultMessage getMembers() {
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
	public ResultMessage getBirthMembers() {
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
}
