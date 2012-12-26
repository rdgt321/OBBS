package RMI;

import java.rmi.Remote;

public interface bookPurchased extends Remote {
	public ResultMessage bookPurchased(String bookISBN, int memberID)
			throws java.rmi.RemoteException;
}
