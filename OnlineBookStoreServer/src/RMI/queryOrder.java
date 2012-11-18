package RMI;

import java.rmi.Remote;


public interface queryOrder extends Remote {
	public ResultMessage orderQuery(String orderID)
			throws java.rmi.RemoteException;
}
