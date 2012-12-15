package Book;

import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBC.BookDAO;
import DBC.ConnectionFactory;
import DBC.DAOFactory;
import DBC.DirectoryDAO;
import RMI.ResultMessage;
import Server.Const;

public class BookServiceImpl extends UnicastRemoteObject implements BookService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6312085607190371127L;
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
	public ResultMessage getSelectedDirectory(int directoryID)
			throws RemoteException {
		return bookDAO.queryBookByDirectory(directoryID);
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

	@Override
	public ResultMessage getTopBooksInTotal(int num) throws RemoteException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select book.* from book left join order_item on book.isbn=order_item.bookisbn group by book.isbn order by sum(order_item.count) desc limit 0,?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<BookPO> books = bookDAO.map(resultSet);
		if (books != null) {
			return new ResultMessage(true, books,
					"query ok,return top books in total");
		}
		return new ResultMessage(false, null, "query top books in total fail");
	}

	@Override
	public ResultMessage getTopBooksInDirectory(int diectoryID, int num)
			throws RemoteException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select book.* from book left join order_item on book.isbn=order_item.bookisbn where book.directoryid=? group by book.isbn order by sum(order_item.count) desc limit 0,?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, diectoryID);
			ps.setInt(2, num);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<BookPO> books = bookDAO.map(resultSet);
		if (books != null) {
			return new ResultMessage(true, books,
					"query ok,return top books in specified directory");
		}
		return new ResultMessage(false, null,
				"query top books in specified directory fail");
	}

	@Override
	public ResultMessage getTopDirectories(int num) throws RemoteException {
		Connection con = ConnectionFactory.getConnection();
		String sql = "select directory.* from directory left join book on directory.directoryid=book.directoryid left join order_item on book.isbn=order_item.bookisbn group by directory.directoryid order by sum(order_item.count) desc limit 0,?";
		PreparedStatement ps;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			resultSet = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<BookPO> books = bookDAO.map(resultSet);
		if (books != null) {
			return new ResultMessage(true, books,
					"query ok,return top directory");
		}
		return new ResultMessage(false, null, "query top directory fail");
	}

}
