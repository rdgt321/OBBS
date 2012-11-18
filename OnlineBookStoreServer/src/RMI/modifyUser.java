package RMI;

import java.rmi.Remote;


public interface modifyUser extends Remote {
	public ResultMessage modifyUser(String UserID, String name,
			String password, String type) throws java.rmi.RemoteException;

}
