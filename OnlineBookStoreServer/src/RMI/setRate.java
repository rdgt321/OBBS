package RMI;

import java.rmi.Remote;

public interface setRate extends Remote {
	public ResultMessage setRate(String bookISBN, int memberID, double rate)
			throws java.rmi.RemoteException;
}
