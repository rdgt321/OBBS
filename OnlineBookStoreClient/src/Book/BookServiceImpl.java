package Book;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import ClientRunner.Const;
import RMI.ResultMessage;

public class BookServiceImpl implements BookService {
	private BookService service_stub = null;

	public BookServiceImpl() throws MalformedURLException, RemoteException,
			NotBoundException {
		service_stub = (BookService) Naming
				.lookup(Const.SERVER + "BookService");
	}

	@Override
	public ResultMessage addBook(BookPO bookPO) throws RemoteException {
		return service_stub.addBook(bookPO);
	}

	@Override
	public ResultMessage addDirectory(DirectoryPO directoryPO)
			throws RemoteException {
		return service_stub.addDirectory(directoryPO);
	}

	@Override
	public ResultMessage deleteBook(String bookISBN) throws RemoteException {
		return service_stub.deleteBook(bookISBN);
	}

	@Override
	public ResultMessage deleteDirectory(int directoryID)
			throws RemoteException {
		return service_stub.deleteDirectory(directoryID);
	}

	@Override
	public ResultMessage modifyBook(BookPO bookPO) throws RemoteException {
		return service_stub.modifyBook(bookPO);
	}

	@Override
	public ResultMessage modifyDirectory(DirectoryPO directoryPO)
			throws RemoteException {
		return service_stub.modifyDirectory(directoryPO);
	}

	@Override
	public ResultMessage getSelectedBook(String bookISBN)
			throws RemoteException {
		return service_stub.getSelectedBook(bookISBN);
	}

	@Override
	public ResultMessage getSelectedDirectory(int directoryID)
			throws RemoteException {
		return service_stub.getSelectedDirectory(directoryID);
	}

	@Override
	public ResultMessage getAllBooks() throws RemoteException {
		return service_stub.getAllBooks();
	}

	@Override
	public ResultMessage getAllDirectories() throws RemoteException {
		return service_stub.getAllDirectories();
	}

	@Override
	public ResultMessage searchBook(String searchString, int type)
			throws RemoteException {
		return service_stub.searchBook(searchString, type);
	}

	@Override
	public ResultMessage getTopBooksInTotal(int num) throws RemoteException {
		return service_stub.getTopBooksInTotal(num);
	}

	@Override
	public ResultMessage getTopBooksInDirectory(int diectoryID, int num)
			throws RemoteException {
		return service_stub.getTopBooksInDirectory(diectoryID, num);
	}

	@Override
	public ResultMessage getTopDirectories(int num) throws RemoteException {
		return service_stub.getTopDirectories(num);
	}

	@Override
	public ResultMessage setRate(String bookISBN, int memberID, double rate)
			throws RemoteException {
		return service_stub.setRate(bookISBN, memberID, rate);
	}

	@Override
	public ResultMessage getRate(String bookISBN) throws RemoteException {
		return service_stub.getRate(bookISBN);
	}

	@Override
	public ResultMessage queryRate(String bookISBN, int memberID)
			throws RemoteException {
		return service_stub.queryRate(bookISBN, memberID);
	}

}
