package DBC;

import Promotion.CouponsPO;
import RMI.ResultMessage;

public interface CouponsDAO {
	public ResultMessage addCoupons(CouponsPO couponsPO);

	public ResultMessage deleteCoupons(int couponsID);

	public ResultMessage updateCoupons(CouponsPO couponsPO);

	public ResultMessage queryCoupons(int couponsID);

	public ResultMessage getCoupons(int memberID);

}
