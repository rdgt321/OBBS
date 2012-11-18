package RMI;

import java.rmi.Remote;

public interface deleteUser extends Remote {
	public ResultMessage deleteUser(String UserID)
			throws java.rmi.RemoteException;
}
