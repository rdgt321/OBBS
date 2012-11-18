package RMI;

import java.rmi.Remote;

public interface deleteFromCast extends Remote {
	public ResultMessage deleteFromCast(String BookISBN)
			throws java.rmi.RemoteException;
}
