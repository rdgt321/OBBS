package RMI;

import java.rmi.Remote;

public interface Login extends Remote {
	public ResultMessage login(String ID, String password)
			throws java.rmi.RemoteException;
}
