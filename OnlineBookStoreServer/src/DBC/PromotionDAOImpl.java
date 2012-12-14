package DBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import Promotion.PromotionPO;
import RMI.ResultMessage;

public class PromotionDAOImpl implements PromotionDAO {

	private ArrayList<PromotionPO> map(ResultSet resultSet) {
		ArrayList<PromotionPO> polist = null;
		int promotionID = 0;
		int leastIntegral = 0;
		String name = null;
		Calendar startDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		double discountRate = 0;
		double equivalentDenomination = 0;
		double bondUseLimit = 0;
		try {
			resultSet.last();
			int len = 0;
			if ((len = resultSet.getRow()) != 0) {
				polist = new ArrayList<PromotionPO>();
				resultSet.beforeFirst();
				for (int i = 0; i < len; i++) {
					resultSet.next();
					promotionID = resultSet.getInt(1);
					leastIntegral = resultSet.getInt(2);
					name = resultSet.getString(3);
					startDate.setTimeInMillis(resultSet.getDate(4).getTime());
					endDate.setTimeInMillis(resultSet.getDate(5).getTime());
					discountRate = resultSet.getDouble(6);
					equivalentDenomination = resultSet.getDouble(7);
					bondUseLimit = resultSet.getDouble(8);
					polist.add(new PromotionPO(promotionID, leastIntegral,
							name, startDate, endDate, discountRate,
							equivalentDenomination, bondUseLimit));
				}
				return polist;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage addPromotion(PromotionPO promotionPO) {
		ResultMessage isExist = queryPromotion(promotionPO.getPromotionID());
		if (isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null, "promotion id exist,add fail");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "insert into promotion(leastintegral,name,startdate,enddate,discountrate,equivalentdenomination,bonduselimit) values (?,?,?,?,?,?,?)";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, promotionPO.getLeastIntegral());
			ps.setString(2, promotionPO.getName());
			ps.setDate(3, new java.sql.Date(promotionPO.getStartDate()
					.getTimeInMillis()));
			ps.setDate(4, new java.sql.Date(promotionPO.getEndDate()
					.getTimeInMillis()));
			ps.setDouble(5, promotionPO.getDiscountRate());
			ps.setDouble(6, promotionPO.getEquivalentDenomination());
			ps.setDouble(7, promotionPO.getBondUseLimit());
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			return new ResultMessage(true, null, "add promotion success");
		}
		return new ResultMessage(false, null, "add promotion failed");
	}

	@Override
	public ResultMessage deletePromotion(int promotionID) {
		ResultMessage isExist = queryPromotion(promotionID);
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null,
					"promotion id does not exist,delete fail");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "delete from promotion where promotionid=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, promotionID);
			row = ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			return new ResultMessage(true, null, "delete promotion success");
		}
		return new ResultMessage(false, null, "delete promotion fail");
	}

	@Override
	public ResultMessage queryPromotion(int promotionID) {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from promotion where promotionid=?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, promotionID);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<PromotionPO> po = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (po != null) {
			return new ResultMessage(true, po, "qeury ok,promotion return");
		}
		return new ResultMessage(false, null, "query fail,no such promotion");
	}

	@Override
	public ResultMessage updatePromotion(PromotionPO promotionPO) {
		ResultMessage isExist = queryPromotion(promotionPO.getPromotionID());
		if (!isExist.isInvokeSuccess()) {
			return new ResultMessage(false, null,
					"promotion id does not exist,update fail");
		}
		Connection con = ConnectionFactory.getConnection();
		String sql = "update promotion set leastintegral=?,startdate=?,enddate=?,discountrate=?,equivalentdenomination=?,bonduselimit=? where promotionid=?";
		PreparedStatement ps;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, promotionPO.getLeastIntegral());
			ps.setDate(2, new java.sql.Date(promotionPO.getStartDate()
					.getTimeInMillis()));
			ps.setDate(3, new java.sql.Date(promotionPO.getEndDate()
					.getTimeInMillis()));
			ps.setDouble(4, promotionPO.getDiscountRate());
			ps.setDouble(5, promotionPO.getEquivalentDenomination());
			ps.setInt(6, promotionPO.getPromotionID());
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (row != 0) {
			return new ResultMessage(true, null, "update promotion success");
		}
		return new ResultMessage(false, null, "update promotion failed");

	}

	@Override
	public ResultMessage getPromotionList() {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select * from promotion where startdate<=CURRENT_DATE() and enddate>=CURRENT_DATE() and name<> ?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, "生日");
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<PromotionPO> po = map(resultSet);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (po != null) {
			return new ResultMessage(true, po, "qeury ok,promotions return");
		}
		return new ResultMessage(false, null, "query fail,no  promotion in db");
	}

}
