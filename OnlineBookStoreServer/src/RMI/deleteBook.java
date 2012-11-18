package RMI;

import java.rmi.Remote;

public interface deleteBook extends Remote {
	public ResultMessage deleteBook(String BookISBN)
			throws java.rmi.RemoteException;
}
