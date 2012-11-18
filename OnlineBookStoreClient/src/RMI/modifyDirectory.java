package RMI;

import java.rmi.Remote;


public interface modifyDirectory extends Remote {
	public ResultMessage modifyDirectory(String directoryID,String name)
			throws java.rmi.RemoteException;
}
