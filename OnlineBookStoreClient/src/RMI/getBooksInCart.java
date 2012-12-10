package RMI;

import java.rmi.Remote;

public interface getBooksInCart extends Remote {
	public ResultMessage getBooksInCart(int memberID) throws java.rmi.RemoteException;
}
