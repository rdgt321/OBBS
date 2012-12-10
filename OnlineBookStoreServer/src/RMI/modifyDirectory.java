package RMI;

import java.rmi.Remote;

import Book.DirectoryPO;


public interface modifyDirectory extends Remote {
	public ResultMessage modifyDirectory(DirectoryPO directoryPO)
			throws java.rmi.RemoteException;
}
