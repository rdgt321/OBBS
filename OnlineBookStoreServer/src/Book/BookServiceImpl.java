package Book;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import DBC.BookDAO;
import DBC.CollectDAO;
import DBC.DAOFactory;
import DBC.DirectoryDAO;
import RMI.ResultMessage;
import Server.Const;

public class BookServiceImpl extends UnicastRemoteObject implements BookService {

	private BookDAO bookDAO = null;
	private DirectoryDAO directoryDAO = null;

	public BookServiceImpl() throws RemoteException {
		super();
		try {
			Naming.rebind("rmi://localhost:1099/BookService", this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		bookDAO = DAOFactory.getBookDAO();
		directoryDAO = DAOFactory.getDirectoryDAO();
	}

	@Override
	public ResultMessage addBook(BookPO bookPO) throws RemoteException {
		return bookDAO.addBook(bookPO);
	}

	@Override
	public ResultMessage addDirectory(DirectoryPO directoryPO)
			throws RemoteException {
		return directoryDAO.addDirectory(directoryPO);
	}

	@Override
	public ResultMessage deleteBook(String bookISBN) throws RemoteException {
		return bookDAO.deleteBook(bookISBN);
	}

	@Override
	public ResultMessage deleteDirectory(int directoryID)
			throws RemoteException {
		return directoryDAO.deleteDirectory(directoryID);
	}

	@Override
	public ResultMessage modifyBook(BookPO bookPO) throws RemoteException {
		return bookDAO.updateBook(bookPO);
	}

	@Override
	public ResultMessage modifyDirectory(DirectoryPO directoryPO)
			throws RemoteException {
		return directoryDAO.modifyDirectory(directoryPO);
	}

	@Override
	public ResultMessage getSelectedBook(String bookISBN)
			throws RemoteException {
		return bookDAO.queryBookByISBN(bookISBN);
	}

	@Override
	public ResultMessage getSelectedDirectory(String directoryID)
			throws RemoteException {
		return bookDAO.queryBookByDirectory(Integer.parseInt(directoryID));
	}

	@Override
	public ResultMessage searchBook(String searchString, int type)
			throws RemoteException {
		if (type == Const.SEARCH_BY_MULTI) {
			return bookDAO.searchBookByMulti(searchString);
		} else if (type == Const.SEARCH_BY_NAME) {
			return bookDAO.searchBookByName(searchString);
		} else if (type == Const.SEARCH_BY_AUTHOR) {
			return bookDAO.searchBookByAuthor(searchString);
		} else {
			return new ResultMessage(false, null, "unknown search type");
		}
	}

	@Override
	public ResultMessage getAllBooks() throws RemoteException {
		return bookDAO.queryAllBooks();
	}

	@Override
	public ResultMessage getAllDirectories() throws RemoteException {
		return directoryDAO.getAllDirectories();
	}

}
