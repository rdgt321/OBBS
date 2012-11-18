package Test;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Calendar;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit3.JUnit3Mockery;
import org.jmock.integration.junit3.MockObjectTestCase;

import Book.BookService;
import RMI.ResultMessage;

public class BookTest extends MockObjectTestCase {
	private BookService bookService = null;
	private Mockery context = null;
	private BookService mockInterface = null;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = new JUnit3Mockery();
		mockInterface = context.mock(BookService.class);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		bookService = null;
		context = null;
		mockInterface = null;
	}

	public void testAddBook() throws SQLException, RemoteException {
		context.checking(new Expectations() {
			{
				oneOf(mockInterface)
						.addBook("", "", "", "", "", "", null, 0, 0);
				will(returnValue(new ResultMessage(true, null,
						"add book success")));
			}
		});
		assertEquals(true,
				mockInterface.addBook("", "", "", "", "", "", null, 0, 0)
						.isInvokeSuccess());
	}

	public void testDeleteBook() throws SQLException, RemoteException {
		context.checking(new Expectations() {
			{
				oneOf(mockInterface).deleteBook("");
				will(returnValue(new ResultMessage(true, null,
						"delete book success")));
			}
		});
		assertEquals(true, mockInterface.deleteBook("").isInvokeSuccess());
	}

	public void testSearchBook() throws SQLException, RemoteException {
		context.checking(new Expectations() {
			{
				oneOf(mockInterface).searchBook("");
				will(returnValue(new ResultMessage(true, null,
						"results follows")));
			}
		});
		assertEquals(true, mockInterface.searchBook("").isInvokeSuccess());
	}
}
