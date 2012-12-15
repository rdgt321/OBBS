package RMI;

import java.rmi.Remote;

public interface getUsers extends Remote {
	public ResultMessage getUsers() throws java.rmi.RemoteException;
}
