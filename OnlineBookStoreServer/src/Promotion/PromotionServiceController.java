package Promotion;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;

import RMI.ResultMessage;

public class PromotionServiceController extends UnicastRemoteObject implements PromotionService{

	protected PromotionServiceController() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResultMessage addPromotion(int leastIntegral, Calendar startDate,
			Calendar endDate, double discountRate, double EquivalentDenomination)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deletePromotion(String promotionID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage triggerPromotion(String custumorID, String promotionID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}


}
