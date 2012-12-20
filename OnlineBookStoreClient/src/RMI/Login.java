package RMI;

import java.rmi.Remote;

public interface Login extends Remote {
	public ResultMessage login(String ID, String password, String IP, int type)
			throws java.rmi.RemoteException;
}
