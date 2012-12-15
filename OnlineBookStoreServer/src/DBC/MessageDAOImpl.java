package DBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Member.MessagePO;
import RMI.ResultMessage;

public class MessageDAOImpl implements MessageDAO {

	private ArrayList<MessagePO> map(ResultSet resultSet) {
		ArrayList<MessagePO> msg = null;
		try {
			resultSet.last();
			int len = 0;
			if ((len = resultSet.getRow()) != 0) {
				msg = new ArrayList<MessagePO>();
				resultSet.beforeFirst();
				for (int i = 0; i < len; i++) {
					resultSet.next();
					int messageID = resultSet.getInt(1);
					int memberID = resultSet.getInt(2);
					String title = resultSet.getString(3);
					String context = resultSet.getString(4);
					updateMessage(messageID, true);
					msg.add(new MessagePO(messageID, memberID, title, context,
							true));
				}
				return msg;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage addMessage(int memberID, String msg) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into message(memberid,msg,sent) values(?,?,?)";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			ps.setString(2, msg);
			ps.setBoolean(3, false);
			row = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			return new ResultMessage(true, null, "add msg success");
		}
		return new ResultMessage(false, null, "add msg fail");
	}

	@Override
	public ResultMessage updateMessage(int messageID, boolean sent) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "update message set sent=? where messageid=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setBoolean(1, sent);
			ps.setInt(2, messageID);
			row = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			return new ResultMessage(true, null, "update success");
		}
		return new ResultMessage(false, null, "update fail");
	}

	@Override
	public ResultMessage getMessage(int memberID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from message where memberid=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<MessagePO> msg = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (msg != null) {
			return new ResultMessage(true, msg, "query ok,msg return");
		}
		return new ResultMessage(false, null, "no msg");
	}

}
