package RMI;

import java.rmi.Remote;

import User.UserPO;

public interface deleteUser extends Remote {
	public ResultMessage deleteUser(int userID)
			throws java.rmi.RemoteException;
}
