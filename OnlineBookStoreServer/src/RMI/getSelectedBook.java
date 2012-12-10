package RMI;

import java.rmi.Remote;

import Book.BookPO;

public interface getSelectedBook extends Remote {
	public ResultMessage getSelectedBook(String bookISBN)
			throws java.rmi.RemoteException;
}
