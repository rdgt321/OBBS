package RMI;

import java.rmi.Remote;

public interface modifyOrder extends Remote {
	public ResultMessage orderModify(String orderID)
			throws java.rmi.RemoteException;
}
