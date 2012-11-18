package Test;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit3.JUnit3Mockery;
import org.jmock.integration.junit3.MockObjectTestCase;

import Promotion.PromotionService;
import RMI.ResultMessage;

public class PromotionTest extends MockObjectTestCase{
	private PromotionService promotionService = null;
	private Mockery context = null;
	private PromotionService mockInterface = null;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = new JUnit3Mockery();
		mockInterface = context.mock(PromotionService.class);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		promotionService = null;
		context = null;
		mockInterface = null;
	}
	
	public void testAddPromotion() throws SQLException, RemoteException {
		context.checking(new Expectations() {
			{
				oneOf(mockInterface).addPromotion(0, null, null, 0, 0);
				will(returnValue(new ResultMessage(true, null,
						"add promotion success")));
			}
		});
		assertEquals(true, mockInterface.addPromotion(0, null, null, 0, 0)
				.isInvokeSuccess());
	}

	public void testDeleteMember() throws SQLException, RemoteException {
		context.checking(new Expectations() {
			{
				oneOf(mockInterface).deletePromotion("");
				will(returnValue(new ResultMessage(true, null,
						"delete member success")));
			}
		});
		assertEquals(true, mockInterface.deletePromotion("").isInvokeSuccess());
	}
}
