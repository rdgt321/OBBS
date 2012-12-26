package RMI;

import java.rmi.Remote;

import Book.BookPO;


public interface modifyBook extends Remote {
	public ResultMessage modifyBook(BookPO bookPO)
			throws java.rmi.RemoteException;
}
