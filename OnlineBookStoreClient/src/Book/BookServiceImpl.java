package Book;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import RMI.ResultMessage;

public class BookServiceImpl implements BookService {
	private BookService service_stub = null;

	public BookServiceImpl() {
		try {
			service_stub = (BookService) Naming.lookup("BookService");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
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
	public ResultMessage getSelectedDirectory(String directoryID)
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

}
