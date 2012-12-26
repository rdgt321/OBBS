package RMI;

import java.rmi.Remote;

public interface deleteFromCart extends Remote {
	public ResultMessage deleteFromCart(String bookISBN,int memberID)
			throws java.rmi.RemoteException;
}
