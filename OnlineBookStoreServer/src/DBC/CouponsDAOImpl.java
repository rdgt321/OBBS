package DBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import Promotion.CouponsPO;
import RMI.ResultMessage;

public class CouponsDAOImpl implements CouponsDAO {

	private ArrayList<CouponsPO> map(ResultSet resultSet) {
		ArrayList<CouponsPO> polist = null;
		int counponsID = 0;
		int ownerID = 0;
		double discountRate = 0;
		Calendar endDate = null;
		boolean used = false;
		try {
			resultSet.last();
			int len = 0;
			if ((len = resultSet.getRow()) != 0) {
				polist = new ArrayList<CouponsPO>();
				resultSet.beforeFirst();
				for (int i = 0; i < len; i++) {
					resultSet.next();
					counponsID = resultSet.getInt(1);
					ownerID = resultSet.getInt(2);
					discountRate = resultSet.getDouble(3);
					endDate = Calendar.getInstance();
					endDate.setTimeInMillis(resultSet.getDate(4).getTime());
					used = resultSet.getBoolean(5);
					polist.add(new CouponsPO(counponsID, ownerID, discountRate,
							endDate, used));
				}
				return polist;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public synchronized ResultMessage addCoupons(CouponsPO couponsPO) {
		ResultMessage isExist = queryCoupons(couponsPO.getCounponsID());
		if (isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "couponsid exists,add fail");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into coupons(ownerid,discountrate,enddate,used) values(?,?,?,?)";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, couponsPO.getOwnerID());
			ps.setDouble(2, couponsPO.getDiscountRate());
			ps.setDate(3, new java.sql.Date(couponsPO.getEndDate().getTime()
					.getTime()));
			ps.setBoolean(4, couponsPO.isUsed());
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
			return new ResultMessage(true, null, "add coupons success");
		}
		return new ResultMessage(false, null, "add coupons failed");
	}

	@Override
	public synchronized ResultMessage deleteCoupons(int couponsID) {
		ResultMessage isExist = queryCoupons(couponsID);
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "no such coupons");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from coupons where couponsid=?";
		int row = 0;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, couponsID);
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
			return new ResultMessage(true, null, "delete coupons success");
		}
		return new ResultMessage(false, null, "delete coupons failed");
	}

	@Override
	public synchronized ResultMessage updateCoupons(CouponsPO couponsPO) {
		ResultMessage isExist = queryCoupons(couponsPO.getCounponsID());
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null,
					"couponsid does not exists,update fail");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "update coupons set ownerid=?,discoutrate=?,enddate=?,used=? where couponsid=?";
		int row = 0;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, couponsPO.getOwnerID());
			ps.setDouble(2, couponsPO.getDiscountRate());
			ps.setDate(3, new java.sql.Date(couponsPO.getEndDate().getTime()
					.getTime()));
			ps.setBoolean(4, couponsPO.isUsed());
			ps.setInt(5, couponsPO.getCounponsID());
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
			return new ResultMessage(true, null, "update coupons success");
		}
		return new ResultMessage(false, null, "update coupons failed");
	}

	@Override
	public synchronized ResultMessage queryCoupons(int couponsID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from  coupons where couponsid = ?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, couponsID);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<CouponsPO> polist = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (polist != null) {
			return new ResultMessage(true, polist, "coupons exist ");
		}
		return new ResultMessage(false, null, "no coupons have");
	}

	@Override
	public synchronized ResultMessage getCoupons(int memberID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from coupons where ownerid=? and used=?";
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
		ArrayList<CouponsPO> polist = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (polist != null) {
			return new ResultMessage(true, polist, "query ok,coupons return ");
		}
		return new ResultMessage(false, null, "no coupons have");
	}

}
