package RMI;

import java.rmi.Remote;

public interface deleteDirectory extends Remote {
	public ResultMessage deleteDirectory(int directoryID)
			throws java.rmi.RemoteException;
}
