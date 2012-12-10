package RMI;

import java.rmi.Remote;

import User.UserPO;

public interface addUser extends Remote {
	public ResultMessage addUser(UserPO userPO)
			throws java.rmi.RemoteException;
}
