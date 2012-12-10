package RMI;

import java.rmi.Remote;

import Book.DirectoryPO;

public interface deleteDirectory extends Remote {
	public ResultMessage deleteDirectory(int directoryID)
			throws java.rmi.RemoteException;
}
