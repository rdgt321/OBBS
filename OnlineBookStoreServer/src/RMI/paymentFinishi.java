package RMI;

import java.rmi.Remote;

public interface paymentFinishi extends Remote {
	public ResultMessage paymentFinishi(int orderID, int state)
			throws java.rmi.RemoteException;
}
