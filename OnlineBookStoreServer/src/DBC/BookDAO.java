package DBC;

import java.sql.ResultSet;
import java.util.ArrayList;

import Book.BookPO;
import RMI.ResultMessage;

public interface BookDAO {
	public ResultMessage addBook(BookPO bookPO);

	public ResultMessage deleteBook(String bookISBN);

	public ResultMessage updateBook(BookPO bookPO);

	public ResultMessage queryBookByISBN(String bookISBN);

	public ResultMessage queryBookByDirectory(int directoryID);

	public ResultMessage searchBookByName(String keywords);

	public ResultMessage searchBookByAuthor(String keywords);

	public ResultMessage searchBookByMulti(String keywords);

	public ResultMessage queryAllBooks();

	public ArrayList<BookPO> map(ResultSet resultSet);

	public ResultMessage fill(ArrayList<BookPO> books);

}
