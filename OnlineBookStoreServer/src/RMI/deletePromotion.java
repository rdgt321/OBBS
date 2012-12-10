package RMI;

import java.rmi.Remote;

import Promotion.PromotionPO;

public interface deletePromotion extends Remote {
	public ResultMessage deletePromotion(int promotionID)
			throws java.rmi.RemoteException;
}
