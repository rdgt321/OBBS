package DBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import Promotion.EquivalentBondPO;
import RMI.ResultMessage;

public class EquivalentBondDAOImpl implements EquivalentBondDAO {

	private ArrayList<EquivalentBondPO> map(ResultSet resultSet) {
		ArrayList<EquivalentBondPO> polist = null;
		int equivalentBondID = 0;
		int ownerID = 0;
		double equivalentDenomination = 0;
		double useLimit = 0;
		Calendar endDate = null;
		boolean used = false;
		try {
			resultSet.last();
			int len = 0;
			if ((len = resultSet.getRow()) != 0) {
				polist = new ArrayList<EquivalentBondPO>();
				resultSet.beforeFirst();
				for (int i = 0; i < len; i++) {
					resultSet.next();
					equivalentBondID = resultSet.getInt(1);
					ownerID = resultSet.getInt(2);
					useLimit = resultSet.getDouble(3);
					equivalentDenomination = resultSet.getDouble(4);
					endDate = Calendar.getInstance();
					endDate.setTimeInMillis(resultSet.getDate(5).getTime());
					used = resultSet.getBoolean(6);
					polist.add(new EquivalentBondPO(equivalentBondID, ownerID,
							useLimit, equivalentDenomination, endDate, used));
				}
				return polist;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized ResultMessage addEquivalentBond(
			EquivalentBondPO equivalentBondPO) {
		ResultMessage isExist = queryEquivalentBond(equivalentBondPO
				.getEquivalentBondID());
		if (isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "equbondid exist");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into equivalentbond(ownerid,uselimit,equivalentdenomination,enddate,used) values(?,?,?,?,?)";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, equivalentBondPO.getOwnerID());
			ps.setDouble(2, equivalentBondPO.getUseLimit());
			ps.setDouble(3, equivalentBondPO.getEquivalentDenomination());
			ps.setDate(4, new java.sql.Date(equivalentBondPO.getEndDate()
					.getTime().getTime()));
			ps.setBoolean(5, equivalentBondPO.isUsed());
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
			return new ResultMessage(true, null, "add equivalentbond success");
		}
		return new ResultMessage(false, null, "add equivalentbond fail");
	}

	@Override
	public synchronized ResultMessage queryEquivalentBond(int equivalentBondID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from equivalentbond where equivalentbondid=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, equivalentBondID);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<EquivalentBondPO> polist = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (polist != null) {
			return new ResultMessage(true, polist, "query success");
		}
		return new ResultMessage(false, null, "no such equbondid");
	}

	@Override
	public synchronized ResultMessage deleteEquivalentBond(int equivalentBondID) {
		ResultMessage isExist = queryEquivalentBond(equivalentBondID);
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "equbondid does not exist");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from equivalentbond where equivalentbondid=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, equivalentBondID);
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
			return new ResultMessage(true, null,
					"delete equivalentbond success");
		}
		return new ResultMessage(false, null, "delete equivalentbond fail");
	}

	@Override
	public synchronized ResultMessage updateEquivalentBond(
			EquivalentBondPO equivalentBondPO) {
		ResultMessage isExist = queryEquivalentBond(equivalentBondPO
				.getEquivalentBondID());
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "equbondid does not exist");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "update equivalentbond set ownerid=?,uselimit=?,equivalentdenomination=?,enddate=?,used=? where equivalentbondid=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, equivalentBondPO.getOwnerID());
			ps.setDouble(2, equivalentBondPO.getUseLimit());
			ps.setDouble(3, equivalentBondPO.getEquivalentDenomination());
			ps.setDate(4, new java.sql.Date(equivalentBondPO.getEndDate()
					.getTime().getTime()));
			ps.setBoolean(5, equivalentBondPO.isUsed());
			ps.setInt(6, equivalentBondPO.getEquivalentBondID());
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
			return new ResultMessage(true, null, "add equivalentbond success");
		}
		return new ResultMessage(false, null, "add equivalentbond fail");
	}

	@Override
	public synchronized ResultMessage getEquivalentBond(int memberID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from equivalentbond where ownerid=? and used=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			ps.setBoolean(2, false);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<EquivalentBondPO> polist = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (polist != null) {
			return new ResultMessage(true, polist, "query ok,bonds return");
		}
		return new ResultMessage(false, null, "you have no equbond");
	}

}
