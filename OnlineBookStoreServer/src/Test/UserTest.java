package Test;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit3.JUnit3Mockery;
import org.jmock.integration.junit3.MockObjectTestCase;

import RMI.ResultMessage;
import Sale.SaleService;
import User.UserService;


public class UserTest extends MockObjectTestCase{
	private UserService userService = null;
	private Mockery context = null;
	private UserService mockInterface = null;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = new JUnit3Mockery();
		mockInterface = context.mock(UserService.class);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		userService = null;
		context = null;
		mockInterface = null;
	}
	
	public void testAddUser() throws SQLException, RemoteException {
		context.checking(new Expectations() {
			{
				oneOf(mockInterface).addUser("", "", "");
				will(returnValue(new ResultMessage(true, null,
						"add user success")));
			}
		});
		assertEquals(true, mockInterface.addUser("", "", "")
				.isInvokeSuccess());
	}

	public void testDeleteUser() throws SQLException, RemoteException {
		context.checking(new Expectations() {
			{
				oneOf(mockInterface).deleteUser("");
				will(returnValue(new ResultMessage(true, null,
						"delete user success")));
			}
		});
		assertEquals(true, mockInterface.deleteUser("").isInvokeSuccess());
	}
	
	public void testLogin() throws SQLException, RemoteException {
		context.checking(new Expectations() {
			{
				oneOf(mockInterface).login("", "");
				will(returnValue(new ResultMessage(true, null,
						"login success")));
			}
		});
		assertEquals(true, mockInterface.login("", "").isInvokeSuccess());
	}
}
