package RMI;

import java.rmi.Remote;

public interface getTopDirectories extends Remote {
	public ResultMessage getTopDirectories(int num) throws java.rmi.RemoteException;
}
