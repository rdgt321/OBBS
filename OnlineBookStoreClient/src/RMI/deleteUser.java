package RMI;

import java.rmi.Remote;

public interface deleteUser extends Remote {
	public ResultMessage deleteUser(int userID)
			throws java.rmi.RemoteException;
}
