package RMI;

import java.rmi.Remote;

public interface getAllBooks extends Remote {
	public ResultMessage getAllBooks() throws java.rmi.RemoteException;
}
