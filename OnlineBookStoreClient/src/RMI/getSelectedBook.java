package RMI;

import java.rmi.Remote;

public interface getSelectedBook extends Remote {
	public ResultMessage getSelectedBook(String bookISBN)
			throws java.rmi.RemoteException;
}
