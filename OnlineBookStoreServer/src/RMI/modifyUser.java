package RMI;

import java.rmi.Remote;

import User.UserPO;


public interface modifyUser extends Remote {
	public ResultMessage modifyUser(UserPO userPO) throws java.rmi.RemoteException;

}
