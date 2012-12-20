package Promotion;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import ClientRunner.Const;
import RMI.ResultMessage;

public class PromotionServiceImpl implements PromotionService {
	private PromotionService service_stub = null;

	public PromotionServiceImpl() throws MalformedURLException,
			RemoteException, NotBoundException {
		service_stub = (PromotionService) Naming.lookup(Const.SERVER
				+ "PromotionService");
	}

	@Override
	public ResultMessage addPromotion(PromotionPO promotionPO)
			throws RemoteException {
		return service_stub.addPromotion(promotionPO);
	}

	@Override
	public ResultMessage deletePromotion(int promotionID)
			throws RemoteException {
		return service_stub.deletePromotion(promotionID);
	}

	@Override
	public ResultMessage triggerPromotion(int memberID, int promotionID)
			throws RemoteException {
		return service_stub.triggerPromotion(memberID, promotionID);
	}

	@Override
	public ResultMessage getPormotionList() throws RemoteException {
		return service_stub.getPormotionList();
	}

}
