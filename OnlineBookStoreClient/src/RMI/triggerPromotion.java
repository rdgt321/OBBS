package RMI;

import java.rmi.Remote;


public interface triggerPromotion extends Remote {
	public ResultMessage triggerPromotion(String custumorID, String promotionID)
			throws java.rmi.RemoteException;
}
