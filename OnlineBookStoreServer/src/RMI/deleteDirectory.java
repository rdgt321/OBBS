package RMI;

import java.rmi.Remote;

public interface deleteDirectory extends Remote {
	public ResultMessage deleteDirectory(String directoryID)
			throws java.rmi.RemoteException;
}
