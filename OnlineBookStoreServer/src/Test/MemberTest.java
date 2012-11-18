package Test;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit3.JUnit3Mockery;
import org.jmock.integration.junit3.MockObjectTestCase;

import Member.MemberService;
import RMI.ResultMessage;

public class MemberTest extends MockObjectTestCase {
	private MemberService memberService = null;
	private Mockery context = null;
	private MemberService mockInterface = null;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = new JUnit3Mockery();
		mockInterface = context.mock(MemberService.class);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		memberService = null;
		context = null;
		mockInterface = null;
	}

	public void testAddMember() throws SQLException, RemoteException {
		context.checking(new Expectations() {
			{
				oneOf(mockInterface).addMember("", "", "", null);
				will(returnValue(new ResultMessage(true, null,
						"add member success")));
			}
		});
		assertEquals(true, mockInterface.addMember("", "", "", null)
				.isInvokeSuccess());
	}

	public void testDeleteMember() throws SQLException, RemoteException {
		context.checking(new Expectations() {
			{
				oneOf(mockInterface).deleteMember("");
				will(returnValue(new ResultMessage(true, null,
						"delete member success")));
			}
		});
		assertEquals(true, mockInterface.deleteMember("").isInvokeSuccess());
	}
	
	public void testModifyMember() throws SQLException, RemoteException {
		context.checking(new Expectations() {
			{
				oneOf(mockInterface).modifyMember("", "", "", "", null);
				will(returnValue(new ResultMessage(true, null,
						"modify member success")));
			}
		});
		assertEquals(true, mockInterface.modifyMember("", "", "", "", null).isInvokeSuccess());
	}
}
