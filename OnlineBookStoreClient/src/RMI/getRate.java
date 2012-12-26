package RMI;

import java.rmi.Remote;

public interface getRate extends Remote {
	public ResultMessage getRate(String bookISBN)
			throws java.rmi.RemoteException;
}
