package RMI;

import java.rmi.Remote;

public interface getMessage extends Remote {
	public ResultMessage getMessage(UserAgent userAgent) throws java.rmi.RemoteException;
}
