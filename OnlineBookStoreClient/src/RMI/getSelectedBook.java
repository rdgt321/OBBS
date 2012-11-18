package RMI;

import java.rmi.Remote;

public interface getSelectedBook extends Remote {
	public ResultMessage getSelectedBook(String BookISBN)
			throws java.rmi.RemoteException;
}
