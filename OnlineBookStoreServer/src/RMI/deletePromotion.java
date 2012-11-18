package RMI;

import java.rmi.Remote;

public interface deletePromotion extends Remote {
	public ResultMessage deletePromotion(String promotionID)
			throws java.rmi.RemoteException;
}
