package RMI;

import java.rmi.Remote;


public interface addDirectory extends Remote{
	public ResultMessage addDirectory(String DirectoryName)
			throws java.rmi.RemoteException;
}
