package RMI;

import java.rmi.Remote;

public interface getTopBooks extends Remote {
	public ResultMessage getTopBooksInTotal(int num) throws java.rmi.RemoteException;

	public ResultMessage getTopBooksInDirectory(int diectoryID,int num)
			throws java.rmi.RemoteException;
}
