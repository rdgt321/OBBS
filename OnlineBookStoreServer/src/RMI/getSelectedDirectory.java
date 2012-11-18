package RMI;

import java.rmi.Remote;


public interface getSelectedDirectory extends Remote {
	public ResultMessage getSelectedDirectory(String directoryID)
			throws java.rmi.RemoteException;
}
