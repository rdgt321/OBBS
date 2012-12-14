package RMI;

import java.rmi.Remote;

public interface logout extends Remote {
	public ResultMessage logout(UserAgent userAgent) throws java.rmi.RemoteException;
}
