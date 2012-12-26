package RMI;

import java.rmi.Remote;

public interface deleteBook extends Remote {
	public ResultMessage deleteBook(String bookISBN)
			throws java.rmi.RemoteException;
}
