package RMI;

import java.rmi.Remote;


public interface addToCast extends Remote {
	public ResultMessage addToCast(String bookISBN) throws java.rmi.RemoteException;
}
