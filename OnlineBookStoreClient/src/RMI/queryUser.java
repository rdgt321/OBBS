package RMI;

import java.rmi.Remote;

public interface queryUser extends Remote {
	public ResultMessage queryUser(String name) throws java.rmi.RemoteException;
}
