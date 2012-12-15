package RMI;

import java.rmi.Remote;

import Book.DirectoryPO;


public interface getSelectedDirectory extends Remote {
	public ResultMessage getSelectedDirectory(int directoryID)
			throws java.rmi.RemoteException;
}
