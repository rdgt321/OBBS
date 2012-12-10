package RMI;

import java.rmi.Remote;

import Book.BookPO;

public interface deleteBook extends Remote {
	public ResultMessage deleteBook(String bookISBN)
			throws java.rmi.RemoteException;
}
