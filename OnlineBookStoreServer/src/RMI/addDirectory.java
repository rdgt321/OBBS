package RMI;

import java.rmi.Remote;

import Book.DirectoryPO;


public interface addDirectory extends Remote{
	public ResultMessage addDirectory(DirectoryPO directoryPO)
			throws java.rmi.RemoteException;
}
