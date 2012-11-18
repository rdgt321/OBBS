package RMI;

import java.rmi.Remote;


public interface bookCollect extends Remote {
	public ResultMessage bookCollect(String bookISBN) throws java.rmi.RemoteException;
}
