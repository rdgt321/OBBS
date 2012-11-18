package RMI;

import java.rmi.Remote;

public interface addUser extends Remote {
	public ResultMessage addUser(String name, String password, String type)
			throws java.rmi.RemoteException;
}
