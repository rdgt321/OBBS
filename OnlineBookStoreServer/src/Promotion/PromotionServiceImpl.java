package Promotion;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import DBC.CouponsDAO;
import DBC.DAOFactory;
import DBC.EquivalentBondDAO;
import DBC.MessageDAO;
import DBC.PromotionDAO;
import RMI.ResultMessage;

public class PromotionServiceImpl extends UnicastRemoteObject implements
		PromotionService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9198592419143809531L;
	private PromotionDAO promotionDAO = null;
	private CouponsDAO couponsDAO = null;
	private EquivalentBondDAO equivalentBondDAO = null;
	private MessageDAO messageDAO = null;

	public PromotionServiceImpl() throws RemoteException {
		super();
		try {
			Naming.bind("rmi://localhost:1099/PromotionService", this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
		promotionDAO = DAOFactory.getPromotionDAO();
		couponsDAO = DAOFactory.getCouponsDAO();
		equivalentBondDAO = DAOFactory.getEquivalentBondDAO();
		messageDAO = DAOFactory.getMessageDAO();
	}

	@Override
	public ResultMessage deletePromotion(int promotionID)
			throws RemoteException {
		return promotionDAO.deletePromotion(promotionID);
	}

	@Override
	public ResultMessage triggerPromotion(int memberID, int promotionID)
			throws RemoteException {
		ResultMessage promotionMessage = promotionDAO
				.queryPromotion(promotionID);
		if (promotionMessage.isInvokeSuccess()) {
			PromotionPO promotion = (PromotionPO) promotionMessage
					.getResultSet().get(0);
			double discountRate = promotion.getDiscountRate();
			double equivalentDenomination = promotion
					.getEquivalentDenomination();
			if (discountRate != 0) {
				couponsDAO.addCoupons(new CouponsPO(-1, memberID, discountRate,
						promotion.getEndDate(), false));
				messageDAO.addMessage(memberID, "折扣券赠送",
						"您获得了" + promotion.getName() + "促销的折扣券");
			}
			if (equivalentDenomination != 0) {
				equivalentBondDAO.addEquivalentBond(new EquivalentBondPO(-1,
						memberID, promotion.getBondUseLimit(),
						equivalentDenomination, promotion.getEndDate(), false));
				messageDAO.addMessage(memberID, "等价券赠送",
						"您获得了" + promotion.getName() + "促销的折扣券");
			}
			return new ResultMessage(true, null, "trigger success");
		}
		return promotionMessage;
	}

	@Override
	public ResultMessage addPromotion(PromotionPO promotionPO)
			throws RemoteException {
		return promotionDAO.addPromotion(promotionPO);
	}

	@Override
	public ResultMessage getPormotionList() throws RemoteException {
		return promotionDAO.getPromotionList();
	}

}
