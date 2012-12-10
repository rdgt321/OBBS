package RMI;

import java.rmi.Remote;


public interface triggerPromotion extends Remote {
	public ResultMessage triggerPromotion(int memberID, int promotionID)
			throws java.rmi.RemoteException;
}
