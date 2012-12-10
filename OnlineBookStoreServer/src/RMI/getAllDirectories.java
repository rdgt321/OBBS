package RMI;

import java.rmi.Remote;

public interface getAllDirectories extends Remote {
	public ResultMessage getAllDirectories() throws java.rmi.RemoteException;
}
