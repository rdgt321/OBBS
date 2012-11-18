package Test;

import java.rmi.RemoteException;
import java.sql.SQLException;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit3.JUnit3Mockery;
import org.jmock.integration.junit3.MockObjectTestCase;

import Promotion.PromotionService;
import RMI.ResultMessage;
import Sale.SaleService;

public class SalesTest extends MockObjectTestCase {
	private SaleService saleService = null;
	private Mockery context = null;
	private SaleService mockInterface = null;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = new JUnit3Mockery();
		mockInterface = context.mock(SaleService.class);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		saleService = null;
		context = null;
		mockInterface = null;
	}

	public void testAddPromotion() throws SQLException, RemoteException {
		context.checking(new Expectations() {
			{
				oneOf(mockInterface).addOrder("", null, 0, null);
				will(returnValue(new ResultMessage(true, null,
						"add order success")));
			}
		});
		assertEquals(true, mockInterface.addOrder("", null, 0, null)
				.isInvokeSuccess());
	}

	public void testAddToCast() throws SQLException, RemoteException {
		context.checking(new Expectations() {
			{
				oneOf(mockInterface).addToCast("");
				will(returnValue(new ResultMessage(true, null,
						"add to cast success")));
			}
		});
		assertEquals(true, mockInterface.addToCast("").isInvokeSuccess());
	}
	
	public void testPay() throws SQLException, RemoteException {
		context.checking(new Expectations() {
			{
				oneOf(mockInterface).pay(0);
				will(returnValue(new ResultMessage(true, null,
						"pay success")));
			}
		});
		assertEquals(true, mockInterface.pay(0).isInvokeSuccess());
	}
}
