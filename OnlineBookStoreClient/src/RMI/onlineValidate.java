package RMI;

import java.rmi.Remote;

public interface onlineValidate extends Remote {
	public ResultMessage onlineValidate(UserAgent userAgent) throws java.rmi.RemoteException;
}
