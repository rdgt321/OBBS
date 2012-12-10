package RMI;

import java.rmi.Remote;
import java.util.Calendar;

import Promotion.PromotionPO;


public interface addPromption extends Remote {
	public ResultMessage addPromotion(PromotionPO promotionPO)
			throws java.rmi.RemoteException;
}
