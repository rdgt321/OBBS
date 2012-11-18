package Book;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.Calendar;

import DBC.DBOperations;
import RMI.ResultMessage;

public class BookServiceController extends UnicastRemoteObject implements
		BookService {
	
	public BookServiceController() throws RemoteException {
		super();
		LocateRegistry.createRegistry(1099);
		try {
			Naming.rebind("BookService", this);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultMessage addBook(String name, String ISBN, String author,
			String press, String description, String directoryID,
			Calendar date, double price, double specialprice)
			throws RemoteException {
		// TODO Auto-generated method stub
		ResultMessage resultMessage = null;
		try {
			resultMessage = DBOperations.insertBook(name, ISBN, author, press, description, directoryID, date, price, specialprice);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultMessage;
	}

	@Override
	public ResultMessage addDirectory(String DirectoryName)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage deleteBook(String BookISBN) throws RemoteException {
		// TODO Auto-generated method stub
		ResultMessage resultMessage = null;
		try {
			resultMessage = DBOperations.deleteBook(BookISBN);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultMessage;
	}

	@Override
	public ResultMessage deleteDirectory(String directoryID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage modifyBook(String name, String ISBN, String author,
			String press, String description, String directoryID,
			Calendar date, double price, double specialprice)
			throws RemoteException {
		// TODO Auto-generated method stub
		ResultMessage resultMessage = null;
		try {
			resultMessage = DBOperations.modifyBook(name, ISBN, author, press, description, directoryID, date, price, specialprice);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultMessage;
	}

	@Override
	public ResultMessage modifyDirectory(String directoryID, String name)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage getSelectedBook(String BookISBN)
			throws RemoteException {
		// TODO Auto-generated method stub
		ResultMessage resultMessage = null;
		try {
			resultMessage = DBOperations.queryBook(BookISBN);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultMessage;
	}

	@Override
	public ResultMessage getSelectedDirectory(String directoryID)
			throws RemoteException {
		// TODO Auto-generated method stub
		ResultMessage resultMessage = null;
		try {
			resultMessage = DBOperations.getBooksByDirectory(directoryID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultMessage;
	}

	@Override
	public ResultMessage searchBook(String searchString) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
