package RMI;

import java.rmi.Remote;

import Sale.CartPO;

import Book.BookPO;

public interface deleteFromCart extends Remote {
	public ResultMessage deleteFromCart(String bookISBN,int memberID)
			throws java.rmi.RemoteException;
}
