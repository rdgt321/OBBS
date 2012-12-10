package RMI;

import java.rmi.Remote;

import Book.BookPO;


public interface bookCollect extends Remote {
	public ResultMessage bookCollect(String bookISBN,int memberID) throws java.rmi.RemoteException;
}
