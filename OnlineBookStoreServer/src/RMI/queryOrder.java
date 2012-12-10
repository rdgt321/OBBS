package RMI;

import java.rmi.Remote;


public interface queryOrder extends Remote {
	public ResultMessage orderQuery(int orderID)
			throws java.rmi.RemoteException;
}
