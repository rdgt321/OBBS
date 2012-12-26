package RMI;

import java.rmi.Remote;

public interface queryRate extends Remote {
	public ResultMessage queryRate(String bookISBN, int memberID)
			throws java.rmi.RemoteException;
}
