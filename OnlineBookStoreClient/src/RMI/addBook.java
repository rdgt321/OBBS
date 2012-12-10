package RMI;

import java.rmi.Remote;

import Book.BookPO;

public interface addBook extends Remote {
	public ResultMessage addBook(BookPO bookPO) throws java.rmi.RemoteException;
}
